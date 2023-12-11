package org.greatgamesonly.core.universalcalculator.model.repository;

import org.greatgamesonly.core.universalcalculator.model.domain.formula.FormulaParameterInputSpecification;
import org.greatgamesonly.core.universalcalculator.model.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaParameterInputSpecificationRepo extends BaseRepository<FormulaParameterInputSpecification,Long> {
}
