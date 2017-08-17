package com.github.cangoksel.firma;

import com.github.cangoksel.FaaliyetAlani;
import com.github.cangoksel.FaaliyetKodu;
import com.github.cangoksel.Kisi;
import com.github.cangoksel.UrunGrubu;
import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import com.github.cangoksel.common.validation.constraints.Telefon;
import com.github.cangoksel.il.Il;
import com.github.cangoksel.user.Kullanici;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Firma extends AbstractVersionedEntity {
    @Column
    String unvan;
    @Column
    @Enumerated(EnumType.STRING)
    Il il;
    @Column
    LocalDate kurulusTarihi;
    @OneToOne
    @JoinColumn(name = "KAYDI_OLUSTURAN_KISI_ID", foreignKey = @ForeignKey(name = "FK_FIRMA_KAYDI_OLUSTURAN_KISI"))
    @AuditJoinTable(name = "FIRMA_KAYDI_OLUSTURAN_KISI_L")
    Kisi kaydiOlusturanKisi;
    @OneToOne
    @JoinColumn(name = "YETKILI_KISI_ID", foreignKey = @ForeignKey(name = "FK_FIRMA_YETKILI_KISI"))
    @AuditJoinTable(name = "YETKILI_KISI_L")
    Kisi yetkiliKisi;
    @Column
    @Enumerated(EnumType.STRING)
    FaaliyetAlani faaliyetAlani;
    @Column
    @Enumerated(EnumType.STRING)
    UrunGrubu urunGrubu;
    @Column
    String sektorBilgisi;
    @OneToOne
    @JoinColumn(name = "FAALİYET_KODU_ID", foreignKey = @ForeignKey(name = "FK_FIRMA_FAALİYET_KODU_"))
    @AuditJoinTable(name = "FIRMA_FAALİYET_KODU_L")
    FaaliyetKodu faaliyetKodu;
    @Column
    boolean argeBirimiVarMi;
    @Column
    String ticaretSicilNo;
    @Column
    String vergiNo;
    @Column
    String acikAdress;
    @Column
    @Telefon
    String telefon;
    @Column
    @Telefon
    String fax;
    @Column
    @Email
    String email;
    @Column
    String webAdresi;


}
