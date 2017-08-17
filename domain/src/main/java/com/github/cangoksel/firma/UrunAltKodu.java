package com.github.cangoksel.firma;


import org.hibernate.envers.AuditJoinTable;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class UrunAltKodu {

    @Column
    private String urunAltKoduAdi;

    @Column
    private  String urunAltKodu;

    @OneToOne
    @JoinColumn(name = "URUN_KODU_ID", foreignKey = @ForeignKey(name = "FK_URUN_ALT_KODU_URUN_KODU"))
    @AuditJoinTable(name = "URUN_ALT_KODU_URUN_KODU_L")
    private UrunKodu urunKodu;
}
