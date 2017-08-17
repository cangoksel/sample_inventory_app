package com.github.cangoksel.firma;

import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

public class ProjeBilgisi {

    @Column
    private String konusu;

    @OneToOne
    @JoinColumn(name = "KURUM_ID", foreignKey = @ForeignKey(name = "FK_PROJE_BILGILERI_KURUM"))
    @AuditJoinTable(name = "PROJE_BILGILERI_KURUM_L")
    private Kurum destekVerenKurum;
}
