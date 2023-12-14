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
}
