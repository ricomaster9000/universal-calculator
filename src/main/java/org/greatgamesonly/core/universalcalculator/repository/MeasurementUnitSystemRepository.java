package org.greatgamesonly.core.universalcalculator.repository;

import org.greatgamesonly.core.universalcalculator.domain.formula.MeasurementUnitSystem;
import org.greatgamesonly.core.universalcalculator.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementUnitSystemRepository extends BaseRepository<MeasurementUnitSystem,Long> {
}
