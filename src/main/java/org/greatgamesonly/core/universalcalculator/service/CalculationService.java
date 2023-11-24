package org.greatgamesonly.core.universalcalculator.service;

import org.greatgamesonly.core.universalcalculator.domain.calculation.CalculateRequest;
import org.greatgamesonly.core.universalcalculator.domain.calculation.Calculation;
import org.greatgamesonly.core.universalcalculator.service.calculation.FormulaCalculator;
import org.greatgamesonly.core.universalcalculator.exception.CalculationException;
import org.greatgamesonly.core.universalcalculator.repository.CalculationRepository;
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

    @Transactional()
    public Calculation createCalculation(Calculation calculation) throws CalculationException {
        FormulaCalculator formulaCalculator = calculation.getFormula().getFormulaCalculator(applicationContext);
        Calculation performedCalculation = formulaCalculator.calculate(calculation);
        return calculationRepository.save(performedCalculation);
    }

    @Transactional()
    public List<Calculation> createCalculations(CalculateRequest calculateRequest) {
        FormulaCalculator formulaCalculator = calculateRequest.getLinkedFormula().getFormulaCalculator(applicationContext);
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
}
