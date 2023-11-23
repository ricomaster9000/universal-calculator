package com.kingprice.insurance.springworkassessment.domain.formula;

import com.kingprice.insurance.springworkassessment.annotation.LinkedRepository;
import com.kingprice.insurance.springworkassessment.domain.base.BaseEntity;
import com.kingprice.insurance.springworkassessment.repository.MeasurementToMeasurementConversionFactRepo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

import static com.kingprice.insurance.springworkassessment.domain.formula.MeasurementUnit.*;

@Entity(name = "measurement_unit_to_measurement_unit_conv_factor")
@LinkedRepository(MeasurementToMeasurementConversionFactRepo.class)
public class MeasurementUnitToMeasurementUnitConversionFactor extends BaseEntity implements Serializable {

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

    @Column(name="id")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "from_measurement_unit_id")
    @NotNull
    private MeasurementUnit fromMeasurementUnit;

    @OneToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name = "to_measurement_unit_id")
    @NotNull
    private MeasurementUnit toMeasurementUnit;

    @Column(name = "conversion_factor")
    @NotNull
    @Min(0L)
    @Max(Long.MAX_VALUE)
    private Double conversionFactor;


    public MeasurementUnitToMeasurementUnitConversionFactor() {}

    public MeasurementUnitToMeasurementUnitConversionFactor(
            Long id,
            MeasurementUnit fromMeasurementUnit,
            MeasurementUnit toMeasurementUnit,
            Double conversionFactor
    ) {
        this.id = id;
        this.fromMeasurementUnit = fromMeasurementUnit;
        this.toMeasurementUnit = toMeasurementUnit;
        this.conversionFactor = conversionFactor;
    }

    public MeasurementUnitToMeasurementUnitConversionFactor withId(Long id) {
        this.id = id;
        return this;
    }

    public MeasurementUnitToMeasurementUnitConversionFactor withFromMeasurementUnit(MeasurementUnit fromMeasurementUnit) {
        this.fromMeasurementUnit = fromMeasurementUnit;
        return this;
    }

    public MeasurementUnitToMeasurementUnitConversionFactor withToMeasurementUnit(MeasurementUnit toMeasurementUnit) {
        this.toMeasurementUnit = toMeasurementUnit;
        return this;
    }

    public MeasurementUnitToMeasurementUnitConversionFactor withConversionFactor(Double conversionFactor) {
        this.conversionFactor = conversionFactor;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MeasurementUnit getFromMeasurementUnit() {
        return fromMeasurementUnit;
    }

    public void setFromMeasurementUnit(MeasurementUnit fromMeasurementUnit) {
        this.fromMeasurementUnit = fromMeasurementUnit;
    }

    public MeasurementUnit getToMeasurementUnit() {
        return toMeasurementUnit;
    }

    public void setToMeasurementUnit(MeasurementUnit toMeasurementUnit) {
        this.toMeasurementUnit = toMeasurementUnit;
    }

    public Double getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(Double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }
}
