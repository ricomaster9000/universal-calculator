package org.greatgamesonly.core.universalcalculator.model.repository;

import org.greatgamesonly.core.universalcalculator.model.domain.calculation.CalculationInputParam;
import org.greatgamesonly.core.universalcalculator.model.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationInputParamRepository extends BaseRepository<CalculationInputParam, Long> {
}
