package com.kingprice.insurance.springworkassessment.repository;

import com.kingprice.insurance.springworkassessment.domain.formula.base.Formula;
import com.kingprice.insurance.springworkassessment.domain.formula.conversion.ConversionFormula;
import com.kingprice.insurance.springworkassessment.repository.base.BaseFormulaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaRepository extends BaseFormulaRepository<Formula<?,?>> {
}