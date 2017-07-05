package com.github.cangoksel.common.log;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * Created by mersoy on 8.03.2017.
 */
@Document(collection = "inboundLogs")
public class InboundLogs {
    @Id
    private BigInteger id;
    private LocalDateTime tarih;
    private String adres;
    private String mesaj;

    public InboundLogs(LocalDateTime tarih, String adres, String mesaj) {
        this.tarih = tarih;
        this.adres = adres;
        this.mesaj = mesaj;
    }
}