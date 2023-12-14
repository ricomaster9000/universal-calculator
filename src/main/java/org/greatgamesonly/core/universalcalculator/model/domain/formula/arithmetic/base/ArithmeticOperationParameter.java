package org.greatgamesonly.core.universalcalculator.model.domain.formula.arithmetic.base;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.greatgamesonly.core.universalcalculator.model.annotation.LinkedRepository;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.PossibleFormulaParameter;
import org.greatgamesonly.core.universalcalculator.model.repository.MeasurementUnitRepository;

import static org.greatgamesonly.core.universalcalculator.model.domain.formula.arithmetic.base.ArithmeticOperationParameter.TYPE;

@Entity(name = "arithmetic_operation_parameter")
@LinkedRepository(MeasurementUnitRepository.class)
@DiscriminatorValue(TYPE )
@JsonTypeName(TYPE)
@DiscriminatorColumn(name = "arithmetic_operation_type")
public abstract class ArithmeticOperationParameter extends PossibleFormulaParameter {
    public final static String TYPE = "arithmeticOperationParameter";

    @Override
    public String getFormulaParameterType() {
        return TYPE;
    }
}
