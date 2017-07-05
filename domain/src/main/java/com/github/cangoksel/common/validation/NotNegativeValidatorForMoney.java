package com.github.cangoksel.common.validation;

import com.github.cangoksel.common.money.Moneys;
import com.github.cangoksel.common.validation.constraints.NotNegativeMoney;
import org.javamoney.moneta.Money;

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
