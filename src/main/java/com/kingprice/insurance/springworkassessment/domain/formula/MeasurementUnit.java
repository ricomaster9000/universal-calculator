package com.kingprice.insurance.springworkassessment.domain.formula;

import com.kingprice.insurance.springworkassessment.annotation.LinkedRepository;
import com.kingprice.insurance.springworkassessment.domain.shared.InputParamSpecification;
import com.kingprice.insurance.springworkassessment.repository.MeasurementUnitRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import static com.kingprice.insurance.springworkassessment.domain.formula.MeasurementUnitSystem.*;
import static com.kingprice.insurance.springworkassessment.domain.shared.InputParamSpecification.GENERIC_NUMBER_DECIMAL_INPUT_PARAM;

@Entity(name = "measurement_unit")
@LinkedRepository(MeasurementUnitRepository.class)
public class MeasurementUnit extends PossibleFormulaParameter {

    @ManyToOne
    @JoinColumn(name = "measurement_system_id")
    private MeasurementUnitSystem measurementSystem;

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

    public MeasurementUnit() {}

    public MeasurementUnit(Long id, String name, String description, MeasurementUnitSystem measurementSystem, InputParamSpecification inputParamSpecification) {
        super(id,name,description, inputParamSpecification);
        this.measurementSystem = measurementSystem;
    }

    public MeasurementUnitSystem getMeasurementSystem() {
        return measurementSystem;
    }

    public void setMeasurementSystem(MeasurementUnitSystem measurementSystem) {
        this.measurementSystem = measurementSystem;
    }
}
