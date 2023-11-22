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

    @Column(name = "possible_formula_parameter_name")
    @Size(min=3,max=STANDARD_DB_STRING_MAX_CHAR_SIZE)
    @NotNull
    private String possibleFormulaParameterName;

    @Column(name = "placeholder_name")
    @Size(min=3,max=STANDARD_DB_STRING_MAX_CHAR_SIZE)
    @NotNull
    private String placeholderName;

    @Column(name = "numerical_input_value")
    @Max(Long.MAX_VALUE)
    private Double numericalInputValue;

    public CalculationInputParam() {}

    public CalculationInputParam(String possibleFormulaParameterName, String placeholderName) {
        this.possibleFormulaParameterName = possibleFormulaParameterName;
        this.placeholderName = placeholderName;
    }

    public CalculationInputParam(String possibleFormulaParameterName, String placeholderName, Double numericalInputValue) {
        this.possibleFormulaParameterName = possibleFormulaParameterName;
        this.placeholderName = placeholderName;
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

    public Double getNumericalInputValue() {
        return numericalInputValue;
    }

    public void setNumericalInputValue(Double numericalInputValue) {
        this.numericalInputValue = numericalInputValue;
    }
}
