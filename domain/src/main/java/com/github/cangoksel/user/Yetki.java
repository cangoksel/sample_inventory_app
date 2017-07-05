package com.github.cangoksel.user;

import com.github.cangoksel.common.entity.AbstractVersionedEntity;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Created by gcan on 16.03.2015.
 */
@Entity
@Table(name = "YETKI", uniqueConstraints = {@UniqueConstraint(columnNames = "KOD", name = "UK_YETKI_KOD")})
@Audited
public class Yetki extends AbstractVersionedEntity {

    @Column(name = "KOD")
    private String kod;
    @Column(name = "ACIKLAMA")
    private String aciklama;

    protected Yetki() {
    }

    public Yetki(String kod) {
        this.kod = kod;
    }

    public String getAciklama() {
        return aciklama;
    }

    public String getKod() {
        return kod;
    }
}
