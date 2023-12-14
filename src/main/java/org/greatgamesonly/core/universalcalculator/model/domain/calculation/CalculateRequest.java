package org.greatgamesonly.core.universalcalculator.model.domain.calculation;

import jakarta.validation.constraints.NotNull;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.base.Formula;

import java.io.Serializable;
import java.util.List;

public class CalculateRequest implements Serializable {

    @NotNull
    private Formula<?> linkedFormula;

    @NotNull
    private List<Calculation> calculationsToPerform;

    public CalculateRequest() {}

    public CalculateRequest(Formula<?> linkedFormula, List<Calculation> calculationsToPerform) {
        this.linkedFormula = linkedFormula;
        this.calculationsToPerform = calculationsToPerform;
    }

    public Formula<?> getLinkedFormula() {
        return linkedFormula;
    }

    public void setLinkedFormula(Formula<?> linkedFormula) {
        this.linkedFormula = linkedFormula;
    }

    public List<Calculation> getCalculationsToPerform() {
        return calculationsToPerform;
    }

    public void setCalculationsToPerform(List<Calculation> calculationsToPerform) {
        this.calculationsToPerform = calculationsToPerform;
    }
}
