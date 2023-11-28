package org.greatgamesonly.core.universalcalculator.domain;

import org.greatgamesonly.core.universalcalculator.domain.formula.conversion.ConversionFormula;
import org.greatgamesonly.core.universalcalculator.domain.shared.InputParamSpecification;
import org.greatgamesonly.core.universalcalculator.domain.formula.*;

import java.util.ArrayList;
import java.util.List;

public class ConstantEntities {
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

    public static final MeasurementUnitSystem EITHER_OR_BOTH = new MeasurementUnitSystem(1L,"EITHER_OR_BOTH");
    public static final MeasurementUnitSystem IMPERIAL_SYSTEM = new MeasurementUnitSystem(2L,"IMPERIAL_SYSTEM");
    public static final MeasurementUnitSystem METRIC_SYSTEM = new MeasurementUnitSystem(3L,"METRIC_SYSTEM");

    public static final MeasurementUnit ALL_MEASUREMENT_UNITS = new MeasurementUnit(
            1L,
            "ALL_MEASUREMENT_UNITS",
            "Allow all measurement units to be used in formula",
            EITHER_OR_BOTH,
            GENERIC_NUMBER_DECIMAL_INPUT_PARAM
    );

    public static final MeasurementUnit MILLIMETER = new MeasurementUnit(2L,"MILLIMETER", "Millimeter", METRIC_SYSTEM, GENERIC_NUMBER_DECIMAL_INPUT_PARAM);
    public static final MeasurementUnit CENTIMETER = new MeasurementUnit(3L,"CENTIMETER", "Centimeters", METRIC_SYSTEM, GENERIC_NUMBER_DECIMAL_INPUT_PARAM);
    public static final MeasurementUnit METER = new MeasurementUnit(4L,"METER", "Meters", METRIC_SYSTEM, GENERIC_NUMBER_DECIMAL_INPUT_PARAM);
    public static final MeasurementUnit KILOMETER = new MeasurementUnit(5L,"KILOMETER", "Kilometer", METRIC_SYSTEM, GENERIC_NUMBER_DECIMAL_INPUT_PARAM);

    public static final MeasurementUnit INCH = new MeasurementUnit(6L,"INCH", "Inches", IMPERIAL_SYSTEM, GENERIC_NUMBER_DECIMAL_INPUT_PARAM);
    public static final MeasurementUnit FOOT = new MeasurementUnit(7L,"FOOT", "Feet", IMPERIAL_SYSTEM, GENERIC_NUMBER_DECIMAL_INPUT_PARAM);
    public static final MeasurementUnit YARD = new MeasurementUnit(8L,"YARD", "Yards", IMPERIAL_SYSTEM, GENERIC_NUMBER_DECIMAL_INPUT_PARAM);
    public static final MeasurementUnit MILE = new MeasurementUnit(9L,"MILE", "Miles", IMPERIAL_SYSTEM, GENERIC_NUMBER_DECIMAL_INPUT_PARAM);


    public static final MeasurementUnitToMeasurementUnitConversionFactor METER_TO_FOOT = new MeasurementUnitToMeasurementUnitConversionFactor()
            .withId(1L)
            .withFromMeasurementUnit(METER)
            .withToMeasurementUnit(FOOT)
            .withConversionFactor(3.28084D);

    public static final MeasurementUnitToMeasurementUnitConversionFactor FOOT_TO_METER = new MeasurementUnitToMeasurementUnitConversionFactor()
            .withId(2L)
            .withFromMeasurementUnit(FOOT)
            .withToMeasurementUnit(METER)
            .withConversionFactor(0.3048D);

    public static final MeasurementUnitToMeasurementUnitConversionFactor CENTIMETER_TO_INCH = new MeasurementUnitToMeasurementUnitConversionFactor()
            .withId(3L)
            .withFromMeasurementUnit(CENTIMETER)
            .withToMeasurementUnit(INCH)
            .withConversionFactor(0.393701D);

