package com.kingprice.insurance.springworkassessment.service;

import com.kingprice.insurance.springworkassessment.domain.calculation.CalculateRequest;
import com.kingprice.insurance.springworkassessment.domain.calculation.Calculation;
import com.kingprice.insurance.springworkassessment.exception.CalculationException;
import com.kingprice.insurance.springworkassessment.repository.CalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CalculationService {
    @Autowired
    private CalculationRepository calculationRepository;

    @Transactional()
    public Calculation createCalculation(Calculation calculation) throws CalculationException {
        Calculation performedCalculation = calculation.getFormula().getFormulaCalculator().calculate(calculation);
        return calculationRepository.save(performedCalculation);
    }

    @Transactional()
    public List<Calculation> createCalculations(CalculateRequest calculateRequest) throws CalculationException {
        List<Calculation> performedCalculations = calculateRequest.getLinkedFormula().getFormulaCalculator().calculate(calculateRequest.getCalculationsToPerform());
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

    public Calculation updateCalculation(Long id, Calculation calculation) {
        Calculation existingCalculation = getCalculationById(id);
        existingCalculation.setName(calculation.getName());
        existingCalculation.setOutput(calculation.getOutput());
        return calculationRepository.save(existingCalculation);
    }

    public void deleteCalculation(Long id) {
        Calculation existingCalculation = getCalculationById(id);
        calculationRepository.delete(existingCalculation);
    }
}
