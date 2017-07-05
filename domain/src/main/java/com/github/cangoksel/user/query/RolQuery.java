package com.github.cangoksel.user.query;

import com.github.cangoksel.common.utils.LocaleUtils;
import com.github.cangoksel.common.utils.Query;

import com.github.cangoksel.user.QRol;
import com.github.cangoksel.user.RolTipi;
import com.github.cangoksel.user.model.RolViewModel;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;


/**
 * Created by usuicmez on 14.04.2015.
 */
public class RolQuery implements Query {
    private final RolViewModel model;
    private static final QRol rol = QRol.rol;

    private final RolTipi kullaniciRolTipi;

    public RolQuery(RolViewModel model) {
        this.model = model;
        this.kullaniciRolTipi = RolTipi.findRolTipi(model.getKullanici());
    }

    private Predicate rolGrubu() {
        return this.kullaniciRolTipi == null ? rol.rolTipi.isNull() : rol.rolTipi.isNull().or(
            this.kullaniciRolTipi == RolTipi.SISTEM_ADMIN ? rol.rolTipi.eq(RolTipi.SISTEM_ADMIN).or(
                rol.rolTipi.notIn(RolTipi.SISTEM_ADMIN).and(rol.isSistemRolu.eq(true))
            ) : rol.rolTipi.eq(kullaniciRolTipi)
        );
    }



    private Predicate rolAdi() {
        if (model.getSorguKriteriRolAdi() != null) {
            return rol.aciklama.containsIgnoreCase(model.getSorguKriteriRolAdi().toLowerCase(LocaleUtils.TURKISH));
        }
        return null;
    }

    @Override
    public Predicate getQuery() {
        return new BooleanBuilder()
            .and(rolGrubu())
            .and(rolAdi())
            .getValue();
    }
}
