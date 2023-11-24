package org.greatgamesonly.core.universalcalculator.repository;

import org.greatgamesonly.core.universalcalculator.domain.formula.MeasurementUnit;
import org.greatgamesonly.core.universalcalculator.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementUnitRepository extends BaseRepository<MeasurementUnit, Long> {
}
