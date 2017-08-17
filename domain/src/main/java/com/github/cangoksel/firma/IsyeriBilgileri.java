package com.github.cangoksel.firma;

import com.github.cangoksel.belge.Belge;
import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
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
public class IsyeriBilgileri extends AbstractVersionedEntity {

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "KURULUS_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_ISYERIBILGILERI_KURULUS"))
    @Audited(targetAuditMode = RelationTargetAuditMode.AUDITED)
    @AuditJoinTable(name = "ISYERIBILGILERI_KURULUS_L")
    private Set<Kurulus> kayitliOlduguKuruluslar;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CALISAN_SAYI_BILGISI_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_ISYERI_BILGILERI_CALISAN_SAYI_BILGISI"))
    @Audited(targetAuditMode = RelationTargetAuditMode.AUDITED)
    @AuditJoinTable(name = "ISYERI_BILGILERI_CALISAN_SAYI_BILGISI_L")
    private Set<CalisanSayiBilgisi> calisanBilgileri;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "BELGE_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_ISYERI_BILGILERI_BELGE"))
    @Audited(targetAuditMode = RelationTargetAuditMode.AUDITED)
    @AuditJoinTable(name = "ISYERI_BILGILERI_BELGE_L")
    private Set<Belge> kaliteBelgeleri;

}
