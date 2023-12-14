package org.greatgamesonly.core.universalcalculator.model.domain.constant;

import org.greatgamesonly.core.universalcalculator.model.domain.formula.conversion.SimpleConversionFormula;

import java.util.ArrayList;
import java.util.List;

import static org.greatgamesonly.core.universalcalculator.model.domain.constant.FormulaParameterUsageInfoConstants.CONVERSION_FROM_PARAM_INPUT_SPEC;
import static org.greatgamesonly.core.universalcalculator.model.domain.constant.FormulaParameterUsageInfoConstants.CONVERSION_TO_PARAM_INPUT_SPEC;
import static org.greatgamesonly.core.universalcalculator.model.domain.constant.MeasurementUnitConstants.ALL_MEASUREMENT_UNITS;

public class ConversionFormulaConstants {
    public static final SimpleConversionFormula SIMPLE_CONVERSION_FORMULA = new SimpleConversionFormula()
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

    public static final SimpleConversionFormula DYNAMIC_CONVERSION_FORMULA = new SimpleConversionFormula()
            .withId(2L)
            .withName("Dynamic Conversion Formula")
            .withDescription("Takes a multitude of Input Params, 10 types of each basic arithmetic operation (plus, minus, multiply, divide) and the CONVERSION_FROM and CONVERSION_TO." +
                    "How these params are passed determines the order of how the formula will be calculated")
            .withFormulaParameterUsageInfo(new ArrayList<>(List.of(
                    CONVERSION_FROM_PARAM_INPUT_SPEC,
                    CONVERSION_TO_PARAM_INPUT_SPEC,
                    CONVERSION_TO_PARAM_INPUT_SPEC
            )))
            .withPossibleFormulaParams(new ArrayList<>(List.of(
                    ALL_MEASUREMENT_UNITS
            )));
}
