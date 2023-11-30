package org.greatgamesonly.core.universalcalculator.controller.v1;

import jakarta.servlet.http.HttpServletRequest;
import org.greatgamesonly.core.universalcalculator.domain.calculation.CalculateRequest;
import org.greatgamesonly.core.universalcalculator.domain.calculation.Calculation;
import org.greatgamesonly.core.universalcalculator.service.CalculationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/calculation")
public class CalculationController {

    @Autowired
    private CalculationService calculationService;

    @PostMapping
    public List<Calculation> createCalculation(@Valid @RequestBody CalculateRequest calculateRequest) {
        return calculationService.createCalculations(calculateRequest);
    }

    @PostMapping
    public List<Calculation> createCalculationQuick(HttpServletRequest request) {
        Map<String, String[]> queryParams = request.getParameterMap();
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
