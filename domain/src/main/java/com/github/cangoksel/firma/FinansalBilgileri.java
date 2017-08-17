package com.github.cangoksel.firma;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FinansalBilgileri extends AbstractVersionedEntity {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "YILLIK_CIRO_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_FINANSAL_BILGILER_YILLIK_CIRO"))
    @Audited(targetAuditMode = RelationTargetAuditMode.AUDITED)
    @AuditJoinTable(name = "FINANSAL_BILGILER_YILLIK_CIRO_L")
    private Set<YillikCiro> yillikCiroList;

    @Column
    private boolean ihracatVarMi;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "IHRACAT_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_FINANSAL_BILGILER_IHRACAT"))
    @Audited(targetAuditMode = RelationTargetAuditMode.AUDITED)
    @AuditJoinTable(name = "FINANSAL_BILGILER_IHRACAT_L")
    private Set<Ihracat> ihracatList;
}
