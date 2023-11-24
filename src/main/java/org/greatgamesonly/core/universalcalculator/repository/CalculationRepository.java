package org.greatgamesonly.core.universalcalculator.repository;

import org.greatgamesonly.core.universalcalculator.domain.calculation.Calculation;
import org.greatgamesonly.core.universalcalculator.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends BaseRepository<Calculation, Long> {
}