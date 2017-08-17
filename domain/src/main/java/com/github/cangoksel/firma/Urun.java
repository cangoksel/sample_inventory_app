package com.github.cangoksel.firma;

import com.github.cangoksel.belge.Belge;
import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Urun extends AbstractVersionedEntity {


    @OneToOne
    @JoinColumn(name = "URUN_ALT_KODU_ID", foreignKey = @ForeignKey(name = "FK_URUN_URUN_ALT__KODU"))
    @AuditJoinTable(name = "URUN_URUN_ALT_KODU_L")
    private UrunAltKodu urunAltKodu;

    @Column
    @Enumerated(EnumType.STRING)
    private UrunGrubu urunGrubu;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "URUN_ID", referencedColumnName = "ID", foreignKey = @ForeignKey(name = "FK_URUN_BELGE"))
    @AuditJoinTable(name = "URUN_BELGE_L")
    private Set<Belge> belgeler = new HashSet<>();

}
