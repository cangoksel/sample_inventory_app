package com.github.cangoksel.common.money;


import com.github.cangoksel.common.utils.Displayable;

/**
 * Created by herdemir on 14.07.2015.
 */
public enum ParaBirimi implements Displayable {
    TRY("TL", "Türk Lirası", "PRBRMTL", "TL - Türk Lirası"),
    USD("USD", "Amerikan Doları", "PRBRMUSD", "USD - Amerikan Doları"),
    EUR("EUR", "Euro", "PRBRMEUR", "EUR - Euro"),
    AUD("AUD", "Avusturalya Doları", "PRBRMAUD", "AUD - Avustralya Doları"),
    DKK("DKK", "Danimarka Kronu", "PRBRMDKK", "DKK - Danimarka Kronu"),
    GBP("GBP", "İngiliz Sterlini", "PRBRMMIST", "MIST - İngiliz Sterlini"),
    CHF("CHF", "İsviçre Frangı", "PRBRMCHF", "CHF - İsviçre Frangı"),
    SEK("SEK", "İsveç Kronu", "PRBRMSEK", "SEK - İsveç Kronu"),
    CAD("CAD", "Kanada Doları", "PRBRMCAD", "CAD - Kanada Doları"),
    KWD("KWD", "Kuveyt Dinarı", "PRBRMKWD", "KWD - Kuveyt Dinarı"),
    NOK("NOK", "Norveç Kronu", "PRBRMNOK", "NOK - Norveç Kronu"),
    SAR("SAR", "Suudi Arabistan Riyali", "PRBRMSAR", "SAR - Suudi Arabistan Riyali"),
    JPY("JPY", "Japon Yeni", "PRBRMJPY", "JPY - Japon Yeni"),
    BGN("BGN", "Bulgar Levası", "PRBRMBGN", "BGN - Bulgar Levası"),
    RON("RON", "Rumen Leyi", "PRBRMROL", "ROL - Rumen Leyi"),
    RUB("RUB", "Rus Rublesi", "PRBRMRUR", "RUR - Rus Rublesi"),
    IRR("IRR", "İran Riyali", "PRBRMIRR", "IRR - İran Riyali"),
    CNY("CNY", "Çin Yuanı", "PRBRMCNY", "CNY - Çin Yuanı"),
    PKR("PKR", "Pakistan Rupisi", "PRBRMIDR", "IDR - Pakistan Rupisi");

    private final String displayCode;
    private final String label;
    private final String uyapKodu;
    private final String uyapAciklama;

    ParaBirimi(String displayCode, String label, String uyapKodu, String uyapAciklama) {
        this.displayCode = displayCode;
        this.label = label;
        this.uyapKodu = uyapKodu;
        this.uyapAciklama = uyapAciklama;
    }

    public static ParaBirimi getParaBirimiByCode(String currencyCode) {
        for (ParaBirimi paraBirimi : ParaBirimi.values()) {
            if (paraBirimi.name().equals(currencyCode)) {
                return paraBirimi;
            }
        }
        return ParaBirimi.TRY;
    }

    public String getDisplayCode() {
        return displayCode;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public String getUyapKodu() {
        return uyapKodu;
    }

    public String getUyapAciklama() {
        return uyapAciklama;
    }
}
