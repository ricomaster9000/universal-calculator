package com.kingprice.insurance.springworkassessment.repository;

import com.kingprice.insurance.springworkassessment.domain.calculation.Calculation;
import com.kingprice.insurance.springworkassessment.domain.formula.conversion.ConversionFormula;
import com.kingprice.insurance.springworkassessment.repository.base.BaseFormulaRepository;
import com.kingprice.insurance.springworkassessment.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends BaseRepository<Calculation, Long> {
}