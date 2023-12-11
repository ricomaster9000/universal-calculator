package org.greatgamesonly.core.universalcalculator.controller.v1;

import jakarta.servlet.http.HttpServletRequest;
import org.greatgamesonly.core.universalcalculator.model.domain.calculation.CalculateRequest;
import org.greatgamesonly.core.universalcalculator.model.domain.calculation.CalculateRequestQuick;
import org.greatgamesonly.core.universalcalculator.model.domain.calculation.Calculation;
import org.greatgamesonly.core.universalcalculator.model.domain.calculation.CalculationInputParam;
import org.greatgamesonly.core.universalcalculator.model.service.CalculationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/calculation")
public class CalculationController {

    @Autowired
    private CalculationService calculationService;

    @PostMapping()
    public List<Calculation> createCalculation(@Valid @RequestBody CalculateRequest calculateRequest) {
        return calculationService.createCalculations(calculateRequest);
    }

    @GetMapping("/quick/{formulaParams}")
    public List<Calculation> createCalculationQuickAlt(@PathVariable("formulaParams") String formulaParams, @RequestParam("formulaId") Long formulaId) {
        Calculation calculation = new Calculation();
        calculation.setName("QUICK_CALCULATION_ALT");
        List<CalculationInputParam> calculationInputParams = new ArrayList<>();

        String[] formulaParamsSplitted = formulaParams.split("-");
        for(int i = 0, remaining = formulaParamsSplitted.length-1; i < formulaParamsSplitted.length; i+=3,remaining-=3) {
            String placeholderName = formulaParamsSplitted[i];
            String possibleFormulaParameterName = formulaParamsSplitted[i+1];
            String numericalInput = (remaining > 2) ? formulaParamsSplitted[i + 2] : null;
            calculationInputParams.add(new CalculationInputParam(
                    possibleFormulaParameterName,
                    placeholderName,
                    numericalInput != null ? Double.parseDouble(numericalInput) : null
            ));
        }
        calculation.setCalculationInputParams(calculationInputParams);
        return calculationService.createCalculationQuick(new CalculateRequestQuick(formulaId,calculation));
    }

    @GetMapping("/quick")
    public List<Calculation> createCalculationQuick(HttpServletRequest request) {
        Map<String, String[]> queryParams = request.getParameterMap();

        Calculation calculation = new Calculation();
        calculation.setName("QUICK_CALCULATION");

        Long formulaId = Long.parseLong(queryParams.get("formulaId")[0]);

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
        return calculationService.createCalculationQuick(new CalculateRequestQuick(formulaId,calculation));
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
