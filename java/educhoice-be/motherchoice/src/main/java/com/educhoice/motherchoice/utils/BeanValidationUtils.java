package com.educhoice.motherchoice.utils;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class BeanValidationUtils {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public <T> Set<ConstraintViolation<T>> validateObject(T object) {
        return validator.validate(object);
    }
}
