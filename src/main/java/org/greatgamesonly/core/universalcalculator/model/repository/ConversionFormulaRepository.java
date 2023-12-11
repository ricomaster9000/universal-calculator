package org.greatgamesonly.core.universalcalculator.model.repository;

import org.greatgamesonly.core.universalcalculator.model.domain.formula.conversion.ConversionFormula;
import org.greatgamesonly.core.universalcalculator.model.repository.base.BaseFormulaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversionFormulaRepository extends BaseFormulaRepository<ConversionFormula> {
}