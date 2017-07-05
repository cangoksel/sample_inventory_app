/*
 * Hibernate Validator, declare and validate application constraints
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package common.validation.datetime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class PastValidatorForLocalDate implements ConstraintValidator<Past, LocalDate> {

    @Override
    public void initialize(Past constraintAnnotation) {

    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        // null values are valid
        if (value == null) {
            return true;
        }

        return value.atStartOfDay().isBefore(LocalDateTime.now());
    }
}
