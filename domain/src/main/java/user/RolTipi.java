package user;

import tr.com.innova.sample.common.utils.Displayable;

/**
 * Created by tozyurek on 15.04.2015.
 */
public enum RolTipi implements Displayable {
    BURO("Büro"),
    MUVEKKIL("Müvekkil"),
    SISTEM_ADMIN("Sistem");

    private final String label;

    RolTipi(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public static RolTipi findRolTipi(Kullanici kullanici) {
        return SISTEM_ADMIN;
    }
}
