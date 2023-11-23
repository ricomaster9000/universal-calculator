package com.kingprice.insurance.springworkassessment.domain.formula.conversion;

import com.kingprice.insurance.springworkassessment.annotation.LinkedRepository;
import com.kingprice.insurance.springworkassessment.domain.formula.FormulaType;
import com.kingprice.insurance.springworkassessment.domain.formula.MeasurementUnit;
import com.kingprice.insurance.springworkassessment.domain.formula.base.Formula;
import com.kingprice.insurance.springworkassessment.repository.ConversionFormulaRepository;
import com.kingprice.insurance.springworkassessment.service.calculation.ConversionFormulaCalculator;
import jakarta.persistence.Entity;

import static com.kingprice.insurance.springworkassessment.domain.ConstantEntities.CONVERSION_FORMULA_TYPE;

@Entity(name = "conversion_formula")
@LinkedRepository(ConversionFormulaRepository.class)
public class ConversionFormula extends Formula<MeasurementUnit,ConversionFormula> {

    public ConversionFormula() {}

    @Override
    public FormulaType getFormulaType() {
        return CONVERSION_FORMULA_TYPE;
    }

    @Override
    public Class<ConversionFormulaCalculator> getFormulaCalculatorClass() {
        return ConversionFormulaCalculator.class;
    }
}
