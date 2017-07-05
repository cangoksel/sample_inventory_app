package com.github.cangoksel.common.validation.constraints;

import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.Email;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.hibernate.validator.constraints.CompositionType.OR;

/**
 * Created by herdemir on 26.05.2015.
 */
@ConstraintComposition(OR)
@Email(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
@Telefon
@ReportAsSingleViolation
@Target({METHOD, FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {})
public @interface IletisimBilgisiConstraint {
    String message() default "{com.github.cangoksel.sample.validation.constraints.IletisimBilgisi.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
