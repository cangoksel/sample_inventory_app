package com.github.cangoksel.common.validation.constraints;


import com.github.cangoksel.common.validation.NotNullGroupValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by tozyurek on 10.06.2015.
 */
@Documented
@Constraint(validatedBy = NotNullGroupValidator.class)
@Target({TYPE,ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface NotNullGroup {

    String[] fieldNames();

    String message() default "{com.github.cangoksel.sample.validation.constraints.not.null.group.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target( { TYPE, ANNOTATION_TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        NotNullGroup[] value();
    }
}