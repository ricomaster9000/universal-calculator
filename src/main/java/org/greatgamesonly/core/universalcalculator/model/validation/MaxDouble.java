package org.greatgamesonly.core.universalcalculator.model.validation;

import jakarta.validation.Payload;

public @interface MaxDouble {

    String message() default "The value must be less than or equal to {value}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    double value();

}
