package com.github.cangoksel;

import com.github.cangoksel.common.money.Kur;
import com.github.cangoksel.common.money.ParaBirimi;
import com.github.cangoksel.kur.ObjectFactory;
import com.github.cangoksel.kur.TarihDate;
import com.google.common.base.Strings;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.spring.SpringRouteBuilder;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlSeeAlso;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.lang.System.getProperty;
import static java.util.stream.Collectors.toList;

/**
 * Created by herdemir on 13.07.2015.
 */
@Component
@XmlSeeAlso({ObjectFactory.class})
public class TcmbKurRouteBuilder extends SpringRouteBuilder {

    public static Logger logger = LoggerFactory.logger(TcmbKurRouteBuilder.class);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final String URL = "http4://www.tcmb.gov.tr/kurlar/today.xml";
    public static final String DATED_URL = "http4://www.tcmb.gov.tr/kurlar/%1$s/%2$s.xml";

    public static final String SCHEMA_NAME = "schemas/tcmbKur.xsd";

    @Override
    public void configure() throws Exception {
        from("direct:tcmbKur")
            .errorHandler(loggingErrorHandler())
            .to(getUri())
            .unmarshal(jaxbFormat())
            .process(mapToKur())
            .to("jpa:java.util.List");

        from("direct:tcmbKurDated")
            .errorHandler(loggingErrorHandler())
            .process(datedKurUrl())
            .toD("${body}")
            .unmarshal(jaxbFormat())
            .process(mapToKur())
            .to("jpa:java.util.List");

        // TODO: [HE] Prod'da bu kalkacak
        from("quartz2://sample/tcmbKurNow?fireNow=true&trigger.repeatCount=0")
            .to("direct:tcmbKur");

        from("quartz2://sample/tcmbKur?cron=0+0+16+?+*+MON-FRI")
            .to("direct:tcmbKur");
    }

    private String getUriWithDate(LocalDate date) {
        String url = String.format(DATED_URL,date.format(DateTimeFormatter.ofPattern("yyyyMM")),date.format(DateTimeFormatter.ofPattern("ddMMyyyy")));
        final String proxyHost = getProperty("http.proxyHost", null);
        if (Strings.isNullOrEmpty(proxyHost)) {
            return url;
        }

        final StringBuilder sb = new StringBuilder(url);
        sb.append("?proxyAuthHost=").append(proxyHost);
        sb.append("&proxyAuthPort=").append(getProperty("http.proxyPort", null));
        final String proxyUser = getProperty("http.proxyUser", null);
        if (!Strings.isNullOrEmpty(proxyUser)) {
            sb.append("&proxyAuthUsername=").append(proxyUser);
            sb.append("&proxyAuthPassword=").append(getProperty("http.proxyPassword", null));
        }

        return sb.toString();
    }

    protected String getUri() {
        final String proxyHost = getProperty("http.proxyHost", null);
        if (Strings.isNullOrEmpty(proxyHost)) {
            return URL;
        }

        final StringBuilder sb = new StringBuilder(URL);
        sb.append("?proxyAuthHost=").append(proxyHost);
        sb.append("&proxyAuthPort=").append(getProperty("http.proxyPort", null));
        final String proxyUser = getProperty("http.proxyUser", null);
        if (!Strings.isNullOrEmpty(proxyUser)) {
            sb.append("&proxyAuthUsername=").append(proxyUser);
            sb.append("&proxyAuthPassword=").append(getProperty("http.proxyPassword", null));
        }

        return sb.toString();
    }

    protected JaxbDataFormat jaxbFormat() {
        JaxbDataFormat jaxb = new JaxbDataFormat();
        jaxb.setContextPath("com.github.cangoksel.tcmb.kur");
        //jaxb.setSchema("classpath:" + SCHEMA_NAME);
        return jaxb;
    }

    protected Processor mapToKur() {
        return new TarihDateToKurProcessor();
    }

    protected static class TarihDateToKurProcessor implements Processor {
        @Override
        public void process(Exchange exchange) throws Exception {
            final TarihDate tarihDate = (TarihDate) exchange.getIn().getBody();

            final List<Kur> kurList =
                tarihDate.getCurrency().stream()
                         .filter(f -> !"XDR".equals(f.getCurrencyCode()))
                         .map(
                             m ->{
                                 ParaBirimi paraBirimi;
                                 try {
                                     paraBirimi = ParaBirimi.valueOf(m.getCurrencyCode());
                                 } catch (IllegalArgumentException e){
                                     logger.log(Logger.Level.ERROR,e);
                                     return null;
                                 }
                                 BigDecimal forexSelling;
                                 try{
                                     forexSelling = m.getForexSelling();
                                 } catch (NumberFormatException e){
                                     logger.log(Logger.Level.ERROR,e);
                                     return null;
                                 }

                                 LocalDate kurTarihi = LocalDate.parse(tarihDate.getTarih(), DATE_TIME_FORMATTER);
                                 int kurBirimi = m.getUnit().intValue();
                                 if(kurTarihi.isBefore(LocalDate.parse("01.01.2005",DateTimeFormatter.ofPattern("dd.MM.yyyy")))){
                                     kurBirimi *= 1000000;
                                 }
                                 return new Kur(
                                     LocalDate.now(),
                                     paraBirimi,
                                     kurTarihi,
                                     forexSelling,
                                     kurBirimi
                                 );
                             }
                         )
                         .filter(m-> m!= null)
                         .collect(toList());
            exchange.getOut().setBody(kurList);
        }
    }

    protected Processor datedKurUrl() {
        return new Processor() {
            @Override
            public void process(Exchange exchange) throws Exception {
                final String dateString = (String) exchange.getIn().getBody();
                String uri = getUriWithDate(LocalDate.parse(dateString));
                exchange.getIn().setBody(uri);
            }
        };
    }
}
