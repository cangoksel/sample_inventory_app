package common.validation;

import org.javamoney.moneta.Money;
import tr.com.innova.sample.common.money.Moneys;
import tr.com.innova.sample.common.validation.constraints.NotNegativeMoney;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * Created by tozyurek on 04.06.2015.
 */
public class NotNegativeValidatorForMoney implements ConstraintValidator<NotNegativeMoney, Money> {

    @Override
    public void initialize(NotNegativeMoney minValue) {

    }

    @Override
    public boolean isValid(Money value, ConstraintValidatorContext constraintValidatorContext) {
        // null values are valid
        if (value == null) {
            return true;
        }
        return !value.isLessThan(Moneys.of(BigDecimal.ZERO, value.getCurrency()));
    }
}
