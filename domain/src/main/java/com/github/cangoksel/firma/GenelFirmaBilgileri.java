package com.github.cangoksel.firma;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import com.github.cangoksel.common.validation.constraints.Telefon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenelFirmaBilgileri extends AbstractVersionedEntity {
    @Column
    String firmaUnvan;
    @Column
    @Enumerated(EnumType.STRING)
    Il il;
    @Column
    @Enumerated(EnumType.STRING)
    Ulke ulke;
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
    private boolean saglikSektorundeMi;
    @Column
    String sektorBilgisi;
    @OneToOne
    @JoinColumn(name = "FAALİYET_KODU_ID", foreignKey = @ForeignKey(name = "FK_FIRMA_FAALİYET_KODU"))
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

    @OneToOne
    @JoinColumn(name = "ADRES_ID", foreignKey = @ForeignKey(name = "FK_FIRMA_ADRES"))
    @AuditJoinTable(name = "FIRMA_ADRES_L")
    private Adres adres;

    @Column
    @Telefon
    String telefon;
    @Column
    @Telefon
    String telefon2;
    @Column
    @Telefon
    String fax;
    @Column
    @Email
    String email;
    @Column
    String webAdresi;
}
