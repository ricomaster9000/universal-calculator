package org.greatgamesonly.core.universalcalculator.model.domain.constant;

import org.greatgamesonly.core.universalcalculator.model.domain.formula.FormulaParameterUsageInfo;

public class FormulaParameterUsageInfoConstants {

    public static final FormulaParameterUsageInfo CONVERSION_FROM_PARAM_INPUT_SPEC = new FormulaParameterUsageInfo(
            1L,
            "CONVERSION_FROM",
            "the measurement unit to convert from"
    );

    public static final FormulaParameterUsageInfo CONVERSION_TO_PARAM_INPUT_SPEC = new FormulaParameterUsageInfo(
            2L,
            "CONVERSION_TO",
            "the measurement unit to convert to"
    );
}
