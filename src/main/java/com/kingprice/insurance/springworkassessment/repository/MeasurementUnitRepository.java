package com.kingprice.insurance.springworkassessment.repository;

import com.kingprice.insurance.springworkassessment.domain.formula.MeasurementUnit;
import com.kingprice.insurance.springworkassessment.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementUnitRepository extends BaseRepository<MeasurementUnit, Long> {
}
