package com.github.cangoksel.firma;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import com.github.cangoksel.common.validation.constraints.Telefon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.envers.AuditJoinTable;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;

@Entity
@Audited
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Kurulus extends AbstractVersionedEntity {
    @Column
    private String adi;

    @Column
    @Enumerated(EnumType.STRING)
    private FaaliyetAlani faaliyetAlani;

    @Column
    @Enumerated(EnumType.STRING)
    private UrunGrubu urunGrubu;


    @OneToOne
    @JoinColumn(name = "YETKILI_KISI_ID", foreignKey = @ForeignKey(name = "FK_KURULUS_YETKILI_KISI"))
    @AuditJoinTable(name = "KURULUS_YETKILI_KISI_L")
    private Kisi yetkiliKisi;

    @OneToOne
    @JoinColumn(name = "ADRES_ID", foreignKey = @ForeignKey(name = "FK_KURULUS_ADRES"))
    @AuditJoinTable(name = "KURULUS_ADRES_L")
    private Adres adres;

    @Column
    @Enumerated(EnumType.STRING)
    private Il bulunduguIl;

    @Column
    @Telefon
    private String telefon;

    @Column
    @Telefon
    private String telefon2;

    @Column
    @Telefon
    private String fax;

    @Column
    @Email
    private String email;

    @Column
    private String webSitesi;

    @Column
    private Long uyeSaisi;


}
