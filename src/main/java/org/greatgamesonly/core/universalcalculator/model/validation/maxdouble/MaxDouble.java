package org.greatgamesonly.core.universalcalculator.model.validation.maxdouble;

import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MaxDouble {

    String message() default "The value must be less than or equal to {value}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    double value();

}
