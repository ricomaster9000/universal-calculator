package com.kingprice.insurance.springworkassessment.domain.formula.conversion;

import com.kingprice.insurance.springworkassessment.domain.calculation.Calculation;
import com.kingprice.insurance.springworkassessment.domain.calculation.CalculationInputParam;
import com.kingprice.insurance.springworkassessment.domain.formula.base.FormulaCalculator;
import com.kingprice.insurance.springworkassessment.exception.CalculationException;
import com.kingprice.insurance.springworkassessment.repository.MeasurementToMeasurementConversionFactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.kingprice.insurance.springworkassessment.domain.formula.conversion.ConversionFormula.*;
import static com.kingprice.insurance.springworkassessment.exception.CalculationException.CalculationError.CALCULATION_UNABLE_TO_FIND_CONVERSION_FACTOR;

@Component
public class ConversionFormulaCalculator extends FormulaCalculator {

    @Autowired
    MeasurementToMeasurementConversionFactRepo measurementToMeasurementConversionFactRepo;

    @Override
    public List<Calculation> calculate(Calculation... calculations) {
        for (Calculation calculation : calculations) {
            CalculationInputParam convertFromCalcInput = calculation.getCalculationInputParamByPlaceholderName(
                    CONVERSION_FROM_PARAM_INPUT_SPEC.getParameterPlaceholderName()
            );

            CalculationInputParam convertToCalcInput = calculation.getCalculationInputParamByPlaceholderName(
                    CONVERSION_TO_PARAM_INPUT_SPEC.getParameterPlaceholderName()
            );

            Double conversionFactor = measurementToMeasurementConversionFactRepo.findByFromMeasurementUnitNameAndToMeasurementUnitName(
                    convertFromCalcInput.getLinkedFormulaInputParamName(),
                    convertToCalcInput.getLinkedFormulaInputParamName()
            ).stream().findFirst().orElseThrow(() -> new CalculationException(CALCULATION_UNABLE_TO_FIND_CONVERSION_FACTOR)).getConversionFactor();

            calculation.setOutput(convertFromCalcInput.getNumericalInputValue() * conversionFactor);
        }
        return new ArrayList<>(List.of(calculations));
    }
}
