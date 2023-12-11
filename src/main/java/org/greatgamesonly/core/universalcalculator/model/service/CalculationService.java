package org.greatgamesonly.core.universalcalculator.model.service;

import org.greatgamesonly.core.universalcalculator.model.domain.calculation.CalculateRequest;
import org.greatgamesonly.core.universalcalculator.model.domain.calculation.CalculateRequestQuick;
import org.greatgamesonly.core.universalcalculator.model.domain.calculation.Calculation;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.base.Formula;
import org.greatgamesonly.core.universalcalculator.exception.FormulaException;
import org.greatgamesonly.core.universalcalculator.model.repository.CalculationRepository;
import org.greatgamesonly.core.universalcalculator.model.service.calculator.base.FormulaCalculator;
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

    @Autowired
    private FormulaService formulaService;


    public List<Calculation> createCalculations(CalculateRequest calculateRequest) {
        return createCalculations(calculateRequest.getLinkedFormula(),calculateRequest.getCalculationsToPerform());
    }

    public List<Calculation> createCalculationQuick(CalculateRequestQuick calculateRequest) {
        return createCalculations(
                formulaService.getFormulaByIdAndTypeId(
                        calculateRequest.getLinkedFormulaTypeId(),
                        calculateRequest.getLinkedFormulaTypeId()
                ),
                List.of(calculateRequest.getCalculationToPerform())
        );
    }

    @Transactional()
    public List<Calculation> createCalculations(Formula<?> formula, List<Calculation> calculationsToPerform) {
        FormulaCalculator formulaCalculator =
                syncFormulaWithCorrectFormulaSubclass(formula).getFormulaCalculator(applicationContext);

        List<Calculation> performedCalculations = formulaCalculator.calculate(calculationsToPerform);
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

    private Formula<?> syncFormulaWithCorrectFormulaSubclass(Formula<?> formula) {
        Formula<?> result;
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
