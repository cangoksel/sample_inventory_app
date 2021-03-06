package com.github.cangoksel.common.validation;

import com.github.cangoksel.common.validation.constraints.MuvekkilDosyaNo;
import com.google.common.base.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by tcopur on 24.12.2015.
 */
public class MuvekkilDosyaNoValidator implements ConstraintValidator<MuvekkilDosyaNo, String> {

    private static final Pattern PATTERN = Pattern.compile("M([1-9])*");

    @Override
    public void initialize(MuvekkilDosyaNo muvekkilDosyaNo) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Strings.isNullOrEmpty(value) || PATTERN.matcher(value).matches();
    }
}
