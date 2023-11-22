package com.kingprice.insurance.springworkassessment.domain.formula;

import com.kingprice.insurance.springworkassessment.domain.formula.base.Formula;
import com.kingprice.insurance.springworkassessment.domain.formula.conversion.ConversionFormula;

/*
    Formulas as of now are not truly dynamic and cannot be dynamically created,
    the basis is setup for that in future but it will require some changes in code logic.
    This enum class is used to tell the front-end what formula's are supported and can be used
 */
public enum SupportedFormulas {
    CONVERSION_FORMULA(ConversionFormula.class);

    private final Class<? extends Formula<?,?>> formulaClass;

    SupportedFormulas(Class<? extends Formula<?,?>> formulaClass) {
        this.formulaClass = formulaClass;
    }

    public Class<? extends Formula<?,?>> getFormulaClass() {
        return formulaClass;
    }
}
