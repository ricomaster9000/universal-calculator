package org.greatgamesonly.core.universalcalculator.model.domain.formula.arithmetic;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.DiscriminatorValue;
import org.greatgamesonly.core.universalcalculator.model.annotation.LinkedRepository;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.arithmetic.base.ArithmeticOperationParameter;
import org.greatgamesonly.core.universalcalculator.model.repository.MeasurementUnitRepository;

import static org.greatgamesonly.core.universalcalculator.model.domain.formula.arithmetic.base.ArithmeticOperationParameter.TYPE;

@LinkedRepository(MeasurementUnitRepository.class)
@DiscriminatorValue(TYPE)
@JsonTypeName(TYPE)
public class PlusBy extends ArithmeticOperationParameter {
    public final static String TYPE = "plusBy_arithmeticOperation";

    @Override
    public String getFormulaParameterType() {
        return TYPE;
    }
}
