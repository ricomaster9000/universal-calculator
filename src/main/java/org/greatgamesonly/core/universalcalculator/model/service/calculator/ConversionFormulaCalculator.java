package org.greatgamesonly.core.universalcalculator.model.service.calculator;

import org.greatgamesonly.core.universalcalculator.exception.CalculationException;
import org.greatgamesonly.core.universalcalculator.model.domain.calculation.Calculation;
import org.greatgamesonly.core.universalcalculator.model.domain.calculation.CalculationInputParam;
import org.greatgamesonly.core.universalcalculator.model.repository.MeasurementToMeasurementConversionFactRepo;
import org.greatgamesonly.core.universalcalculator.model.service.calculator.base.FormulaCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.greatgamesonly.core.universalcalculator.model.domain.constant.FormulaParameterInputSpecificationConstants.CONVERSION_FROM_PARAM_INPUT_SPEC;
import static org.greatgamesonly.core.universalcalculator.model.domain.constant.FormulaParameterInputSpecificationConstants.CONVERSION_TO_PARAM_INPUT_SPEC;
import static org.greatgamesonly.core.universalcalculator.model.domain.constant.MeasurementUnitConstants.ALL_MEASUREMENT_UNITS;

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

            System.out.println("from parameter name - " + convertFromCalcInput.getPossibleFormulaParameterName());
            System.out.println("to parameter name - " + convertToCalcInput.getPossibleFormulaParameterName());


            Double conversionFactor = measurementToMeasurementConversionFactRepo.findByFromMeasurementUnitNameAndToMeasurementUnitName(
                    convertFromCalcInput.getPossibleFormulaParameterName(),
                    convertToCalcInput.getPossibleFormulaParameterName()
            ).stream().findFirst().orElseThrow(() -> new CalculationException(CalculationException.CalculationError.CALCULATION_UNABLE_TO_FIND_CONVERSION_FACTOR)).getConversionFactor();

            calculation.setOutput(BigDecimal.valueOf(convertFromCalcInput.getNumericalInputValue() * conversionFactor));
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
