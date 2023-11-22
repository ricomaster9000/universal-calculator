package com.kingprice.insurance.springworkassessment.domain.formula;

public class CalculationParamPlaceholder {

    private String name;

    private Object defaultValue;

    public CalculationParamPlaceholder() {}

    public CalculationParamPlaceholder(String name, Object defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }
}
