package org.greatgamesonly.core.universalcalculator.model.domain.constant;

import org.greatgamesonly.core.universalcalculator.model.domain.formula.MeasurementUnit;

import static org.greatgamesonly.core.universalcalculator.model.domain.constant.InputParamSpecificationConstants.GENERIC_NUMBER_DECIMAL_INPUT_PARAM;
import static org.greatgamesonly.core.universalcalculator.model.domain.constant.MeasurementUnitSystemConstants.*;

public class MeasurementUnitConstants {

    public static final MeasurementUnit ALL_MEASUREMENT_UNITS = new MeasurementUnit(
            1L,
            "ALL_MEASUREMENT_UNITS",
            "Allow all measurement units to be used in formula",
            GENERIC_NUMBER_DECIMAL_INPUT_PARAM
    );

    // METRIC
    public static final MeasurementUnit MILLIMETER = new MeasurementUnit(2L,"MILLIMETER", "Millimeter", GENERIC_NUMBER_DECIMAL_INPUT_PARAM, METRIC_SYSTEM);
    public static final MeasurementUnit CENTIMETER = new MeasurementUnit(3L,"CENTIMETER", "Centimeters", GENERIC_NUMBER_DECIMAL_INPUT_PARAM, METRIC_SYSTEM);
    public static final MeasurementUnit METER = new MeasurementUnit(4L,"METER", "Meters", GENERIC_NUMBER_DECIMAL_INPUT_PARAM, METRIC_SYSTEM);
    public static final MeasurementUnit KILOMETER = new MeasurementUnit(5L,"KILOMETER", "Kilometer", GENERIC_NUMBER_DECIMAL_INPUT_PARAM, METRIC_SYSTEM);
    public static final MeasurementUnit PLANCK_LENGTH = new MeasurementUnit(6L,"PLANCK_LENGTH", "Planck Length", GENERIC_NUMBER_DECIMAL_INPUT_PARAM, METRIC_SYSTEM);

    // IMPERIAL
    public static final MeasurementUnit INCH = new MeasurementUnit(7L,"INCH", "Inches", GENERIC_NUMBER_DECIMAL_INPUT_PARAM, IMPERIAL_SYSTEM);
    public static final MeasurementUnit FOOT = new MeasurementUnit(8L,"FOOT", "Feet", GENERIC_NUMBER_DECIMAL_INPUT_PARAM, IMPERIAL_SYSTEM);
    public static final MeasurementUnit YARD = new MeasurementUnit(9L,"YARD", "Yards", GENERIC_NUMBER_DECIMAL_INPUT_PARAM, IMPERIAL_SYSTEM);
    public static final MeasurementUnit MILE = new MeasurementUnit(10L,"MILE", "Miles", GENERIC_NUMBER_DECIMAL_INPUT_PARAM, IMPERIAL_SYSTEM);

    // TIME
    public static final MeasurementUnit FEMTOSECOND = new MeasurementUnit(11L,"FEMTOSECOND", "Femtosecond", GENERIC_NUMBER_DECIMAL_INPUT_PARAM, TIME);
    public static final MeasurementUnit CALENDAR_YEAR = new MeasurementUnit(12L,"CALENDAR_YEAR", "Calendar Year", GENERIC_NUMBER_DECIMAL_INPUT_PARAM, TIME);
}
