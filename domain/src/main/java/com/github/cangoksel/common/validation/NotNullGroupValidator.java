package com.github.cangoksel.common.validation;

import com.github.cangoksel.common.exceptions.DomainException;
import com.github.cangoksel.common.validation.constraints.NotNullGroup;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by herdemir on 24.04.2015.
 */
public class NotNullGroupValidator implements ConstraintValidator <NotNullGroup, Object> {
    private static final Logger LOGGER = LoggerFactory.getLogger(NotNullGroupValidator.class);
    private String[] fieldNames;
    private String errorMessage;

    @Override
    public void initialize(NotNullGroup notNullGroup) {
        fieldNames = notNullGroup.fieldNames();
         errorMessage = notNullGroup.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean isNull = false;
        boolean isNotNull = false;
        try {
            for (String fieldName : fieldNames) {
                if (BeanUtils.getProperty(value, fieldName) != null) {
                    isNull = true;
                } else {
                    isNotNull = true;
                }
            }
        } catch (Exception e){
            LOGGER.error(e.toString(), e);
        }

        if( isNull && isNotNull){
            context.disableDefaultConstraintViolation();

            throw new DomainException(errorMessage);

        }
        return true;
    }
}
