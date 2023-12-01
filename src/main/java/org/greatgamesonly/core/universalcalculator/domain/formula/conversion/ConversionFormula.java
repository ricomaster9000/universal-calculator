package org.greatgamesonly.core.universalcalculator.domain.formula.conversion;

import org.greatgamesonly.core.universalcalculator.annotation.LinkedRepository;
import org.greatgamesonly.core.universalcalculator.domain.formula.base.Formula;
import org.greatgamesonly.core.universalcalculator.repository.ConversionFormulaRepository;
import org.greatgamesonly.core.universalcalculator.repository.base.BaseFormulaRepository;
import org.greatgamesonly.core.universalcalculator.service.calculation.ConversionFormulaCalculator;
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
