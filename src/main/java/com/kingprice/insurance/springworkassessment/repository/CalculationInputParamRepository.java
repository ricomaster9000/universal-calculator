package com.kingprice.insurance.springworkassessment.repository;

import com.kingprice.insurance.springworkassessment.domain.calculation.CalculationInputParam;
import com.kingprice.insurance.springworkassessment.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationInputParamRepository extends BaseRepository<CalculationInputParam, Long> {
}
