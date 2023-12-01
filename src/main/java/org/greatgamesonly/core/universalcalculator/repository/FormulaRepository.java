package org.greatgamesonly.core.universalcalculator.repository;

import org.greatgamesonly.core.universalcalculator.domain.formula.base.Formula;
import org.greatgamesonly.core.universalcalculator.repository.base.BaseFormulaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaRepository extends BaseFormulaRepository<Formula<?>> {
}