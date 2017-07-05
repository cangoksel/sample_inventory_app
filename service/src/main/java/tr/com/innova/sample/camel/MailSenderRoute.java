package tr.com.innova.sample.camel;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by herdemir on 27.01.2016.
 */
@Component
public class MailSenderRoute extends SpringRouteBuilder {
    @Value("${smtp.host}")
    private String mailHost;

    @Override
    public void configure() throws Exception {
        final String extraProperties;

        if (mailHost != null && mailHost.contains("gmail")) {
            extraProperties = "&mail.smtp.auth=true&mail.smtp.starttls.enable=true";
        } else {
            extraProperties = "";
        }

        from("seda:mailSender")
                .to("smtp://{{smtp.host}}?username={{smtp.username}}&password={{smtp.password}}&contentType=text/html;charset=UTF-8" + extraProperties);//NOSONAR
    }
}
