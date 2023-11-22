package com.kingprice.insurance.springworkassessment.service;

import com.kingprice.insurance.springworkassessment.domain.formula.conversion.ConversionFormula;
import com.kingprice.insurance.springworkassessment.exception.FormulaException;
import com.kingprice.insurance.springworkassessment.repository.ConversionFormulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.kingprice.insurance.springworkassessment.exception.FormulaException.FormulaError.FORMULA_NOT_FOUND;

@Service
public class ConversionFormulaService {

    @Autowired
    private ConversionFormulaRepository conversionFormulaRepository;

    @Transactional()
    public ConversionFormula createConversionFormula(ConversionFormula conversionFormula) {
        // Additional validation can be added here
        return conversionFormulaRepository.save(conversionFormula);
    }

    @Transactional(readOnly = true)
    public ConversionFormula getConversionFormulaById(Long id) {
        return conversionFormulaRepository.findById(id)
                .orElseThrow(() -> new FormulaException(FORMULA_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<ConversionFormula> getAllConversionFormulas() {
        return conversionFormulaRepository.findAll();
    }

    @Transactional()
    public ConversionFormula updateConversionFormula(Long id, ConversionFormula conversionFormula) {
        if (!conversionFormulaRepository.existsById(id)) {
            throw new FormulaException(FORMULA_NOT_FOUND);
        }
        conversionFormula.setId(id); // Ensure the ID is set to the formula being updated
        return conversionFormulaRepository.save(conversionFormula);
    }

    @Transactional()
    public void deleteConversionFormula(Long id) throws FormulaException {
        if (!conversionFormulaRepository.existsById(id)) {
            throw new FormulaException(FORMULA_NOT_FOUND);
        }
        conversionFormulaRepository.deleteById(id);
    }
}
