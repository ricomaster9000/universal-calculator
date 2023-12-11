package org.greatgamesonly.core.universalcalculator.model.service;

import org.greatgamesonly.core.universalcalculator.model.annotation.LinkedRepository;
import org.greatgamesonly.core.universalcalculator.model.domain.calculation.Calculation;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.FormulaType;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.base.Formula;
import org.greatgamesonly.core.universalcalculator.exception.FormulaException;
import org.greatgamesonly.core.universalcalculator.model.repository.FormulaRepository;
import org.greatgamesonly.core.universalcalculator.model.repository.FormulaTypeRepository;
import org.greatgamesonly.core.universalcalculator.model.repository.base.BaseFormulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.greatgamesonly.core.universalcalculator.GlobalConstants.CACHED_FORMULA_SUBCLASSES;
import static org.greatgamesonly.core.universalcalculator.GlobalConstants.CACHED_FORMULA_SUBCLASS_TO_REPOSITORY_CLASSES;
import static org.greatgamesonly.core.universalcalculator.exception.FormulaException.FormulaError.*;

@Service
public class FormulaService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private FormulaRepository formulaRepository;

    @Transactional()
    public <T extends Formula<?>> T createFormula(T formula) {
        return getFormulaRepository(formula).save(formula);
    }

    @Transactional(readOnly = true)
    public List<Formula<?>> getAllFormulas() {
        List<Formula<?>> result = new ArrayList<>();

        for(Class<?> formulaClass : CACHED_FORMULA_SUBCLASSES) {
            result.addAll(CACHED_FORMULA_SUBCLASS_TO_REPOSITORY_CLASSES.get(formulaClass).findAll());
        }

        return result;
    }

    @Cacheable(value = "formulaCache")
    @Transactional(readOnly = true)
    public Formula<?> getFormulaById(Long id) {
        return formulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Calculation not found"));
    }

    @Transactional()
    public <T extends Formula<?>> T updateFormula(Long id, T formula) {
        if (!getFormulaRepository(formula).existsById(id)) {
            throw new FormulaException(FORMULA_NOT_FOUND);
        }
        formula.setId(id); // Ensure the ID is set to the formula being updated
        return getFormulaRepository(formula).save(formula);
    }

    @Transactional()
    public void deleteFormula(Long id) throws FormulaException {
        Formula<?> formula = formulaRepository.findById(id)
                .orElseThrow(() -> new FormulaException(FORMULA_NOT_FOUND));

        formulaRepository.deleteById(id);
    }

    private <T extends Formula<?>> BaseFormulaRepository<T> getFormulaRepository(T formula) {
        return (BaseFormulaRepository<T>) applicationContext.getBean(formula.getFormulaRepositoryClass());
    }
}
