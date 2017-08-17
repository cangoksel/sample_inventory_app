package com.github.cangoksel.firma;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;
import org.javamoney.moneta.Money;

import javax.persistence.*;
import java.lang.reflect.Modifier;
import java.time.LocalDate;

@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SayisalVeri extends AbstractVersionedEntity {

    @OneToOne
    @JoinColumn(name = "URUN_ID", foreignKey = @ForeignKey(name = "FK_SAYISAL_VERI_URUN"))
    @AuditJoinTable(name = "SAYISAL_VERI_URUN_L")
    private Urun urun;
    @Column
    private LocalDate yil;
    @Column
    private Long ithalatMiktari;
    @Column
    private Long ihtiyacMiktari;
    @Columns(columns = {@Column(name = "BIRIM_FIYAT_TUTAR"), @Column(name = "BIRIM_FIYAT_TUTAR_PB", length = 3)})
    private Money birimFiyat;
    @Columns(columns = {@Column(name = "DUNYA_PAZARI_TUTAR"), @Column(name = "DUNYA_PAZARI_TUTAR_PB", length = 3)})
    private Money dunyaPazari;
    @Columns(columns = {@Column(name = "TURKIYE_PAZARI_TUTAR"), @Column(name = "TURKIYE_PAZARI_TUTAR_PB", length = 3)})
    private Money turkiyePazari;
}
