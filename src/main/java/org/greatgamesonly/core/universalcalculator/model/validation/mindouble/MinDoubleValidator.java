package org.greatgamesonly.core.universalcalculator.model.validation.mindouble;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinDoubleValidator implements ConstraintValidator<MinDouble, Double> {
    private double minValue;

    @Override
    public void initialize(MinDouble constraintAnnotation) {
        this.minValue = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        // null values are considered valid
        if (value == null) {
            return true;
        }
        return value >= minValue;
    }
}