    public static final MeasurementUnitToMeasurementUnitConversionFactor CENTIMETER_TO_FOOT = new MeasurementUnitToMeasurementUnitConversionFactor()
            .withId(4L)
            .withFromMeasurementUnit(CENTIMETER)
            .withToMeasurementUnit(FOOT)
            .withConversionFactor(0.0328084D);

    public static final MeasurementUnitToMeasurementUnitConversionFactor INCH_TO_CENTIMETER = new MeasurementUnitToMeasurementUnitConversionFactor()
            .withId(5L)
            .withFromMeasurementUnit(INCH)
            .withToMeasurementUnit(CENTIMETER)
            .withConversionFactor(2.5400013716D);

    public static final MeasurementUnitToMeasurementUnitConversionFactor INCH_TO_MILLIMETER = new MeasurementUnitToMeasurementUnitConversionFactor()
            .withId(6L)
            .withFromMeasurementUnit(INCH)
            .withToMeasurementUnit(MILLIMETER)
            .withConversionFactor(25.4D);

    public static final MeasurementUnitToMeasurementUnitConversionFactor MILLIMETER_TO_INCH = new MeasurementUnitToMeasurementUnitConversionFactor()
            .withId(7L)
            .withFromMeasurementUnit(MILLIMETER)
            .withToMeasurementUnit(INCH)
            .withConversionFactor(0.0393701D);

    public static final MeasurementUnitToMeasurementUnitConversionFactor KILOMETER_TO_MILE = new MeasurementUnitToMeasurementUnitConversionFactor()
            .withId(8L)
            .withFromMeasurementUnit(KILOMETER)
            .withToMeasurementUnit(MILE)
            .withConversionFactor(0.6213715277778D);

    public static final MeasurementUnitToMeasurementUnitConversionFactor MILE_TO_KILOMETER = new MeasurementUnitToMeasurementUnitConversionFactor()
            .withId(9L)
            .withFromMeasurementUnit(MILE)
            .withToMeasurementUnit(KILOMETER)
            .withConversionFactor(1.6093448690458176387D);

    public static final MeasurementUnitToMeasurementUnitConversionFactor METER_TO_YARD = new MeasurementUnitToMeasurementUnitConversionFactor()
            .withId(10L)
            .withFromMeasurementUnit(METER)
            .withToMeasurementUnit(YARD)
            .withConversionFactor(1.09361D);

    public static final MeasurementUnitToMeasurementUnitConversionFactor YARD_TO_METER = new MeasurementUnitToMeasurementUnitConversionFactor()
            .withId(11L)
            .withFromMeasurementUnit(YARD)
            .withToMeasurementUnit(METER)
            .withConversionFactor(0.9144D);

    public static final MeasurementUnitToMeasurementUnitConversionFactor FOOT_TO_CENTIMETER = new MeasurementUnitToMeasurementUnitConversionFactor()
            .withId(12L)
            .withFromMeasurementUnit(FOOT)
            .withToMeasurementUnit(CENTIMETER)
            .withConversionFactor(30.48D);
    public static final FormulaType CONVERSION_FORMULA_TYPE = new FormulaType(1L, FormulaType.CONVERSION_FORMULA_TYPE_NAME, ConversionFormula.class.getName());

    public static final ConversionFormula GENERIC_SIMPLE_CONVERSION_FORMULA = new ConversionFormula()
            .withId(1L)
            .withName("Generic Simple Conversion Formula")
            .withDescription("Takes a CONVERSION_FROM measurement unit input param and " +
                    "a CONVERSION_TO measurement unit input param, " +
                    "then gets the relevant conversion ratio and applies it.")
            .withFormulaType(CONVERSION_FORMULA_TYPE)
            .withFormulaParameterUsageInfo(new ArrayList<>(List.of(
                    CONVERSION_FROM_PARAM_INPUT_SPEC,
                    CONVERSION_TO_PARAM_INPUT_SPEC
            )))
            .withPossibleFormulaParams(new ArrayList<>(List.of(
                    ALL_MEASUREMENT_UNITS
            )));
}
