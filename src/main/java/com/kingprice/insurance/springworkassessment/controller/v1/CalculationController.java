package com.kingprice.insurance.springworkassessment.controller.v1;

import com.kingprice.insurance.springworkassessment.domain.calculation.CalculateRequest;
import com.kingprice.insurance.springworkassessment.domain.calculation.Calculation;
import com.kingprice.insurance.springworkassessment.service.CalculationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/calculation")
public class CalculationController {

    @Autowired
    private CalculationService calculationService;

    @PostMapping
    public List<Calculation> createCalculation(@Valid @RequestBody CalculateRequest calculateRequest) {
        return calculationService.createCalculations(calculateRequest);
    }

    @GetMapping("/{id}")
    public Calculation getCalculation(@PathVariable Long id) {
        return calculationService.getCalculationById(id);
    }

    @GetMapping
    public List<Calculation> getAllCalculations() {
        return calculationService.getAllCalculations();
    }

    @PutMapping("/{id}")
    public Calculation updateCalculation(@PathVariable Long id, @Valid @RequestBody Calculation calculation) {
        return calculationService.updateCalculation(id, calculation);
    }

    @DeleteMapping("/{id}")
    public void deleteCalculation(@PathVariable Long id) {
        calculationService.deleteCalculation(id);
    }
}
