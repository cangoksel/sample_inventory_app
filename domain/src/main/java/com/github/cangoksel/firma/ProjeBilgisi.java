package com.github.cangoksel.firma;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;

@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Slf4j
public class ProjeBilgisi extends AbstractVersionedEntity {

    @Column
    private String konusu;

    @OneToOne
    @JoinColumn(name = "KURUM_ID", foreignKey = @ForeignKey(name = "FK_PROJE_BILGILERI_KURUM"))
    @AuditJoinTable(name = "PROJE_BILGILERI_KURUM_L")
    private Kurum destekVerenKurum;
}
