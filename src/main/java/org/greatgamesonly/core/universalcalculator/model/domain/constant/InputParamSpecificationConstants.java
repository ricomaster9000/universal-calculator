package org.greatgamesonly.core.universalcalculator.model.domain.constant;

import org.greatgamesonly.core.universalcalculator.model.domain.shared.InputParamSpecification;

public class InputParamSpecificationConstants {

    public static final InputParamSpecification GENERIC_NUMBER_DECIMAL_INPUT_PARAM = new InputParamSpecification()
            .withId(1L)
            .withStepSizeAllowed(0.00000000001D);

    public static final InputParamSpecification GENERIC_INTEGER_INPUT_PARAM = new InputParamSpecification()
            .withId(2L)
            .withStepSizeAllowed(1.00D);

    public static final InputParamSpecification INTEGER_POSITIVE_INPUT_PARAM = new InputParamSpecification()
            .withId(3L)
            .withMinValueAllowed(0.00D)
            .withStepSizeAllowed(1.00D);

    public static final InputParamSpecification INTEGER_NEGATIVE_INPUT_PARAM = new InputParamSpecification()
            .withId(4L)
            .withMaxValueAllowed(0.00D)
            .withStepSizeAllowed(1.00D);
}
