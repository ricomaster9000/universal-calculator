package org.greatgamesonly.core.universalcalculator.model.domain.formula.conversion;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.DiscriminatorValue;
import org.greatgamesonly.core.universalcalculator.model.annotation.LinkedRepository;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.base.Formula;
import org.greatgamesonly.core.universalcalculator.model.repository.SimpleConversionFormulaRepository;
import org.greatgamesonly.core.universalcalculator.model.repository.base.BaseFormulaRepository;
import org.greatgamesonly.core.universalcalculator.model.service.calculator.conversion.SimpleConversionFormulaCalculator;

import static org.greatgamesonly.core.universalcalculator.model.domain.formula.conversion.SimpleConversionFormula.TYPE;

@LinkedRepository(SimpleConversionFormulaRepository.class)
@DiscriminatorValue(TYPE)
@JsonTypeName(TYPE)
public class SimpleConversionFormula extends Formula<SimpleConversionFormula> {
    public static final String TYPE = "SimpleConversionFormula";

    public SimpleConversionFormula() {}

    @Override
    public String getFormulaType() {
        return TYPE;
    }

    @Override
    public Class<SimpleConversionFormulaCalculator> getFormulaCalculatorClass() {
        return SimpleConversionFormulaCalculator.class;
    }

    @Override
    public Class<? extends BaseFormulaRepository<? extends Formula<?>>> getFormulaRepositoryClass() {
        return SimpleConversionFormulaRepository.class;
    }
}
