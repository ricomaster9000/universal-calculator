package com.kingprice.insurance.springworkassessment.domain.formula.conversion;

import com.kingprice.insurance.springworkassessment.annotation.LinkedRepository;
import com.kingprice.insurance.springworkassessment.domain.formula.CalculationParamPlaceholder;
import com.kingprice.insurance.springworkassessment.domain.formula.FormulaType;
import com.kingprice.insurance.springworkassessment.domain.formula.MeasurementUnit;
import com.kingprice.insurance.springworkassessment.domain.formula.base.Formula;
import com.kingprice.insurance.springworkassessment.domain.formula.base.FormulaCalculator;
import com.kingprice.insurance.springworkassessment.repository.ConversionFormulaRepository;
import jakarta.persistence.Entity;

import java.util.ArrayList;

import static com.kingprice.insurance.springworkassessment.domain.formula.FormulaType.CONVERSION_FORMULA_TYPE;

@Entity(name = "conversion_formula")
@LinkedRepository(ConversionFormulaRepository.class)
public class ConversionFormula extends Formula<MeasurementUnit> {

    public static String CALC_PLACEHOLDER_CONVERSION_FROM = "CONVERSION_FROM";
    public static String CALC_PLACEHOLDER_CONVERSION_TO = "CONVERSION_TO";

    public ConversionFormula() {
        this.calculationParamPlaceholder = new ArrayList<>();
        this.calculationParamPlaceholder.add(new CalculationParamPlaceholder(CALC_PLACEHOLDER_CONVERSION_FROM, 0.00D));
        this.calculationParamPlaceholder.add(new CalculationParamPlaceholder(CALC_PLACEHOLDER_CONVERSION_TO, 0.00D));
    }

    @Override
    public FormulaType getFormulaType() {
        return CONVERSION_FORMULA_TYPE;
    }

    @Override
    public FormulaCalculator getFormulaCalculator() {
        return null;
    }
}
