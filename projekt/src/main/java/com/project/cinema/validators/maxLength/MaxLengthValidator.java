package com.project.cinema.validators.maxLength;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MaxLengthValidator implements ConstraintValidator<MaxLength, String> {

    private int max;
    private String field;

    @Override
    public void initialize(MaxLength constraintAnnotation) {
        max = constraintAnnotation.max();
        field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.length() <= max) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(getMessage(field))
                .addConstraintViolation();

        return false;
    }

    private String getMessage(String field) {
        return "Field '" + field + "' must be max " + max + " characters long!";
    }
}
