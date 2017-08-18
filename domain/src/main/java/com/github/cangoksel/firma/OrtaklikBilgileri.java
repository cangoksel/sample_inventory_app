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
import java.util.Set;

@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Slf4j
public class OrtaklikBilgileri extends AbstractVersionedEntity {

    @Column
    private  boolean isbirligiSunmaİstegiVarMi;

    @Column
    private  boolean isbirligiTalebiVarMi;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ISBIRLIGI_FIRMA_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_ORTAKLIK_BILGILER_ISBIRLIGI_FIRMA"))
    @Audited(targetAuditMode = RelationTargetAuditMode.AUDITED)
    @AuditJoinTable(name = "ORTAKLIK_BILGILER_ISBIRLIGI_FIRMA_L")
    private Set<IsbirligiFirma> isbirligiYapilanFirmaList; 
}
