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
public class UrunAltKodu extends AbstractVersionedEntity {

    @Column
    private String urunAltKoduAdi;

    @Column
    private  String urunAltKod;

    @OneToOne
    @JoinColumn(name = "URUN_KODU_ID", foreignKey = @ForeignKey(name = "FK_URUN_ALT_KODU_URUN_KODU"))
    @AuditJoinTable(name = "URUN_ALT_KODU_URUN_KODU_L")
    private UrunKodu urunKodu;
}
