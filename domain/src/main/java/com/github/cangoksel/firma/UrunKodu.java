package com.github.cangoksel.firma;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Slf4j
public class UrunKodu extends AbstractVersionedEntity {

    @Column
    private String urunKoduAdi;
    @Column
    private String urunKod;

    @OneToOne
    @JoinColumn(name = "URUN_GRUBU_KODU_ID", foreignKey = @ForeignKey(name = "FK_URUN_KODU_URUN_GRUBU_KODU"))
    @AuditJoinTable(name = "URUN_KODU_URUN_GRUBU_KODU_L")
    private UrunGrubuKodu urunGrubuKodu;
}
