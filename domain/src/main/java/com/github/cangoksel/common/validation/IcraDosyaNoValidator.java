package com.github.cangoksel.common.validation;

import com.github.cangoksel.common.validation.constraints.IcraDosyaNo;
import com.google.common.base.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by usuicmez on 13.05.2015.
 */
public class IcraDosyaNoValidator implements ConstraintValidator<IcraDosyaNo, String> {

    private static final Pattern PATTERN = Pattern.compile("\\d{4}/[1-9]\\d*");

    @Override
    public void initialize(IcraDosyaNo icraDosyaNo) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Strings.isNullOrEmpty(value) || PATTERN.matcher(value).matches();
    }
}
