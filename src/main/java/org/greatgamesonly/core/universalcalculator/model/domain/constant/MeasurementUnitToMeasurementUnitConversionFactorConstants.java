package org.greatgamesonly.core.universalcalculator.model.domain.constant;

import org.greatgamesonly.core.universalcalculator.model.domain.formula.MeasurementUnitToMeasurementUnitConversionFactor;

import static org.greatgamesonly.core.universalcalculator.model.domain.constant.MeasurementUnitConstants.*;

public class MeasurementUnitToMeasurementUnitConversionFactorConstants {

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

    public static final MeasurementUnitToMeasurementUnitConversionFactor CALENDAR_YEAR_TO_FEMTOSECOND = new MeasurementUnitToMeasurementUnitConversionFactor()
            .withId(13L)
            .withFromMeasurementUnit(CALENDAR_YEAR)
            .withToMeasurementUnit(FEMTOSECOND)
            .withConversionFactor(31540000000000000000000.0D);

    public static final MeasurementUnitToMeasurementUnitConversionFactor FEMTOSECOND_TO_CALENDAR_YEAR = new MeasurementUnitToMeasurementUnitConversionFactor()
            .withId(14L)
            .withFromMeasurementUnit(FEMTOSECOND)
            .withToMeasurementUnit(CALENDAR_YEAR)
            .withConversionFactor(0.00000000000000000000003171D);

    public static final MeasurementUnitToMeasurementUnitConversionFactor KILOMETER_TO_PLANCK_LENGTH = new MeasurementUnitToMeasurementUnitConversionFactor()
            .withId(15L)
            .withFromMeasurementUnit(KILOMETER)
            .withToMeasurementUnit(PLANCK_LENGTH)
            .withConversionFactor(6.25e+37);

    public static final MeasurementUnitToMeasurementUnitConversionFactor PLANCK_LENGTH_TO_KILOMETER = new MeasurementUnitToMeasurementUnitConversionFactor()
            .withId(16L)
            .withFromMeasurementUnit(PLANCK_LENGTH)
            .withToMeasurementUnit(KILOMETER)
            .withConversionFactor(1.6e-38);
}
