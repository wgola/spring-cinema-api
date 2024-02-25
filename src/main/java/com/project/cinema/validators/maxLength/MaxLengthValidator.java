package com.project.cinema.validators.maxLength;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MaxLengthValidator implements ConstraintValidator<MaxLength, String> {

    private int max;

    @Override
    public void initialize(MaxLength constraintAnnotation) {
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() <= max) {
            return true;
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(getMessage())
                .addConstraintViolation();

        return false;
    }

    private String getMessage() {
        return "Must be max " + max + " characters long!";
    }
}
