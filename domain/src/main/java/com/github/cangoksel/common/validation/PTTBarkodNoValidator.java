package com.github.cangoksel.common.validation;

import com.github.cangoksel.common.validation.constraints.PTTBarkodNo;
import com.google.common.base.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by usuicmez on 06.10.2015.
 */
public class PTTBarkodNoValidator implements ConstraintValidator<PTTBarkodNo, String> {

    private static final Pattern PATTERN = Pattern.compile("(\\d{13})|([a-zA-Z]{2}\\d{11})");

    @Override
    public void initialize(PTTBarkodNo icraDosyaNo) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Strings.isNullOrEmpty(value) || PATTERN.matcher(value).matches();
    }
}