package com.github.cangoksel.belge;


import com.github.cangoksel.common.utils.Displayable;

/**
 * Created by herdemir on 10.06.2015.
 */
public enum BelgeTipi implements Displayable {
    CE("CE"),
    TSE("TSE"),
    ISO9001("ISO9001"),
    ECE("ECE"),
    BELGE("Belge"),
    URUN_RAPORU("Ürün Raporu"),
    FIZIBILITE_RAPORU("Fizibilite Raporu"),
    TEKNOLOJI_MATRISI("Teknoloji Matrisi"),
    URUN_TEKNOLOJISI("Ürün Teknolojisi"),
    FINANSAL_ANALIZ("Finansal Analiz");

    private final String label;

    BelgeTipi(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
