package com.github.cangoksel.firma;

import com.github.cangoksel.common.utils.Displayable;

public enum UrunGrubu implements Displayable {
    TIBBICIHAZ("Tıbbi Cihaz"),
    ILAC("İlaç");

    private final String label;

    private UrunGrubu(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return null;
    }
}
