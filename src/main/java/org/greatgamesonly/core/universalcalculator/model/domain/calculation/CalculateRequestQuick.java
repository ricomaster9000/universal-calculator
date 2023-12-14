package org.greatgamesonly.core.universalcalculator.model.domain.calculation;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class CalculateRequestQuick implements Serializable {

    @NotNull
    private Long linkedFormulaId;

    @NotNull
    private Calculation calculationToPerform;

    public CalculateRequestQuick() {}

    public CalculateRequestQuick(Long linkedFormulaId, Calculation calculationToPerform) {
        this.linkedFormulaId = linkedFormulaId;
        this.calculationToPerform = calculationToPerform;
    }

    public Long getLinkedFormulaId() {
        return linkedFormulaId;
    }

    public void setLinkedFormulaId(Long linkedFormulaId) {
        this.linkedFormulaId = linkedFormulaId;
    }

    public Calculation getCalculationToPerform() {
        return calculationToPerform;
    }

    public void setCalculationToPerform(Calculation calculationToPerform) {
        this.calculationToPerform = calculationToPerform;
    }
}
