package org.greatgamesonly.core.universalcalculator.model.domain.formula;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.DiscriminatorValue;
import org.greatgamesonly.core.universalcalculator.model.annotation.LinkedRepository;
import org.greatgamesonly.core.universalcalculator.model.domain.shared.InputParamSpecification;
import org.greatgamesonly.core.universalcalculator.model.repository.MeasurementUnitRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import static org.greatgamesonly.core.universalcalculator.model.domain.formula.conversion.ConversionFormula.TYPE;

@Entity(name = "measurement_unit")
@LinkedRepository(MeasurementUnitRepository.class)
@DiscriminatorValue(TYPE)
@JsonTypeName(TYPE)
public class MeasurementUnit extends PossibleFormulaParameter {

    public final static String TYPE = "measurementUnit";

    @ManyToOne
    @JoinColumn(name = "measurement_system_id")
    private MeasurementUnitSystem measurementSystem;

    public MeasurementUnit() {}

    public MeasurementUnit(Long id, String name, String description, InputParamSpecification inputParamSpecification) {
        super(id,name,description,inputParamSpecification);
    }

    public MeasurementUnit(Long id, String name, String description, InputParamSpecification inputParamSpecification, MeasurementUnitSystem measurementSystem) {
        super(id,name,description,inputParamSpecification);
        this.measurementSystem = measurementSystem;
    }

    public MeasurementUnitSystem getMeasurementSystem() {
        return measurementSystem;
    }

    public void setMeasurementSystem(MeasurementUnitSystem measurementSystem) {
        this.measurementSystem = measurementSystem;
    }

    @Override
    public String getFormulaParameterType() {
        return TYPE;
    }
}
