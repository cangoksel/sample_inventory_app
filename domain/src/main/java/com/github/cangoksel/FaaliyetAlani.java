package com.github.cangoksel;

import com.github.cangoksel.common.utils.Displayable;

public enum FaaliyetAlani implements Displayable {
    ARGE("Arge"),
    YAZILIM("Yazılım"),
    HIZMET("Hizmet");

    private String label;

    private FaaliyetAlani(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
