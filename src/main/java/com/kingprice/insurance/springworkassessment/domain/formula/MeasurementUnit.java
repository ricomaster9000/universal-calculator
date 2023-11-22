package com.kingprice.insurance.springworkassessment.domain.formula;

import com.kingprice.insurance.springworkassessment.annotation.LinkedRepository;
import com.kingprice.insurance.springworkassessment.domain.shared.InputParamSpecification;
import com.kingprice.insurance.springworkassessment.repository.MeasurementUnitRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import static com.kingprice.insurance.springworkassessment.domain.formula.MeasurementUnitSystem.IMPERIAL_SYSTEM;
import static com.kingprice.insurance.springworkassessment.domain.formula.MeasurementUnitSystem.METRIC_SYSTEM;
import static com.kingprice.insurance.springworkassessment.domain.shared.InputParamSpecification.GENERIC_NUMBER_DECIMAL_INPUT_PARAM;

@Entity(name = "measurement_unit")
@LinkedRepository(MeasurementUnitRepository.class)
public class MeasurementUnit extends FormulaInputParam {

    @ManyToOne
    @JoinColumn(name = "measurement_system_id")
    private MeasurementUnitSystem measurementSystem;

    public static final MeasurementUnit MILLIMETER = new MeasurementUnit("MILLIMETER", "Millimeter", METRIC_SYSTEM, GENERIC_NUMBER_DECIMAL_INPUT_PARAM);
    public static final MeasurementUnit CENTIMETER = new MeasurementUnit("CENTIMETER", "Centimeters", METRIC_SYSTEM, GENERIC_NUMBER_DECIMAL_INPUT_PARAM);
    public static final MeasurementUnit METER = new MeasurementUnit("METER", "Meters", METRIC_SYSTEM, GENERIC_NUMBER_DECIMAL_INPUT_PARAM);
    public static final MeasurementUnit KILOMETER = new MeasurementUnit("KILOMETER", "Kilometer", METRIC_SYSTEM, GENERIC_NUMBER_DECIMAL_INPUT_PARAM);

    public static final MeasurementUnit INCH = new MeasurementUnit("INCH", "Inches", IMPERIAL_SYSTEM, GENERIC_NUMBER_DECIMAL_INPUT_PARAM);
    public static final MeasurementUnit FOOT = new MeasurementUnit("FOOT", "Feet", IMPERIAL_SYSTEM, GENERIC_NUMBER_DECIMAL_INPUT_PARAM);
    public static final MeasurementUnit YARD = new MeasurementUnit("YARD", "Yards", IMPERIAL_SYSTEM, GENERIC_NUMBER_DECIMAL_INPUT_PARAM);
    public static final MeasurementUnit MILE = new MeasurementUnit("MILE", "Miles", IMPERIAL_SYSTEM, GENERIC_NUMBER_DECIMAL_INPUT_PARAM);

    public MeasurementUnit() {}

    public MeasurementUnit(String name, String description, MeasurementUnitSystem measurementSystem, InputParamSpecification inputParamSpecification) {
        super(name,description, inputParamSpecification);
        this.measurementSystem = measurementSystem;
    }

    public MeasurementUnitSystem getMeasurementSystem() {
        return measurementSystem;
    }

    public void setMeasurementSystem(MeasurementUnitSystem measurementSystem) {
        this.measurementSystem = measurementSystem;
    }
}
