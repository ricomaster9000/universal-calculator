package org.greatgamesonly.core.universalcalculator.model.domain.calculation;

import org.greatgamesonly.core.universalcalculator.model.annotation.LinkedRepository;
import org.greatgamesonly.core.universalcalculator.model.domain.base.BaseEntity;
import org.greatgamesonly.core.universalcalculator.model.repository.CalculationInputParamRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import static org.greatgamesonly.core.universalcalculator.GlobalConstants.STANDARD_DB_STRING_MAX_CHAR_SIZE;

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

    public String getPossibleFormulaParameterName() {
        return possibleFormulaParameterName;
    }

    public void setPossibleFormulaParameterName(String possibleFormulaParameterName) {
        this.possibleFormulaParameterName = possibleFormulaParameterName;
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
