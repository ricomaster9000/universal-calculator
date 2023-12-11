package org.greatgamesonly.core.universalcalculator.controller.v1;


import jakarta.validation.Valid;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.base.Formula;
import org.greatgamesonly.core.universalcalculator.model.service.FormulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/formula")
public class FormulaController {

    @Autowired
    private FormulaService formulaService;

    @PostMapping
    public ResponseEntity<? extends Formula<?>> createFormula(@Valid @RequestBody Formula<?> formula) {
        Formula<?> createdFormula = formulaService.createFormula(formula);
        return new ResponseEntity<>(createdFormula, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formula<?>> getFormulaById(@PathVariable Long id) {
        Formula<?> formula = formulaService.getFormulaById(id);
        return ResponseEntity.ok(formula);
    }

    @GetMapping
    public ResponseEntity<List<Formula<?>>> getAllFormulas() {
        List<Formula<?>> formulas = formulaService.getAllFormulas();
        return ResponseEntity.ok(formulas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Formula<?>> updateFormula(@PathVariable Long id, @Valid @RequestBody Formula<?> formula) {
        Formula<?> updatedFormula = formulaService.updateFormula(id, formula);
        return ResponseEntity.ok(updatedFormula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFormula(@PathVariable Long id) {
        formulaService.deleteFormula(id);
        return ResponseEntity.noContent().build();
    }

    /*@GetMapping("/{id}/calculation/quick/")
    public List<Calculation> createCalculationQuick(HttpServletRequest request) {
        Map<String, String[]> queryParams = request.getParameterMap();

        Calculation calculation = new Calculation();
        calculation.setName("QUICK_CALCULATION");

        Long formulaId = Long.parseLong(queryParams.get("formulaId")[0]);
        Long formulaTypeId = Long.parseLong(queryParams.get("formulaTypeId")[0]);

        List<String> calculationInputParamNamesToConsider = new ArrayList<>(Arrays.asList(queryParams.get("paramName")));
        List<CalculationInputParam> calculationInputParams = new ArrayList<>();

        calculationInputParamNamesToConsider.forEach((value) -> {
            String[] possibleFormulaParameterName = queryParams.getOrDefault(value+"_ParamValType",new String[]{null});
            String[] numericalInputValue = queryParams.getOrDefault(value+"_value",new String[]{null});

            calculationInputParams.add(new CalculationInputParam(
                    possibleFormulaParameterName[0],
                    value,
                    numericalInputValue[0] != null ? Double.parseDouble(numericalInputValue[0]) : null
            ));
        });
        calculation.setCalculationInputParams(calculationInputParams);
        return calculationService.createCalculationQuick(new CalculateRequestQuick(formulaId,formulaTypeId,calculation));
    }*/
}
