package com.kingprice.insurance.springworkassessment.domain.formula.conversion;

import com.kingprice.insurance.springworkassessment.annotation.LinkedRepository;
import com.kingprice.insurance.springworkassessment.domain.formula.FormulaParameterInputSpecification;
import com.kingprice.insurance.springworkassessment.domain.formula.FormulaType;
import com.kingprice.insurance.springworkassessment.domain.formula.MeasurementUnit;
import com.kingprice.insurance.springworkassessment.domain.formula.base.Formula;
import com.kingprice.insurance.springworkassessment.repository.ConversionFormulaRepository;
import com.kingprice.insurance.springworkassessment.service.calculation.ConversionFormulaCalculator;
import jakarta.persistence.Entity;

import java.util.ArrayList;
import java.util.List;

import static com.kingprice.insurance.springworkassessment.domain.formula.FormulaParameterInputSpecification.CONVERSION_FROM_PARAM_INPUT_SPEC;
import static com.kingprice.insurance.springworkassessment.domain.formula.FormulaParameterInputSpecification.CONVERSION_TO_PARAM_INPUT_SPEC;
import static com.kingprice.insurance.springworkassessment.domain.formula.FormulaType.CONVERSION_FORMULA_TYPE;
import static com.kingprice.insurance.springworkassessment.domain.formula.MeasurementUnit.ALL_MEASUREMENT_UNITS;

@Entity(name = "conversion_formula")
@LinkedRepository(ConversionFormulaRepository.class)
public class ConversionFormula extends Formula<MeasurementUnit,ConversionFormula> {

    public static ConversionFormula GENERIC_SIMPLE_CONVERSION_FORMULA = new ConversionFormula()
            .withId(1L)
            .withName("Generic Simple Conversion Formula")
            .withDescription("Takes a CONVERSION_FROM measurement unit input param and " +
                    "a CONVERSION_TO measurement unit input param, " +
                    "then gets the relevant conversion ratio and applies it.")
            .withFormulaParameterUsageInfo(new ArrayList<>(List.of(
                    CONVERSION_FROM_PARAM_INPUT_SPEC,
                    CONVERSION_TO_PARAM_INPUT_SPEC
            )))
            .withPossibleFormulaParams(new ArrayList<>(List.of(
                    ALL_MEASUREMENT_UNITS
            )));

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
