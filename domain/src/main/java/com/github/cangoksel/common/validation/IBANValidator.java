package com.github.cangoksel.common.validation;

import com.github.cangoksel.common.validation.constraints.IBAN;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by herdemir on 24.04.2015.
 */
public class IBANValidator implements ConstraintValidator<IBAN, String> {
    public static final BigInteger MOD97 = BigInteger.valueOf(97L);

    private static Map<String, String> ulkeKodlari;

    static {
        ulkeKodlari = ulkeKontrolKodlariniUret();
    }

    private static ImmutableMap<String, String> ulkeKontrolKodlariniUret() {
        final Map<String, String> kodlar = new HashMap<>();
        final char a = "A".charAt(0);
        final char z = "Z".charAt(0);
        for (int currentChar = a; currentChar <= z; currentChar++) {
            final String key = String.valueOf((char) currentChar);
            final String value = String.valueOf(currentChar - a + 10);
            kodlar.put(key, value);
        }

        return ImmutableMap.copyOf(kodlar);
    }

    @Override
    public void initialize(IBAN iban) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (Strings.isNullOrEmpty(value)) {
            return true;
        }
        if(value.contains("_")){
            return false;
        }

        final String iban = value.replace(" ", "").toUpperCase(Locale.ENGLISH);

        if (iban.startsWith("TR") && iban.length() != 26) {
            return false;
        }

        final String duzenlenmisIban = iban.substring(4) + iban.substring(0, 4);
        final String donusturulmusIban = convertHarfToUlkeKodu(duzenlenmisIban);
        final BigInteger bolunen = new BigInteger(donusturulmusIban);
        final BigInteger kalan = bolunen.mod(MOD97);

        return BigInteger.ONE.equals(kalan);
    }

    private String convertHarfToUlkeKodu(String donusturulmusIban) {
        for (Map.Entry<String, String> ulkeKodu : ulkeKodlari.entrySet()) {
            donusturulmusIban = donusturulmusIban.replace(ulkeKodu.getKey(), ulkeKodu.getValue());
        }
        return donusturulmusIban;
    }
}
