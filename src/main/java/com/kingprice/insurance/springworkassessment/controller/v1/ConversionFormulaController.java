package com.kingprice.insurance.springworkassessment.controller.v1;

import com.kingprice.insurance.springworkassessment.domain.formula.conversion.ConversionFormula;
import com.kingprice.insurance.springworkassessment.service.ConversionFormulaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/conversion-formula")
public class ConversionFormulaController {

    @Autowired
    private ConversionFormulaService conversionFormulaService;

    @PostMapping
    public ResponseEntity<ConversionFormula> createConversionFormula(@Valid @RequestBody ConversionFormula conversionFormula) {
        ConversionFormula createdFormula = conversionFormulaService.createConversionFormula(conversionFormula);
        return new ResponseEntity<>(createdFormula, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConversionFormula> getConversionFormulaById(@PathVariable Long id) {
        ConversionFormula formula = conversionFormulaService.getConversionFormulaById(id);
        return ResponseEntity.ok(formula);
    }

    @GetMapping
    public ResponseEntity<List<ConversionFormula>> getAllConversionFormulas() {
        List<ConversionFormula> formulas = conversionFormulaService.getAllConversionFormulas();
        return ResponseEntity.ok(formulas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConversionFormula> updateConversionFormula(@PathVariable Long id, @Valid @RequestBody ConversionFormula conversionFormula) {
        ConversionFormula updatedFormula = conversionFormulaService.updateConversionFormula(id, conversionFormula);
        return ResponseEntity.ok(updatedFormula);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConversionFormula(@PathVariable Long id) {
        conversionFormulaService.deleteConversionFormula(id);
        return ResponseEntity.noContent().build();
    }
}
