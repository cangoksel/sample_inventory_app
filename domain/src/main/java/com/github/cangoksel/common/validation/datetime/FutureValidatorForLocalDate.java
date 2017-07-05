/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package com.github.cangoksel.common.validation.datetime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Future;
import java.time.LocalDate;

public class FutureValidatorForLocalDate implements ConstraintValidator<Future, LocalDate> {

    @Override
    public void initialize(Future constraintAnnotation) {

    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        // null values are valid
        if (value == null) {
            return true;
        }

        return value.isAfter(LocalDate.now());
    }
}
