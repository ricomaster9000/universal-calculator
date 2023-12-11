package org.greatgamesonly.core.universalcalculator.model.service;

import org.greatgamesonly.core.universalcalculator.model.domain.calculation.CalculateRequest;
import org.greatgamesonly.core.universalcalculator.model.domain.calculation.CalculateRequestQuick;
import org.greatgamesonly.core.universalcalculator.model.domain.calculation.Calculation;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.base.Formula;
import org.greatgamesonly.core.universalcalculator.model.repository.CalculationRepository;
import org.greatgamesonly.core.universalcalculator.model.repository.FormulaRepository;
import org.greatgamesonly.core.universalcalculator.model.service.calculator.base.FormulaCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CalculationService {
    @Autowired
    private CalculationRepository calculationRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private FormulaService formulaService;

    @Autowired
    private FormulaRepository formulaRepository;


    public List<Calculation> createCalculations(CalculateRequest calculateRequest) {
        return createCalculations(calculateRequest.getLinkedFormula(),calculateRequest.getCalculationsToPerform());
    }

    public List<Calculation> createCalculationQuick(CalculateRequestQuick calculateRequest) {
        return createCalculations(
                formulaService.getFormulaById(
                        calculateRequest.getLinkedFormulaId()
                ),
                List.of(calculateRequest.getCalculationToPerform())
        );
    }

    @Transactional()
    public List<Calculation> createCalculations(Formula<?> formula, List<Calculation> calculationsToPerform) {
        FormulaCalculator formulaCalculator = formulaService.getFormulaById(formula.getId()).getFormulaCalculator(applicationContext);
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
}
