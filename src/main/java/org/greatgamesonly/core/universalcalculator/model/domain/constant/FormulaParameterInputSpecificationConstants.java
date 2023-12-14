package org.greatgamesonly.core.universalcalculator.model.domain.constant;

import org.greatgamesonly.core.universalcalculator.model.domain.formula.FormulaParameterInputSpecification;

public class FormulaParameterInputSpecificationConstants {

    public static final FormulaParameterInputSpecification CONVERSION_FROM_PARAM_INPUT_SPEC = new FormulaParameterInputSpecification(
            1L,
            "CONVERSION_FROM",
            "the measurement unit to convert from"
    );

    public static final FormulaParameterInputSpecification CONVERSION_TO_PARAM_INPUT_SPEC = new FormulaParameterInputSpecification(
            2L,
            "CONVERSION_TO",
            "the measurement unit to convert to"
    );
}
