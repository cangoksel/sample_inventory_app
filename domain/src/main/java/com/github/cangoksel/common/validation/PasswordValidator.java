package com.github.cangoksel.common.validation;


import com.github.cangoksel.common.validation.constraints.Password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.UNICODE_CASE;

/**
 * Created by herdemir on 30.03.2015.
 */
public class PasswordValidator implements ConstraintValidator<Password, CharSequence> {
    private static String SIFRE_PATTERN = "(?=.*[A-ZĞÜŞİIÖÇ])(?=.*[a-zğüşiıöç])(?=.*[0-9])[A-Za-z0-9ĞÜŞİIÖÇğüşiıöç]{6,}";

    private final Pattern passwordPattern = Pattern.compile(
        SIFRE_PATTERN, UNICODE_CASE
    );

    @Override
    public void initialize(Password password) {
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.length() == 0) {
            return true;
        }

        final Matcher matcher = passwordPattern.matcher(value);

        return matcher.matches();
    }
}
