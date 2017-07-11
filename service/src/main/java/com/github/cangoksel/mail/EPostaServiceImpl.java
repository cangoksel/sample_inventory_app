package com.github.cangoksel.mail;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * Created by gcan on 30.03.2017.
 */
@Service
@Transactional
public class EPostaServiceImpl implements EPostaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EPostaServiceImpl.class);

    @Autowired
    private MailService mailService;
    @Autowired
    private CamelContext camelContext;

    public void epostaIleGitmesiGerekenHatirlatmalariGonder() {
        Eposta eposta = new Eposta(UUID.randomUUID(), "a@a.com", "konu", "metin");
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("seda:epostaGonderRoute", eposta);
    }

    public void epostaGonder(Eposta eposta) {
        mailService.sendMail(eposta.getTo(), eposta.getSubject(), eposta.getText());
    }

    public void epostaGondermeErrorHandle(Exception ex) {
        StringBuilder exMessage = new StringBuilder();
        if (ex instanceof EpostaGondermeException) {
            Eposta eposta = ((EpostaGondermeException) ex).getEposta();
            if (eposta != null) {
                exMessage.append(eposta.getId());
                exMessage.append(" id'li ");
                exMessage.append(eposta.getSubject());
                exMessage.append(" hata aldı. Hata mesajı: ");
            }
        }
        exMessage.append(ex.getMessage());
        LOGGER.error(exMessage.toString(), ex);
    }
}
