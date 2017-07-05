package common.validation;

import org.javamoney.moneta.Money;
import tr.com.innova.sample.common.money.Moneys;
import tr.com.innova.sample.common.validation.constraints.MinMoney;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * Created by hcelik on 04.06.2015.
 */
public class MinValidatorForMoney implements ConstraintValidator<MinMoney, Money> {

    @Override
    public void initialize(MinMoney minValue) {

    }

    @Override
    public boolean isValid(Money value, ConstraintValidatorContext constraintValidatorContext) {
        // null values are not valid
        if (value == null) {
            return false;
        }
        return value.isGreaterThan(Moneys.of(BigDecimal.ZERO, value.getCurrency()));
    }
}
