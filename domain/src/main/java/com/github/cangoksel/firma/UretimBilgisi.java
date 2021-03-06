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
public class UretimBilgisi extends AbstractVersionedEntity {

    @OneToOne
    @JoinColumn(name = "URUN_ID", foreignKey = @ForeignKey(name = "FK_URETIM_BILGILERI_URUN_KODU"))
    @AuditJoinTable(name = "URETIM_BILGILERI_URUN_L")
    private Urun urun;

    @Column
    private String firmaAciklamasi;
}
