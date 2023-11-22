package com.kingprice.insurance.springworkassessment.domain.formula.base;

import com.kingprice.insurance.springworkassessment.domain.calculation.Calculation;
import com.kingprice.insurance.springworkassessment.exception.CalculationException;

import java.util.ArrayList;
import java.util.List;

public abstract class FormulaCalculator {

    public Calculation calculate(Calculation calculation) {
        return calculate(new ArrayList<>(List.of(calculation))).get(0);
    }

    public List<Calculation> calculate(List<Calculation> calculations) {
        return calculations != null ? calculate(calculations.toArray(new Calculation[]{})) : null;
    }

    public abstract List<Calculation> calculate(Calculation... calculation);
}
