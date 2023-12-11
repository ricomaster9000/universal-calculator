package org.greatgamesonly.core.universalcalculator.model.repository;

import org.greatgamesonly.core.universalcalculator.model.domain.formula.base.Formula;
import org.greatgamesonly.core.universalcalculator.model.repository.base.BaseFormulaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaRepository extends BaseFormulaRepository<Formula<?>> {
}