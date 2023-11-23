package com.kingprice.insurance.springworkassessment.service;

import com.kingprice.insurance.springworkassessment.annotation.LinkedRepository;
import com.kingprice.insurance.springworkassessment.domain.formula.FormulaType;
import com.kingprice.insurance.springworkassessment.domain.formula.base.Formula;
import com.kingprice.insurance.springworkassessment.exception.FormulaException;
import com.kingprice.insurance.springworkassessment.repository.FormulaTypeRepository;
import com.kingprice.insurance.springworkassessment.repository.base.BaseFormulaRepository;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static com.kingprice.insurance.springworkassessment.GlobalConstants.CACHED_FORMULA_SUBCLASSES;
import static com.kingprice.insurance.springworkassessment.exception.FormulaException.FormulaError.*;

@Service
public class FormulaService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private FormulaTypeRepository formulaTypeRepository;

    @Transactional()
    public <T extends Formula<?,?>> T createFormula(T formula) {
        return getFormulaRepository(formula).save(formula);
    }

    @Transactional(readOnly = true)
    public <T extends Formula<?,?>> T getFormulaById(Long id, Long formulaTypeId) {
        FormulaType formulaType = formulaTypeRepository.findById(formulaTypeId)
                .orElseThrow(() -> new FormulaException(FORMULA_TYPE_NOT_FOUND));

        return (T) getFormulaRepositoryByFormulaType(formulaType).findById(id)
                .orElseThrow(() -> new FormulaException(FORMULA_NOT_FOUND));
    }

    @EventListener(ApplicationReadyEvent.class)
    public void cacheFormulaSubTypeClasses() throws ClassNotFoundException {
        Reflections reflections = new Reflections();

        /*
         I tried pinpointing the Reflections object instance to target the web app package name, but
         then it does not find the Formula subclasses, I have a feeling Spring is behind this, but
         if I set the Reflections to scan everything it will find but it is very slow
         */
        CACHED_FORMULA_SUBCLASSES.addAll(reflections.getSubTypesOf(Class.forName(Formula.class.getName())));
    }

    @Transactional(readOnly = true)
    public List<Formula<?,?>> getAllFormulas() {
        List<Formula<?,?>> result = new ArrayList<>();

        Reflections reflections = new Reflections();

        Class<?> clazz = null;
        try {
            clazz = Class.forName(Formula.class.getName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for(Class<?> formulaClass : CACHED_FORMULA_SUBCLASSES) {
            try {
                result.addAll(getFormulaRepositoryGeneric((Formula<?, ?>) formulaClass.getConstructor().newInstance()).findAll());
            } catch(IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {

            }
        }

        return result;
    }

    @Transactional()
    public <T extends Formula<?,?>> T updateFormula(Long id, T formula) {
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

    private <T extends Formula<?,?>> BaseFormulaRepository<T> getFormulaRepositoryByFormulaType(FormulaType formulaType) {
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

    private BaseFormulaRepository<? extends Formula<?,?>> getFormulaRepositoryGeneric(Formula<?,?> formula) {
        return applicationContext.getBean(formula.getFormulaRepositoryClass());
    }

    private <T extends Formula<?,?>> BaseFormulaRepository<T> getFormulaRepository(T formula) {
        return (BaseFormulaRepository<T>) applicationContext.getBean(formula.getFormulaRepositoryClass());
    }
}
