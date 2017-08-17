package com.github.cangoksel.firma;

import com.github.cangoksel.common.utils.Displayable;

public enum CalisanTipi implements Displayable {
    DOKTORA("Doktora"),
    YUKSEK_LISANS("Yüksek Lisans"),
    LISANS("Lisans"),
    UZMAN("Uzman"),
    ISCI("İşçi"),
    MYO("MYO"),
    IDARI("İdari"),
    TOPLAM("Toplam");

    private final String label;

    private CalisanTipi(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
