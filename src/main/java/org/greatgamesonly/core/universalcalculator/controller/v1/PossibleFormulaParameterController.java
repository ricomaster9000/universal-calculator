package org.greatgamesonly.core.universalcalculator.controller.v1;


import jakarta.validation.Valid;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.PossibleFormulaParameter;
import org.greatgamesonly.core.universalcalculator.model.service.PossibleFormulaParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/possible-formula-parameter")
public class PossibleFormulaParameterController {

    @Autowired
    private PossibleFormulaParameterService possibleFormulaParameterService;

    @PostMapping
    public ResponseEntity<PossibleFormulaParameter> create(@Valid @RequestBody PossibleFormulaParameter toCreate) {
        PossibleFormulaParameter created = possibleFormulaParameterService.createPossibleFormulaParameter(toCreate);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PossibleFormulaParameter> getById(@PathVariable Long id) {
        PossibleFormulaParameter retrieved = possibleFormulaParameterService.getPossibleFormulaParameter(id);
        return ResponseEntity.ok(retrieved);
    }

    @GetMapping
    public ResponseEntity<List<PossibleFormulaParameter>> getAll() {
        List<PossibleFormulaParameter> retrievedAll = possibleFormulaParameterService.getAllPossibleFormulaParameters();
        return ResponseEntity.ok(retrievedAll);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PossibleFormulaParameter> update(@PathVariable Long id, @Valid @RequestBody PossibleFormulaParameter toUpdate) {
        PossibleFormulaParameter updated = possibleFormulaParameterService.updatePossibleFormulaParameter(toUpdate);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        possibleFormulaParameterService.deletePossibleFormulaParameter(id);
        return ResponseEntity.noContent().build();
    }
}
