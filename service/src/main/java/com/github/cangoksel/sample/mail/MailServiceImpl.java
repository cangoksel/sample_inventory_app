package com.github.cangoksel.sample.mail;

import com.github.cangoksel.common.exceptions.DomainException;
import com.github.cangoksel.mail.MailService;
import org.apache.camel.CamelContext;
import org.apache.camel.CamelExecutionException;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by herdemir on 30.06.2015.
 */
@Service
@Transactional
public class MailServiceImpl implements MailService {
    public static final String MAIL_GONDERILEMEDI = "Mail gönderilemedi.";

    private static final String MAIL_SENDER_URI = "seda:mailSender";

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private CamelContext camelContext;

    @Value("${smtp.from}")
    private String mailFrom;

    private ProducerTemplate producerTemplate;

    @PostConstruct
    private void init() {
        producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.setDefaultEndpointUri(MAIL_SENDER_URI);
    }

    @Override
    public void sendMail(final String to, final String subject, final String text) {
        final Map<String, Object> map = createHeaders(mailFrom, to, subject);

        try {
            producerTemplate.sendBodyAndHeaders(text, map);
        } catch (CamelExecutionException ex) {
            throw new DomainException(MAIL_GONDERILEMEDI, ex);
        }
    }

    private Map<String, Object> createHeaders(String from, String to, String subject) {
        final Map<String, Object> map = new HashMap<>();
        map.put("To", to);
        map.put("From", from);
        map.put("Subject", subject);
        return map;
    }
}
