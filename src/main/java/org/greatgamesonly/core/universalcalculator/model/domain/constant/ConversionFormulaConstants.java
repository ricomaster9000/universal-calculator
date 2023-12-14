package org.greatgamesonly.core.universalcalculator.model.domain.constant;

import org.greatgamesonly.core.universalcalculator.model.domain.formula.conversion.ConversionFormula;

import java.util.ArrayList;
import java.util.List;

import static org.greatgamesonly.core.universalcalculator.model.domain.constant.FormulaParameterInputSpecificationConstants.CONVERSION_FROM_PARAM_INPUT_SPEC;
import static org.greatgamesonly.core.universalcalculator.model.domain.constant.FormulaParameterInputSpecificationConstants.CONVERSION_TO_PARAM_INPUT_SPEC;
import static org.greatgamesonly.core.universalcalculator.model.domain.constant.MeasurementUnitConstants.ALL_MEASUREMENT_UNITS;

public class ConversionFormulaConstants {
    public static final ConversionFormula GENERIC_SIMPLE_CONVERSION_FORMULA = new ConversionFormula()
            .withId(1L)
            .withName("Generic Simple Conversion Formula")
            .withDescription("Takes a CONVERSION_FROM measurement unit input param and " +
                    "a CONVERSION_TO measurement unit input param, " +
                    "then gets the relevant conversion ratio and applies it.")
            .withFormulaParameterUsageInfo(new ArrayList<>(List.of(
                    CONVERSION_FROM_PARAM_INPUT_SPEC,
                    CONVERSION_TO_PARAM_INPUT_SPEC
            )))
            .withPossibleFormulaParams(new ArrayList<>(List.of(
                    ALL_MEASUREMENT_UNITS
            )));
}
