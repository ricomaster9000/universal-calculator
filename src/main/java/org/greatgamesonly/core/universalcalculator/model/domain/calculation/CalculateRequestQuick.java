package org.greatgamesonly.core.universalcalculator.model.domain.calculation;

import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public class CalculateRequestQuick implements Serializable {

    @NotNull
    private Long linkedFormulaId;

    @NotNull
    private Long linkedFormulaTypeId;

    @NotNull
    private Calculation calculationToPerform;

    public CalculateRequestQuick() {}

    public CalculateRequestQuick(Long linkedFormulaId, Long linkedFormulaTypeId, Calculation calculationToPerform) {
        this.linkedFormulaId = linkedFormulaId;
        this.linkedFormulaTypeId = linkedFormulaTypeId;
        this.calculationToPerform = calculationToPerform;
    }

    public Long getLinkedFormulaId() {
        return linkedFormulaId;
    }

    public void setLinkedFormulaId(Long linkedFormulaId) {
        this.linkedFormulaId = linkedFormulaId;
    }

    public Long getLinkedFormulaTypeId() {
        return linkedFormulaTypeId;
    }

    public void setLinkedFormulaTypeId(Long linkedFormulaTypeId) {
        this.linkedFormulaTypeId = linkedFormulaTypeId;
    }

    public Calculation getCalculationToPerform() {
        return calculationToPerform;
    }

    public void setCalculationToPerform(Calculation calculationToPerform) {
        this.calculationToPerform = calculationToPerform;
    }
}
