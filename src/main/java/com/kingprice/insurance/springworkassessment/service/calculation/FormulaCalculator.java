package com.kingprice.insurance.springworkassessment.service.calculation;

import com.kingprice.insurance.springworkassessment.domain.calculation.Calculation;
import com.kingprice.insurance.springworkassessment.domain.calculation.CalculationInputParam;
import com.kingprice.insurance.springworkassessment.domain.formula.PossibleFormulaParameter;
import com.kingprice.insurance.springworkassessment.domain.formula.base.Formula;
import com.kingprice.insurance.springworkassessment.domain.shared.InputParamSpecification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.kingprice.insurance.springworkassessment.domain.formula.MeasurementUnit.ALL_MEASUREMENT_UNITS;

@Component
public abstract class FormulaCalculator {

    private Formula<?,?> linkedFormula;

    public Formula<?,?> getLinkedFormula() {
        return linkedFormula;
    }

    public void setLinkedFormula(Formula<?,?> linkedFormula) {
        this.linkedFormula = linkedFormula;
    }

    public Calculation calculate(Calculation calculation) {
        return calculate(new ArrayList<>(List.of(calculation))).get(0);
    }

    public List<Calculation> calculate(List<Calculation> calculations) {
        return calculations != null ? doCalculation(calculations.toArray(new Calculation[]{})) : null;
    }

    public abstract List<Calculation> doCalculation(Calculation... calculation);

    public void validate(Calculation... calculations) {
        validateCalculationInputParamsViaLinkedSpecifications(calculations);
    }

    public List<Calculation> calculate(Calculation... calculations) {
        validate(calculations);
        doCalculation(calculations);
        return List.of(calculations);
    }

    boolean calculationInputParameterSupported(CalculationInputParam calculationInputParam) {
        return linkedFormula.getPossibleFormulaParameters().stream()
                .anyMatch(supportedFormulaParam ->
                        supportedFormulaParam.getName().equals(calculationInputParam.getPossibleFormulaParameterName())
                );
    }

    public void validateCalculationInputParamsViaLinkedSpecifications(Calculation... calculations) {
        validateCalculationInputParamsViaLinkedSpecifications(List.of(calculations));
    }

    public void validateCalculationInputParamsViaLinkedSpecifications(List<Calculation> calculations) {
        for(Calculation calculation : calculations) {
            for (CalculationInputParam calculationInputParam : calculation.getCalculationInputParams()) {
                // get PossibleFormulaParameter from formula, if not present throw exception
                PossibleFormulaParameter supportedFormulaParam = null;
                if (supportedFormulaParam == null) {
                    // throw exception
                }
            }
        }
    }

    boolean calculationInputParameterMeetsSpecification(CalculationInputParam calculationInputParam, InputParamSpecification specification) {

        boolean result = true;

        if(calculationInputParam.getNumericalInputValue() < specification.getMinValueAllowed()) {
            result = false;
        }

        if(calculationInputParam.getNumericalInputValue() > specification.getMaxValueAllowed()) {
            result = false;
        }

        if(calculationInputParam.getNumericalInputValue() % specification.getStepSizeAllowed() != 0) {
            result = false;
        }

        return result;
    }
}
