package com.kingprice.insurance.springworkassessment.service.calculation;

import com.kingprice.insurance.springworkassessment.domain.calculation.Calculation;
import com.kingprice.insurance.springworkassessment.domain.calculation.CalculationInputParam;
import com.kingprice.insurance.springworkassessment.exception.CalculationException;
import com.kingprice.insurance.springworkassessment.repository.MeasurementToMeasurementConversionFactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.kingprice.insurance.springworkassessment.domain.formula.MeasurementUnit.ALL_MEASUREMENT_UNITS;
import static com.kingprice.insurance.springworkassessment.domain.formula.conversion.ConversionFormula.CONVERSION_FROM_PARAM_INPUT_SPEC;
import static com.kingprice.insurance.springworkassessment.domain.formula.conversion.ConversionFormula.CONVERSION_TO_PARAM_INPUT_SPEC;
import static com.kingprice.insurance.springworkassessment.exception.CalculationException.CalculationError.CALCULATION_UNABLE_TO_FIND_CONVERSION_FACTOR;

@Component
public class ConversionFormulaCalculator extends FormulaCalculator {

    @Autowired
    MeasurementToMeasurementConversionFactRepo measurementToMeasurementConversionFactRepo;

    @Override
    public List<Calculation> doCalculation(Calculation... calculations) {
        CalculationInputParam convertFromCalcInput;
        CalculationInputParam convertToCalcInput;

        for (Calculation calculation : calculations) {
            convertFromCalcInput = calculation.getCalculationInputParamByPlaceholderName(
                    CONVERSION_FROM_PARAM_INPUT_SPEC.getParameterPlaceholderName()
            );

            convertToCalcInput = calculation.getCalculationInputParamByPlaceholderName(
                    CONVERSION_TO_PARAM_INPUT_SPEC.getParameterPlaceholderName()
            );

            Double conversionFactor = measurementToMeasurementConversionFactRepo.findByFromMeasurementUnitNameAndToMeasurementUnitName(
                    convertFromCalcInput.getPossibleFormulaParameterName(),
                    convertToCalcInput.getPossibleFormulaParameterName()
            ).stream().findFirst().orElseThrow(() -> new CalculationException(CALCULATION_UNABLE_TO_FIND_CONVERSION_FACTOR)).getConversionFactor();

            calculation.setOutput(convertFromCalcInput.getNumericalInputValue() * conversionFactor);
        }
        return new ArrayList<>(List.of(calculations));
    }

    @Override
    public void validate(Calculation... calculations) {
        if(!getLinkedFormula().getPossibleFormulaParameters().contains(ALL_MEASUREMENT_UNITS)) {
            for(Calculation calculation : calculations) {
                for(CalculationInputParam calculationInputParam : calculation.getCalculationInputParams()) {
                    if (!calculationInputParameterMeetsSpecification(calculationInputParam, ALL_MEASUREMENT_UNITS.getInputParamSpecification())) {
                        // throw exception
                    }
                }
            }
        } else {
            validateCalculationInputParamsViaLinkedSpecifications(calculations);
        }
    }
}
