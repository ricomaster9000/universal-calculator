package com.kingprice.insurance.springworkassessment.repository;

import com.kingprice.insurance.springworkassessment.domain.formula.FormulaType;
import com.kingprice.insurance.springworkassessment.domain.formula.MeasurementUnitToMeasurementUnitConversionFactor;
import com.kingprice.insurance.springworkassessment.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormulaTypeRepository extends BaseRepository<FormulaType,Long> {
}
