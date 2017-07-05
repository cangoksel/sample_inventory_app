package com.github.cangoksel.common.validation;

import com.github.cangoksel.common.validation.constraints.Telefon;
import com.google.common.base.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by herdemir on 24.04.2015.
 */
public class TelefonValidator implements ConstraintValidator<Telefon, String> {
    private static final Pattern telefon = Pattern.compile("([+]?90|0?)\\s*[234]\\d{2}\\s*\\d{3}\\s*\\d{2}\\s*\\d{2}");
    private static final Pattern cepTelefon = Pattern.compile("([+]?90|0?)\\s*[5]\\d{2}\\s*\\d{3}\\s*\\d{2}\\s*\\d{2}");

    @Override
    public void initialize(Telefon telefon) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (Strings.isNullOrEmpty(value)) {
            return true;
        }

        return telefon.matcher(value).matches() || cepTelefon.matcher(value).matches();
    }
}
