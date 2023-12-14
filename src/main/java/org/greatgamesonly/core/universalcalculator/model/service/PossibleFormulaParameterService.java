package org.greatgamesonly.core.universalcalculator.model.service;

import org.greatgamesonly.core.universalcalculator.exception.FormulaException;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.PossibleFormulaParameter;
import org.greatgamesonly.core.universalcalculator.model.repository.PossibleFormulaParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.greatgamesonly.core.universalcalculator.exception.FormulaException.FormulaError.FORMULA_POSSIBLE_PARAM_NOT_FOUND;

@Service
public class PossibleFormulaParameterService {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private PossibleFormulaParameterRepository possibleFormulaParameterRepository;

    @Transactional()
    public PossibleFormulaParameter createPossibleFormulaParameter(PossibleFormulaParameter possibleFormulaParameter) {
        return possibleFormulaParameterRepository.save(possibleFormulaParameter);
    }

    @Transactional(readOnly=true)
    public PossibleFormulaParameter getPossibleFormulaParameter(Long id) {
        return possibleFormulaParameterRepository.findById(id).orElseThrow(() -> new FormulaException(FORMULA_POSSIBLE_PARAM_NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public List<PossibleFormulaParameter> getAllPossibleFormulaParameters() {
        return possibleFormulaParameterRepository.findAll();
    }

    @Transactional()
    public PossibleFormulaParameter updatePossibleFormulaParameter(PossibleFormulaParameter possibleFormulaParameter) {
        return possibleFormulaParameterRepository.save(possibleFormulaParameter);
    }

    @Transactional()
    public void deletePossibleFormulaParameter(Long id) {
        possibleFormulaParameterRepository.deleteById(id);
    }
}
