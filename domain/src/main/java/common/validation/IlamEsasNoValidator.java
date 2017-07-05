package common.validation;

import com.google.common.base.Strings;
import tr.com.innova.sample.common.validation.constraints.IlamEsasNo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by hcelik on 11.05.2015.
 */
public class IlamEsasNoValidator implements ConstraintValidator<IlamEsasNo, String> {

    private static final Pattern PATTERN = Pattern.compile("\\d{4}/[1-9]\\d*");

    @Override
    public void initialize(IlamEsasNo tckno) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return Strings.isNullOrEmpty(value) || PATTERN.matcher(value).matches();
    }
}