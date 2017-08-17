package com.github.cangoksel.firma;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Set;

@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Firma extends AbstractVersionedEntity {

    @Column
    private boolean firmaYetkinlikCalismasiYepildiMi;

    @OneToOne
    @JoinColumn(name = "GENEL_FIRMA_BILGILER_ID", foreignKey = @ForeignKey(name = "FK_FIRMA_GENEL_FIRMA_BILGILER"))
    @AuditJoinTable(name = "FIRMA_GENEL_FIRMA_BILGILER_L")
    private GenelFirmaBilgileri genelBilgiler;

    @OneToOne
    @JoinColumn(name = "ISYERI_BILGILERI_ID", foreignKey = @ForeignKey(name = "FK_FIRMA_FIRMA_ISYERI_BILGILERI"))
    @AuditJoinTable(name = "FIRMA_ISYERI_BILGILERI_L")
    private IsyeriBilgileri isyeriBilgileri;

    @OneToOne
    @JoinColumn(name = "ORTAKLIK_BILGILERI_ID", foreignKey = @ForeignKey(name = "FK_FIRMA_ORTAKLIK_BILGILERI"))
    @AuditJoinTable(name = "FIRMA_ORTAKLIK_BILGILERI_L")
    private OrtaklikBilgileri ortaklikBilgileri;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "PROJE_BILGISI_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_FIRMA_PROJE_BILGISI"))
    @AuditJoinTable(name = "FIRMA_PROJE_BILGISI_L")
    private Set<ProjeBilgisi> projeBilgileri;

    @OneToOne
    @JoinColumn(name = "FINANSAL_BILGILERI_ID", foreignKey = @ForeignKey(name = "FK_FIRMA_FINANSAL_BILGILERI"))
    @AuditJoinTable(name = "FIRMA_FINANSAL_BILGILERI_L")
    private FinansalBilgileri finansalBilgileri;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "TESIS_BILGISI_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_FIRMA_TESIS_BILGISI"))
    @AuditJoinTable(name = "FIRMA_TESIS_BILGISI_L")
    private Set<TesisBilgisi> tesisBilgisileri;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "URETIM_BILGISI_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_FIRMA_URETIM_BILGISI"))
    @AuditJoinTable(name = "FIRMA_URETIM_BILGISI_L")
    private Set<UretimBilgisi> uretimBilgileri;
}
