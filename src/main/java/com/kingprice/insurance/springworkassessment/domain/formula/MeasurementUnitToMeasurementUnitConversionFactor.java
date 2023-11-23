package com.kingprice.insurance.springworkassessment.domain.formula;

import com.kingprice.insurance.springworkassessment.annotation.LinkedRepository;
import com.kingprice.insurance.springworkassessment.domain.base.BaseEntity;
import com.kingprice.insurance.springworkassessment.repository.MeasurementToMeasurementConversionFactRepo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;


@Entity(name = "measurement_unit_to_measurement_unit_conv_factor")
@LinkedRepository(MeasurementToMeasurementConversionFactRepo.class)
public class MeasurementUnitToMeasurementUnitConversionFactor extends BaseEntity implements Serializable {

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
