/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package com.github.cangoksel.common.validation.datetime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

public class PastValidatorForLocalDateTime implements ConstraintValidator<Past, LocalDateTime> {

    @Override
    public void initialize(Past constraintAnnotation) {

    }

    @Override
    public boolean isValid(LocalDateTime value, ConstraintValidatorContext context) {
        // null values are valid
        if (value == null) {
            return true;
        }

        return value.isBefore(LocalDateTime.now());
    }
}
