package com.kingprice.insurance.springworkassessment.controller.v1;

import com.kingprice.insurance.springworkassessment.domain.formula.conversion.ConversionFormula;
import com.kingprice.insurance.springworkassessment.service.ConversionFormulaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/conversion-formula")
public class ConversionFormulaCtrl {
    @Autowired
    private ConversionFormulaService conversionFormulaService;

    @PostMapping
    public ConversionFormula createConversionFormula(@Valid @RequestBody ConversionFormula conversionFormula) {
        return conversionFormulaService.createConversionFormula(conversionFormula);
    }

    @GetMapping("/{id}")
    public ConversionFormula getConversionFormula(@Valid @PathVariable Long id) {
        return conversionFormulaService.getConversionFormulaById(id);
    }

    @GetMapping
    public List<ConversionFormula> getAllConversionFormulas() {
        return conversionFormulaService.getAllConversionFormulas();
    }

    @PutMapping("/{id}")
    public ConversionFormula updateConversionFormula(@PathVariable Long id, @Valid @RequestBody ConversionFormula conversionFormula) {
        return conversionFormulaService.updateConversionFormula(id, conversionFormula);
    }

    @DeleteMapping("/{id}")
    public void deleteConversionFormula(@PathVariable Long id) {
        conversionFormulaService.deleteConversionFormula(id);
    }
}
