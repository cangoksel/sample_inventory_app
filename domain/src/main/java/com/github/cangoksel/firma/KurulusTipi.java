package com.github.cangoksel.firma;

import com.github.cangoksel.common.utils.Displayable;

public enum KurulusTipi implements Displayable {
    SIVIL_TOPLUM_KURULUSU("Sivil Toplum Kuruluşu"),
    ODA("Oda"),
    TEKNOKENT_TEKNOPARK("Teknokent/Teknopark"),
    KUMELENME("Kümelenme");

    private final String label;

    private KurulusTipi(String label) {

        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
