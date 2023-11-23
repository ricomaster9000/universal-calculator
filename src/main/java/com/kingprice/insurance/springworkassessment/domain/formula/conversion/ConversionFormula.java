package com.kingprice.insurance.springworkassessment.domain.formula.conversion;

import com.kingprice.insurance.springworkassessment.annotation.LinkedRepository;
import com.kingprice.insurance.springworkassessment.domain.formula.MeasurementUnit;
import com.kingprice.insurance.springworkassessment.domain.formula.base.Formula;
import com.kingprice.insurance.springworkassessment.repository.ConversionFormulaRepository;
import com.kingprice.insurance.springworkassessment.repository.base.BaseFormulaRepository;
import com.kingprice.insurance.springworkassessment.service.calculation.ConversionFormulaCalculator;
import jakarta.persistence.Entity;

@Entity(name = "conversion_formula")
@LinkedRepository(ConversionFormulaRepository.class)
public class ConversionFormula extends Formula<MeasurementUnit,ConversionFormula> {

    public ConversionFormula() {}

    @Override
    public Class<ConversionFormulaCalculator> getFormulaCalculatorClass() {
        return ConversionFormulaCalculator.class;
    }

    @Override
    public Class<? extends BaseFormulaRepository<? extends Formula<?, ?>>> getFormulaRepositoryClass() {
        return ConversionFormulaRepository.class;
    }
}
