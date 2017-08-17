package com.github.cangoksel.firma;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UrunKodu extends AbstractVersionedEntity {


    private String urunKoduAdi;

    private String urunKodu;

    @OneToOne
    @JoinColumn(name = "URUN_GRUBU_KODU_ID", foreignKey = @ForeignKey(name = "FK_URUN_KODU_URUN_GRUBU_KODU"))
    @AuditJoinTable(name = "URUN_KODU_URUN_GRUBU_KODU_L")
    private UrunGrubuKodu urunGrubuKodu;
}
