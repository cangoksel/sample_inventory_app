package common.validation;

import org.springframework.util.StringUtils;
import tr.com.innova.sample.common.validation.constraints.TCKNO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by usuicmez on 06.05.2015.
 */
public class TCKNOValidator implements ConstraintValidator<TCKNO, String> {

    @Override
    public void initialize(TCKNO tckno) {
    }

    @Override
    public  boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return isTCKimlikNoValid(value);
    }

    public static boolean isTCKimlikNoValid(String value) {
        if (StringUtils.isEmpty(value)) {
            return true;
        }

        if (value.length() != 11) {
            return false;
        }

        char[] tcKmlikNoArray = value.toCharArray();
        int tekler = 0;
        int ciftler = 0;

        for (int k = 0; k < 9; k++) {
            if (k % 2 == 0) {
                tekler += Character.getNumericValue(tcKmlikNoArray[k]);
            } else {
                ciftler += Character.getNumericValue(tcKmlikNoArray[k]);
            }
        }

        int c1 = ((tekler * 7) + (ciftler * 9)) % 10;
        int c2 = (tekler * 8) % 10;

        return c1 != Character.getNumericValue(tcKmlikNoArray[9]) || c2 != Character.getNumericValue(tcKmlikNoArray[10]) || tekler + ciftler == 0 ? false : true;
    }
}
