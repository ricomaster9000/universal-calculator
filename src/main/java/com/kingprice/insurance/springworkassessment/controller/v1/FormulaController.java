package com.kingprice.insurance.springworkassessment.controller.v1;

import com.kingprice.insurance.springworkassessment.domain.formula.base.Formula;
import com.kingprice.insurance.springworkassessment.domain.formula.conversion.ConversionFormula;
import com.kingprice.insurance.springworkassessment.service.FormulaService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Formula<?,?>> createConversionFormula(@Valid @RequestBody Formula<?,?> formula) {
        Formula<?,?> createdFormula = formulaService.createFormula(formula);
        return new ResponseEntity<>(createdFormula, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Formula<?,?>> getConversionFormulaById(@PathVariable Long id, @RequestParam("formulaTypeId") Long formulaTypeId) {
        ConversionFormula formula = formulaService.getFormulaById(id,formulaTypeId);
        return ResponseEntity.ok(formula);
    }

    @GetMapping
    public ResponseEntity<List<Formula<?,?>>> getAllConversionFormulas() {
        List<Formula<?,?>> formulas = formulaService.getAllFormulas();
        return ResponseEntity.ok(formulas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Formula<?,?>> updateConversionFormula(@PathVariable Long id, @Valid @RequestBody Formula<?,?> formula) {
        Formula<?,?> updatedFormula = formulaService.updateFormula(id, formula);
        return ResponseEntity.ok(updatedFormula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConversionFormula(@PathVariable Long id, @RequestParam("formulaTypeId") Long formulaTypeId) {
        formulaService.deleteFormula(id,formulaTypeId);
        return ResponseEntity.noContent().build();
    }
}
