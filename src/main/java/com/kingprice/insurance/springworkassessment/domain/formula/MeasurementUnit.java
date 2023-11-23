package com.kingprice.insurance.springworkassessment.domain.formula;

import com.kingprice.insurance.springworkassessment.annotation.LinkedRepository;
import com.kingprice.insurance.springworkassessment.domain.shared.InputParamSpecification;
import com.kingprice.insurance.springworkassessment.repository.MeasurementUnitRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "measurement_unit")
@LinkedRepository(MeasurementUnitRepository.class)
public class MeasurementUnit extends PossibleFormulaParameter {

    @ManyToOne
    @JoinColumn(name = "measurement_system_id")
    private MeasurementUnitSystem measurementSystem;

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
