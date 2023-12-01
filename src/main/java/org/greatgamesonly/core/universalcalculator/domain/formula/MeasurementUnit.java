package org.greatgamesonly.core.universalcalculator.domain.formula;

import jakarta.persistence.DiscriminatorValue;
import org.greatgamesonly.core.universalcalculator.annotation.LinkedRepository;
import org.greatgamesonly.core.universalcalculator.domain.shared.InputParamSpecification;
import org.greatgamesonly.core.universalcalculator.repository.MeasurementUnitRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "measurement_unit")
@LinkedRepository(MeasurementUnitRepository.class)
@DiscriminatorValue("measurement_unit")
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
