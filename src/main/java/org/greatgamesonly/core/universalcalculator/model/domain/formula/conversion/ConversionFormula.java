package org.greatgamesonly.core.universalcalculator.model.domain.formula.conversion;

import org.greatgamesonly.core.universalcalculator.model.annotation.LinkedRepository;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.base.Formula;
import org.greatgamesonly.core.universalcalculator.model.repository.ConversionFormulaRepository;
import org.greatgamesonly.core.universalcalculator.model.repository.base.BaseFormulaRepository;
import org.greatgamesonly.core.universalcalculator.model.service.calculator.ConversionFormulaCalculator;
import jakarta.persistence.Entity;

@Entity(name = "conversion_formula")
@LinkedRepository(ConversionFormulaRepository.class)
public class ConversionFormula extends Formula<ConversionFormula> {

    public ConversionFormula() {}

    @Override
    public Class<ConversionFormulaCalculator> getFormulaCalculatorClass() {
        return ConversionFormulaCalculator.class;
    }

    @Override
    public Class<? extends BaseFormulaRepository<? extends Formula<?>>> getFormulaRepositoryClass() {
        return ConversionFormulaRepository.class;
    }
}
