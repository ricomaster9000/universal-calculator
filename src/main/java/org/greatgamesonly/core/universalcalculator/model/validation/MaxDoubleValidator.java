package org.greatgamesonly.core.universalcalculator.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MaxDoubleValidator implements ConstraintValidator<MaxDouble, Double> {
    private double maxValue;

    @Override
    public void initialize(MaxDouble constraintAnnotation) {
        this.maxValue = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        // null values are considered valid
        if (value == null) {
            return true;
        }
        return value <= maxValue;
    }
}
