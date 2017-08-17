package com.github.cangoksel.firma;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UretimBilgisi extends AbstractVersionedEntity {

    @OneToOne
    @JoinColumn(name = "URUN_ALT_KODU_ID", foreignKey = @ForeignKey(name = "FK_URETIM_BILGILERI_URUN_ALT__KODU"))
    @AuditJoinTable(name = "URETIM_BILGILERI_URUN_ALT_KODU_L")
    private UrunAltKodu urunAltKodu;

    @Column
    private String firmaAciklamasi;
}
