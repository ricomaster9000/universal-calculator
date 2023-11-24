package org.greatgamesonly.core.universalcalculator.domain.formula.base;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.greatgamesonly.core.universalcalculator.configuration.FormulaDeserializer;

public class FormulaRequest {

    @JsonDeserialize(using = FormulaDeserializer.class)
    private Formula<?,?> formula;

    public FormulaRequest() {}

    public FormulaRequest(Formula<?,?> formula) {
        this.formula = formula;
    }

    public Formula<?, ?> getFormula() {
        return formula;
    }

    public void setFormula(Formula<?, ?> formula) {
        this.formula = formula;
    }
}
