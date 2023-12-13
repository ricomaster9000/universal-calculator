package org.greatgamesonly.core.universalcalculator.model.domain.formula.conversion;

import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.DiscriminatorValue;
import org.greatgamesonly.core.universalcalculator.model.annotation.LinkedRepository;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.base.Formula;
import org.greatgamesonly.core.universalcalculator.model.repository.ConversionFormulaRepository;
import org.greatgamesonly.core.universalcalculator.model.repository.base.BaseFormulaRepository;
import org.greatgamesonly.core.universalcalculator.model.service.calculator.ConversionFormulaCalculator;
import jakarta.persistence.Entity;

import static org.greatgamesonly.core.universalcalculator.model.domain.formula.conversion.ConversionFormula.TYPE;

@Entity(name = "conversion_formula")
@LinkedRepository(ConversionFormulaRepository.class)
@DiscriminatorValue(TYPE)
@JsonTypeName(TYPE)
public class ConversionFormula extends Formula<ConversionFormula> {
    public static final String TYPE = "ConversionFormula";

    public ConversionFormula() {}

    @Override
    public String getFormulaType() {
        return TYPE;
    }

    @Override
    public Class<ConversionFormulaCalculator> getFormulaCalculatorClass() {
        return ConversionFormulaCalculator.class;
    }

    @Override
    public Class<? extends BaseFormulaRepository<? extends Formula<?>>> getFormulaRepositoryClass() {
        return ConversionFormulaRepository.class;
    }
}
