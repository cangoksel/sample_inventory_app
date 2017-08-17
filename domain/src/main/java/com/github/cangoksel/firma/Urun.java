package com.github.cangoksel.firma;

import com.github.cangoksel.belge.Belge;
import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Urun extends AbstractVersionedEntity {


    @OneToOne
    @JoinColumn(name = "URUN_ALT_KODU_ID", foreignKey = @ForeignKey(name = "FK_URUN_URUN_ALT_KODU"))
    @AuditJoinTable(name = "URUN_URUN_ALT_KODU_L")
    private UrunAltKodu urunAltKodu;
    @OneToOne
    @JoinColumn(name = "URUN_PRODKOM_KODU_ID", foreignKey = @ForeignKey(name = "FK_URUN_PRODKOM_KODU"))
    @AuditJoinTable(name = "URUN_PRODKOM_KODU_L")
    private ProdkomKodu prodkomKodu;
    @OneToOne
    @JoinColumn(name = "URUN_GTIP_KODU_ID", foreignKey = @ForeignKey(name = "FK_URUN_GTIP_KODU"))
    @AuditJoinTable(name = "URUN_GTIP_KODU_L")
    private GtipKodu gtipKodu;
    @OneToOne
    @JoinColumn(name = "URUN_MKYS_KODU_ID", foreignKey = @ForeignKey(name = "FK_URUN_MKYS_KODU"))
    @AuditJoinTable(name = "URUN_MKYS_KODU_L")
    private MkysKodu mkysKodu;
    @OneToOne
    @JoinColumn(name = "URUN_MEDIKAL_TUR_KODU_ID", foreignKey = @ForeignKey(name = "FK_URUN_MEDIKAL_TUR_KODU"))
    @AuditJoinTable(name = "URUN_MEDIKAL_TUR_KODU_L")
    private MedikalTurKodu medikalTurKodu;
    @OneToOne
    @JoinColumn(name = "URUN_NACE_KODU_ID", foreignKey = @ForeignKey(name = "FK_URUN_NACE_KODU"))
    @AuditJoinTable(name = "URUN_NACE_KODU_L")
    private NaceKodu naceKodu;
    @OneToOne
    @JoinColumn(name = "URUN_ACT_KODU_ID", foreignKey = @ForeignKey(name = "FK_URUN_ACT_KODU"))
    @AuditJoinTable(name = "URUN_ACT_KODU_L")
    private ActKodu actKodu;
    @OneToOne
    @JoinColumn(name = "URUN_TIBBI_CIHAZ_TEHLIKE_SINIFI_ID", foreignKey = @ForeignKey(name = "FK_URUN_TIBBI_CIHAZ_TEHLIKE_SINIFI"))
    @AuditJoinTable(name = "URUN_TIBBI_CIHAZ_TEHLIKE_SINIFI_L")
    private TibbiCihazTehlikeSinifi tibbiCihazTehlikeSinifi;
    @Column
    private String tanimi;
    @Column
    private String kullanimAlanlari;
    @Column
    private String cesitleri;
    @Column
    private String endikasyonlari;
    @Column
    private String formlari;

    @Column
    @Enumerated(EnumType.STRING)
    private UrunGrubu urunGrubu;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "URUN_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_URUN_BELGE"))
    @AuditJoinTable(name = "URUN_BELGE_L")
    private Set<Belge> belgeler = new HashSet<>();


}
