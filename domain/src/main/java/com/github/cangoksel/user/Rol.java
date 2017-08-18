package com.github.cangoksel.user;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.*;

/**
 * Created by herdemir on 04.02.2015.
 */
@Entity
@Table(name = "ROL", uniqueConstraints = {@UniqueConstraint(columnNames = "KOD", name = "UK_ROL_KOD")})
@NamedEntityGraphs({
    @NamedEntityGraph(name = "Rol.withYetkiler", attributeNodes = {@NamedAttributeNode("yetkiler")})
})
@Audited
@Cacheable
@Getter
@Slf4j
public class Rol extends AbstractVersionedEntity {

    public static final String ROLE_BURO = "ROLE_BURO";
    public static final String ROLE_BURO_ADMIN = "ROLE_BURO_ADMIN";
    public static final String ROLE_MUVEKKIL = "ROLE_MUVEKKIL";
    public static final String ROLE_MUVEKKIL_ADMIN = "ROLE_MUVEKKIL_ADMIN";
    public static final String ROLE_SISTEM_ADMIN = "ROLE_SISTEM_ADMIN";
    public static final String ROLE_DESTEK = "ROLE_DESTEK_USER";
    public static final String ROLE_SATIS = "ROLE_SATIS_USER";
    public static final String ROLE_FINANS = "ROLE_FINANS_USER";
    public static final String ROLE_USER = "ROLE_USER";

    @Column(name = "KOD")
    private String kod;
    @Column(name = "IS_SISTEM_ROLU")
    private Boolean isSistemRolu;
    @Column(name = "ACIKLAMA")
    private String aciklama;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROL_TIPI")
    private RolTipi rolTipi;



    @ManyToMany
    @JoinTable(
        name = "ROL_YETKI",
        joinColumns = {@JoinColumn(name = "ROL_ID", foreignKey = @ForeignKey(name = "FK_ROL_YETKI_ROL"))},
        inverseJoinColumns = {@JoinColumn(name = "YETKI_ID", foreignKey = @ForeignKey(name = "FK_ROL_YETKI_YETKI"))}
    )
    private Set<Yetki> yetkiler = new HashSet<>();

    protected Rol() {
    }

    public Rol(String kod, Boolean isSistemRolu, String aciklama,
        Yetki... yetkiler) {
        this.kod = kod;
        this.isSistemRolu = isSistemRolu;
        this.aciklama = aciklama;
        if (yetkiler != null && yetkiler.length > 0) {
            this.yetkiler = Sets.newHashSet(yetkiler);
        }
    }

    public Rol(RolTipi rolTipi, String kod, Boolean isSistemRolu, String aciklama, List<Yetki> yetkiList) {
        this.rolTipi = rolTipi;
        this.kod = kod;
        this.isSistemRolu = isSistemRolu;
        this.aciklama = aciklama;
        if (yetkiList != null && yetkiList.size() > 0) {
            this.yetkiler = Sets.newHashSet(yetkiList);
        }
    }

    public Rol(final String kod) {
        this.kod = kod;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public Set<Yetki> getYetkiler() {
        return ImmutableSet.copyOf(yetkiler);
    }

    public String getAciklama() {
        return aciklama;
    }

    public Boolean isSistemRolu() {
        return isSistemRolu;
    }

    public RolTipi getRolTipi() {
        return rolTipi;
    }


    public void update(String aciklama, List<Yetki> yetkiList) {
        this.aciklama = aciklama;
        update(yetkiList);
    }

    public void update(Collection<Yetki> yetkiList) {
        yetkiler.retainAll(yetkiList);
        yetkiler.addAll(yetkiList);
    }

    public static String randomRolKoduOlustur(RolTipi rolTipi) {
        return "ROLE_" + rolTipi.getLabel() + "_" + UUID.randomUUID();
    }
}
