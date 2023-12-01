package org.greatgamesonly.core.universalcalculator.domain.calculation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotNull;
import org.greatgamesonly.core.universalcalculator.configuration.FormulaDeserializer;
import org.greatgamesonly.core.universalcalculator.domain.formula.base.Formula;

import java.io.Serializable;
import java.util.List;

public class CalculateRequest implements Serializable {

    @NotNull
    @JsonDeserialize(using = FormulaDeserializer.class)
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
