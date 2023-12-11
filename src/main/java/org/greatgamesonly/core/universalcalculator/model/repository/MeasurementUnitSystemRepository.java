package org.greatgamesonly.core.universalcalculator.model.repository;

import org.greatgamesonly.core.universalcalculator.model.domain.formula.MeasurementUnitSystem;
import org.greatgamesonly.core.universalcalculator.model.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementUnitSystemRepository extends BaseRepository<MeasurementUnitSystem,Long> {
}
