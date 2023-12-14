package org.greatgamesonly.core.universalcalculator.model.validation.mindouble;

import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MinDouble {

    String message() default "The value must be greater than or equal to {value}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    double value();

}
