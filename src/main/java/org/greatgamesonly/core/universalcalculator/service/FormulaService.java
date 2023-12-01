package org.greatgamesonly.core.universalcalculator.service;

import org.greatgamesonly.core.universalcalculator.annotation.LinkedRepository;
import org.greatgamesonly.core.universalcalculator.domain.formula.FormulaType;
import org.greatgamesonly.core.universalcalculator.domain.formula.base.Formula;
import org.greatgamesonly.core.universalcalculator.exception.FormulaException;
import org.greatgamesonly.core.universalcalculator.repository.FormulaTypeRepository;
import org.greatgamesonly.core.universalcalculator.repository.base.BaseFormulaRepository;
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
    private FormulaTypeRepository formulaTypeRepository;

    @Transactional()
    public <T extends Formula<?>> T createFormula(T formula) {
        return getFormulaRepository(formula).save(formula);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "formulaCache")
    public <T extends Formula<?>> T getFormulaByIdAndTypeId(Long id, Long formulaTypeId) {
        FormulaType formulaType = formulaTypeRepository.findById(formulaTypeId)
                .orElseThrow(() -> new FormulaException(FORMULA_TYPE_NOT_FOUND));

        return (T) getFormulaRepositoryByFormulaType(formulaType).findById(id)
                .orElseThrow(() -> new FormulaException(FORMULA_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<Formula<?>> getAllFormulas() {
        List<Formula<?>> result = new ArrayList<>();

        for(Class<?> formulaClass : CACHED_FORMULA_SUBCLASSES) {
            result.addAll(CACHED_FORMULA_SUBCLASS_TO_REPOSITORY_CLASSES.get(formulaClass).findAll());
        }

        return result;
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
    public void deleteFormula(Long id, Long formulaTypeId) throws FormulaException {
        FormulaType formulaType = formulaTypeRepository.findById(formulaTypeId)
                .orElseThrow(() -> new FormulaException(FORMULA_TYPE_NOT_FOUND));

        BaseFormulaRepository<?> formulaRepository = getFormulaRepositoryByFormulaType(formulaType);

        if (!formulaRepository.existsById(id)) {
            throw new FormulaException(FORMULA_NOT_FOUND);
        }
        formulaRepository.deleteById(id);
    }

    private <T extends Formula<?>> BaseFormulaRepository<T> getFormulaRepositoryByFormulaType(FormulaType formulaType) {
        BaseFormulaRepository<T> result;
        try {
            Class<?> formulaClass = Class.forName(formulaType.getLinkedFormulaSubClassName());
            if(!formulaClass.isAnnotationPresent(LinkedRepository.class)) {
                throw new FormulaException(FORMULA_LINKED_REPOSITORY_NOT_FOUND);
            }

            result = (BaseFormulaRepository<T>) applicationContext.getBean(formulaClass.getAnnotation(LinkedRepository.class).value());
        } catch (ClassNotFoundException e) {
            throw new FormulaException(FORMULA_TYPE_LINKED_FORMULA_NOT_FOUND,e);
        }
        return result;
    }

    private <T extends Formula<?>> BaseFormulaRepository<T> getFormulaRepository(T formula) {
        return (BaseFormulaRepository<T>) applicationContext.getBean(formula.getFormulaRepositoryClass());
    }
}
