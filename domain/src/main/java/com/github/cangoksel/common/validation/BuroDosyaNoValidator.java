package com.github.cangoksel.common.validation;

import com.github.cangoksel.common.validation.constraints.BuroDosyaNo;
import com.google.common.base.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by tcopur on 24.12.2015.
 */
public class BuroDosyaNoValidator implements ConstraintValidator<BuroDosyaNo, String> {
    private final static Pattern PATTERN = Pattern.compile("B([1-9])*");

    @Override
    public void initialize(BuroDosyaNo buroDosyaNo) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (Strings.isNullOrEmpty(value)) {
            return true;
        }

        return PATTERN.matcher(value).matches();
    }
}
