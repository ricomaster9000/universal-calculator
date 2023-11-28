package org.greatgamesonly.core.universalcalculator.service;

import org.greatgamesonly.core.universalcalculator.domain.calculation.CalculateRequest;
import org.greatgamesonly.core.universalcalculator.domain.calculation.Calculation;
import org.greatgamesonly.core.universalcalculator.domain.formula.base.Formula;
import org.greatgamesonly.core.universalcalculator.exception.CalculationException;
import org.greatgamesonly.core.universalcalculator.exception.FormulaException;
import org.greatgamesonly.core.universalcalculator.repository.CalculationRepository;
import org.greatgamesonly.core.universalcalculator.service.calculation.FormulaCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.greatgamesonly.core.universalcalculator.GlobalConstants.CACHED_FORMULA_SUBCLASS_TO_REPOSITORY_CLASSES;
import static org.greatgamesonly.core.universalcalculator.exception.FormulaException.FormulaError.FORMULA_TYPE_LINKED_FORMULA_NOT_FOUND;

@Service
public class CalculationService {
    @Autowired
    private CalculationRepository calculationRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Transactional()
    public List<Calculation> createCalculations(CalculateRequest calculateRequest) {
        FormulaCalculator formulaCalculator =
                syncFormulaWithCorrectFormulaSubclass(calculateRequest.getLinkedFormula()).getFormulaCalculator(applicationContext);

        List<Calculation> performedCalculations = formulaCalculator.calculate(calculateRequest.getCalculationsToPerform());
        return calculationRepository.saveAll(performedCalculations);
    }

    @Transactional(readOnly = true)
    public Calculation getCalculationById(Long id) {
        return calculationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calculation not found"));
    }

    @Transactional(readOnly = true)
    public List<Calculation> getAllCalculations() {
        return calculationRepository.findAll();
    }

    @Transactional()
    public Calculation updateCalculation(Long id, Calculation calculation) {
        Calculation existingCalculation = getCalculationById(id);
        existingCalculation.setName(calculation.getName());
        existingCalculation.setOutput(calculation.getOutput());
        return calculationRepository.save(existingCalculation);
    }

    @Transactional()
    public void deleteCalculation(Long id) {
        Calculation existingCalculation = getCalculationById(id);
        calculationRepository.delete(existingCalculation);
    }

    private Formula<?,?> syncFormulaWithCorrectFormulaSubclass(Formula<?,?> formula) {
        Formula<?,?> result;
        try {
            CACHED_FORMULA_SUBCLASS_TO_REPOSITORY_CLASSES.forEach((key, value) -> System.out.println(key + " " + value));
            Class.forName(formula.getFormulaType().getLinkedFormulaSubClassName());
            result = CACHED_FORMULA_SUBCLASS_TO_REPOSITORY_CLASSES.get(Class.forName(formula.getFormulaType().getLinkedFormulaSubClassName()))
                    .findById(formula.getId())
                    .orElseThrow(() -> new FormulaException(FORMULA_TYPE_LINKED_FORMULA_NOT_FOUND));
        } catch (ClassNotFoundException | FormulaException e) {
            System.out.println(e.getMessage());
            throw new FormulaException(FORMULA_TYPE_LINKED_FORMULA_NOT_FOUND,e);
        }
        return result;
    }
}
