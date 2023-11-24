package org.greatgamesonly.core.universalcalculator.repository;

import org.greatgamesonly.core.universalcalculator.domain.formula.FormulaParameterInputSpecification;
import org.greatgamesonly.core.universalcalculator.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaParameterInputSpecificationRepo extends BaseRepository<FormulaParameterInputSpecification,Long> {
}
