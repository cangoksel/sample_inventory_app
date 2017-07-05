package common.money;

import org.javamoney.moneta.Money;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.*;
import javax.money.format.MonetaryAmountFormat;
import java.time.LocalDate;

/**
 * Created by herdemir on 09.07.2015.
 */

public final class Moneys {

    public static final String DEFAULT_CURRENCY_CODE = "TRY";
    public static final String DEFAULT_EXCHAGE_RATE_PROVIDER_NAME = "TCMB";
    public static final Money ZERO = Moneys.of(0);
    public static final Money ONE = Moneys.of(1);

    private static ExchangeRateProvider exchangeRateProvider;

    private Moneys() {
    }

    @SuppressWarnings("unchecked")
    public static <T extends MonetaryAmount> T of(final Number number, CurrencyUnit currencyUnit) {
        return (T) Monetary.getDefaultAmountFactory().setNumber(number).setCurrency(currencyUnit).create();
    }

    public static <T extends MonetaryAmount> T of(final Number number, String currencyCode) {
        return of(number, Monetary.getCurrency(currencyCode));
    }

    public static <T extends MonetaryAmount> T of(final Money money) {
        return of(money.getNumber(), money.getCurrency());
    }

    public static <T extends MonetaryAmount> T of(final Number number, ParaBirimi paraBirimi) {
        return of(number, paraBirimi.name());
    }

    public static <T extends MonetaryAmount> T of(final Number number) {
        return of(number, DEFAULT_CURRENCY_CODE);
    }

    @SuppressWarnings("unchecked")
    public static <T extends MonetaryAmount> T exchangeTo(T baseAmount, CurrencyUnit currency, LocalDate date) {
        final ConversionQuery conversionQuery = ConversionQueryBuilder.of()
                .setTermCurrency(currency)
                .set(LocalDate.class, date).build();

        final CurrencyConversion currencyConversion = getExchangeRateProvider().getCurrencyConversion(conversionQuery);

        return (T) currencyConversion.apply(baseAmount);
    }

    public static <T extends MonetaryAmount> T exchangeTo(T baseAmount, String termCode, LocalDate date) {
        return exchangeTo(baseAmount, Monetary.getCurrency(termCode), date);
    }

    public static <T extends MonetaryAmount> T exchangeTo(T baseAmount, ParaBirimi paraBirimi, LocalDate date) {
        return exchangeTo(baseAmount, paraBirimi.name(), date);
    }

    public static <T extends MonetaryAmount> T exchangeTo(T baseAmount, CurrencyUnit currency) {
        return exchangeTo(baseAmount, currency, LocalDate.now());
    }

    public static <T extends MonetaryAmount> T exchangeTo(T baseAmount, String termCode) {
        return exchangeTo(baseAmount, Monetary.getCurrency(termCode), LocalDate.now());
    }

    public static <T extends MonetaryAmount> T exchangeTo(T baseAmount, ParaBirimi paraBirimi) {
        return exchangeTo(baseAmount, paraBirimi.name(), LocalDate.now());
    }

    public static <T extends MonetaryAmount> T exchangeToDefault(T baseAmount) {
        return exchangeTo(baseAmount, DEFAULT_CURRENCY_CODE, LocalDate.now());
    }

    public static <T extends MonetaryAmount> T exchangeToDefault(T baseAmount, LocalDate date) {
        return exchangeTo(baseAmount, DEFAULT_CURRENCY_CODE, date);
    }

    @SuppressWarnings("unchecked")
    public static <T extends MonetaryAmount> T parse(CharSequence text) {
        return (T) Money.parse(text);
    }

    @SuppressWarnings("unchecked")
    public static <T extends MonetaryAmount> T parse(CharSequence text, MonetaryAmountFormat formatter) {
        return (T) Money.parse(text, formatter);
    }

    private static ExchangeRateProvider getExchangeRateProvider() {
        if (exchangeRateProvider == null) {
            exchangeRateProvider = MonetaryConversions.getExchangeRateProvider(DEFAULT_EXCHAGE_RATE_PROVIDER_NAME);
        }

        return exchangeRateProvider;
    }
}
