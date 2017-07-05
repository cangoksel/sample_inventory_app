package com.github.cangoksel.common.money;

import com.github.cangoksel.common.exceptions.DomainException;
import com.google.common.collect.Maps;
import org.javamoney.moneta.ExchangeRateBuilder;
import org.javamoney.moneta.spi.AbstractRateProvider;
import org.javamoney.moneta.spi.DefaultNumberValue;
import org.springframework.beans.factory.annotation.Autowired;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.NumberValue;
import javax.money.convert.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

/**
 * Created by herdemir on 21.07.2015.
 */
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class TCMBRateProvider extends AbstractRateProvider implements ExchangeRateProvider {
    private static final String BASE_CURRENCY_CODE = Moneys.DEFAULT_CURRENCY_CODE;

    private static final CurrencyUnit BASE_CURRENCY = Monetary.getCurrency(BASE_CURRENCY_CODE);

    private static final ProviderContext CONTEXT = ProviderContextBuilder
            .of(Moneys.DEFAULT_EXCHAGE_RATE_PROVIDER_NAME, RateType.DEFERRED, RateType.HISTORIC)
            .set("providerDescription", "Central Bank of Turkey").set("days", 1).build();

    @Autowired
    private KurRepository repository;

    public TCMBRateProvider() {
        super(CONTEXT);
    }

    @Override
    public ExchangeRate getExchangeRate(ConversionQuery conversionQuery) {
        Objects.requireNonNull(conversionQuery);

        final LocalDate[] dates = getQueryDates(conversionQuery);
        LocalDate selectedDate = null;
        Map<String, ExchangeRate> targets = null;

        // Tatil olmayan ilk günü seç
        for (LocalDate date : dates) {
            targets = getTargetExchangeRatesForDate(date);
            if (!targets.isEmpty()) {
                selectedDate = date;
                break;
            }
        }

        if (Objects.isNull(targets) || targets.isEmpty()) {
            throw new DomainException("Kur oranları tanımlı değil, com.github.cangoksel.sistem yöneticisiyle iletişime geçiniz.");
            //return null;
        }

        ExchangeRateBuilder builder = getBuilder(conversionQuery, selectedDate);

        ExchangeRate sourceRate = targets.get(conversionQuery.getBaseCurrency().getCurrencyCode());
        ExchangeRate target = targets.get(conversionQuery.getCurrency().getCurrencyCode());

        return createExchangeRate(conversionQuery, builder, sourceRate, target);
    }

    private Map<String, ExchangeRate> getTargetExchangeRatesForDate(final LocalDate localDate) {
        final Map<String, ExchangeRate> rateMap = Maps.newHashMap();
        final Collection<Kur> kurlar = repository.findByKurTarihiOrderByParaBirimi(localDate);

        for (final Kur kur : kurlar) {
            ExchangeRate exchangeRate = buildExchangeRate(localDate, kur);
            rateMap.put(kur.getParaBirimi().name(), exchangeRate);
        }

        return rateMap;
    }

    private ExchangeRate buildExchangeRate(LocalDate localDate, Kur kur) {
        final CurrencyUnit term = Monetary.getCurrency(kur.getParaBirimi().name());
        RateType rateType = RateType.HISTORIC;
        final ExchangeRateBuilder builder;
        if (Objects.nonNull(localDate)) {
            if (localDate.equals(LocalDate.now())) {
                rateType = RateType.DEFERRED;
            }
            builder = new ExchangeRateBuilder(ConversionContextBuilder.create(getContext(), rateType).set(localDate).build());
        } else {
            builder = new ExchangeRateBuilder(ConversionContextBuilder.create(getContext(), rateType).build());
        }

        builder.setBase(BASE_CURRENCY);
        builder.setTerm(term);

        final BigDecimal kurOrani = kur.getKurOrani()
                                       .divide(BigDecimal.valueOf(kur.getKurBirimi().longValue()), 7, RoundingMode.HALF_EVEN);

        final NumberValue asilKurOrani = divide(DefaultNumberValue.ONE, DefaultNumberValue.of(kurOrani), MathContext.DECIMAL64);

        builder.setFactor(asilKurOrani);
        return builder.build();
    }

    private LocalDate[] getQueryDates(ConversionQuery query) {
        LocalDate date = query.get(LocalDate.class);
        if (date == null) {
            LocalDateTime dateTime = query.get(LocalDateTime.class);
            if (dateTime != null) {
                date = dateTime.toLocalDate();
            } else {
                date = LocalDate.now();
            }
        }

        // Tatil olma ihtimali nedeni ile 10 gün öncesine kadar tarih aralığı belirle
        final List<LocalDate> queryDates = new ArrayList<>();
        for (int day = 0; day < 10; day++) {
            queryDates.add(date.minus(Period.ofDays(day)));
        }

        return queryDates.toArray(new LocalDate[queryDates.size()]);
    }

    private ExchangeRate createExchangeRate(ConversionQuery query,
                                            ExchangeRateBuilder builder, ExchangeRate sourceRate,
                                            ExchangeRate target) {

        if (areBothBaseCurrencies(query)) {
            builder.setFactor(DefaultNumberValue.ONE);
            return builder.build();
        } else if (BASE_CURRENCY_CODE.equals(query.getCurrency().getCurrencyCode())) {
            if (Objects.isNull(sourceRate)) {
                return null;
            }
            return reverse(sourceRate);
        } else if (BASE_CURRENCY_CODE.equals(query.getBaseCurrency().getCurrencyCode())) {
            return target;
        } else {
            // Get Conversion base as derived rate: base -> TRY -> term
            ExchangeRate rate1 = getExchangeRate(query.toBuilder().setTermCurrency(Monetary.getCurrency(BASE_CURRENCY_CODE)).build());
            ExchangeRate rate2 = getExchangeRate(query.toBuilder()
                                                      .setBaseCurrency(Monetary.getCurrency(BASE_CURRENCY_CODE))
                                                      .setTermCurrency(query.getCurrency())
                                                      .build());
            if (Objects.nonNull(rate1) && Objects.nonNull(rate2)) {
                builder.setFactor(multiply(rate1.getFactor(), rate2.getFactor()));
                builder.setRateChain(rate1, rate2);
                return builder.build();
            }
            throw new CurrencyConversionException(
                    query.getBaseCurrency(),
                    query.getCurrency(), sourceRate.getContext()
            );
        }
    }

    private boolean areBothBaseCurrencies(ConversionQuery query) {
        return BASE_CURRENCY_CODE.equals(query.getBaseCurrency().getCurrencyCode()) &&
                BASE_CURRENCY_CODE.equals(query.getCurrency().getCurrencyCode());
    }

    private ExchangeRateBuilder getBuilder(ConversionQuery query, LocalDate localDate) {
        ExchangeRateBuilder builder = new ExchangeRateBuilder(
                ConversionContextBuilder.create(getContext(), RateType.HISTORIC).set(localDate).build()
        );
        builder.setBase(query.getBaseCurrency());
        builder.setTerm(query.getCurrency());
        return builder;
    }

    private ExchangeRate reverse(ExchangeRate rate) {
        if (Objects.isNull(rate)) {
            throw new IllegalArgumentException("Rate null is not reversible.");
        }
        return new ExchangeRateBuilder(rate)
                .setRate(rate).setBase(rate.getCurrency()).setTerm(rate.getBaseCurrency())
                .setFactor(divide(DefaultNumberValue.ONE, rate.getFactor(), MathContext.DECIMAL64)).build();
    }
}
