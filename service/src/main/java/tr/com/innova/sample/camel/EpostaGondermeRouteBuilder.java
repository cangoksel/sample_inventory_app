package tr.com.innova.sample.camel;

import org.apache.camel.builder.DeadLetterChannelBuilder;
import org.apache.camel.builder.DefaultErrorHandlerBuilder;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tr.com.innova.sample.mail.EPostaService;

/**
 * Created by usuicmez on 17.02.2016.
 */
@Component
public class EpostaGondermeRouteBuilder extends SpringRouteBuilder {
    @Autowired
    private EPostaService ePostaService;

    @Override
    public void configure() throws Exception {

        DefaultErrorHandlerBuilder epostaGondermeErrorHandler = new DeadLetterChannelBuilder("seda:epostaGondermeError")
            .asyncDelayedRedelivery()
            .redeliveryDelay(1000)
            .maximumRedeliveries(3);

        from("quartz2://sample/epostaGonderme?cron=0+0/5+*+*+*+?")
            .to("direct:epostaGondermeRoute");

        from("direct:epostaGondermeRoute")
            .errorHandler(epostaGondermeErrorHandler)
            .bean(ePostaService, "epostaIleGitmesiGerekenHatirlatmalariGonder");


        from("seda:epostaGonderRoute")
            .errorHandler(epostaGondermeErrorHandler)
            .bean(ePostaService, "epostaGonder");

        from("seda:epostaGondermeError")
            .errorHandler(epostaGondermeErrorHandler)
            .bean(ePostaService, "epostaGondermeErrorHandle");
    }
}
