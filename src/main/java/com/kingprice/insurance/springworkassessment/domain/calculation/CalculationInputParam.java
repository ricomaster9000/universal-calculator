package com.kingprice.insurance.springworkassessment.domain.calculation;

import com.kingprice.insurance.springworkassessment.annotation.LinkedRepository;
import com.kingprice.insurance.springworkassessment.domain.base.BaseEntity;
import com.kingprice.insurance.springworkassessment.repository.CalculationInputParamRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import static com.kingprice.insurance.springworkassessment.GlobalConstants.STANDARD_DB_STRING_MAX_CHAR_SIZE;

@Entity(name = "calculation_input_parameter")
@LinkedRepository(CalculationInputParamRepository.class)
public class CalculationInputParam extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placeholder_name")
    @Size(min=3,max=STANDARD_DB_STRING_MAX_CHAR_SIZE)
    @NotNull
    private String placeholderName;

    @Column(name = "linked_formula_input_param_name")
    @Size(min=3,max=STANDARD_DB_STRING_MAX_CHAR_SIZE)
    @NotNull
    private String linkedFormulaInputParamName;

    @Column(name = "numerical_input_value")
    @Max(Long.MAX_VALUE)
    @NotNull
    private Double numericalInputValue;

    public CalculationInputParam() {}

    public CalculationInputParam(Long id, Double numericalInputValue) {
        this.id = id;
        this.numericalInputValue = numericalInputValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaceholderName() {
        return placeholderName;
    }

    public void setPlaceholderName(String placeholderName) {
        this.placeholderName = placeholderName;
    }

    public String getLinkedFormulaInputParamName() {
        return linkedFormulaInputParamName;
    }

    public void setLinkedFormulaInputParamName(String linkedFormulaInputParamName) {
        this.linkedFormulaInputParamName = linkedFormulaInputParamName;
    }

    public Double getNumericalInputValue() {
        return numericalInputValue;
    }

    public void setNumericalInputValue(Double numericalInputValue) {
        this.numericalInputValue = numericalInputValue;
    }
}
