package org.greatgamesonly.core.universalcalculator.model.repository;

import org.greatgamesonly.core.universalcalculator.model.domain.formula.MeasurementUnit;
import org.greatgamesonly.core.universalcalculator.model.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementUnitRepository extends BaseRepository<MeasurementUnit, Long> {
}
