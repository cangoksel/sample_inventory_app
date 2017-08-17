package com.github.cangoksel.il;

import com.github.cangoksel.common.utils.Displayable;

public enum Bolge implements Displayable {
    AKDENIZ("Akdeniz"),
    DOGUANADOLU("Doğu Anadolu"),
    EGE("Ege"),
    GUNEYDOGUANADOLU("GüneyDoğu Anadolu"),
    ICANADOLU("İç Anadolu"),
    KARADENIZ("Karadeniz"),
    MARMARA("Marmara");

    private final String label;

    private Bolge(String label){
        this.label = label;
    }
    @Override
    public String getLabel() {
        return label;
    }
}
