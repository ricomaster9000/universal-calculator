package org.greatgamesonly.core.universalcalculator.repository;

import org.greatgamesonly.core.universalcalculator.domain.calculation.CalculationInputParam;
import org.greatgamesonly.core.universalcalculator.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationInputParamRepository extends BaseRepository<CalculationInputParam, Long> {
}
