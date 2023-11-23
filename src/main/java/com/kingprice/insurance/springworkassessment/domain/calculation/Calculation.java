package com.kingprice.insurance.springworkassessment.domain.calculation;

import com.kingprice.insurance.springworkassessment.GlobalConstants;
import com.kingprice.insurance.springworkassessment.annotation.LinkedRepository;
import com.kingprice.insurance.springworkassessment.domain.base.BaseEntity;
import com.kingprice.insurance.springworkassessment.domain.formula.base.Formula;
import com.kingprice.insurance.springworkassessment.repository.CalculationRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Entity(name = "calculation")
@LinkedRepository(CalculationRepository.class)
public class Calculation extends BaseEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Size(min = 3, max = GlobalConstants.STANDARD_DB_STRING_MAX_CHAR_SIZE)
    @NotNull
    private String name = "NO_NAME";

    @Column(name = "description")
    @Size(min = 3, max = GlobalConstants.LONG_DB_TEXT_MAX_CHAR_SIZE)
    @NotNull
    private String description = "NO_DESCRIPTION";

    @Transient // too many complications arise from ensuring bi-directional relationship, all I need it for is to do the appropriate
    private Formula<?,?> formula;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "calculation_to_calculation_input_params",
            joinColumns = @JoinColumn(name = "calculation_id"),
            inverseJoinColumns = @JoinColumn(name = "calculation_input_param_id")
    )
    private List<CalculationInputParam> calculationInputParams;

    @Column(name="output_value")
    private BigDecimal output;

    public Calculation() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Formula<?,?> getFormula() {
        return formula;
    }

    public void setFormula(Formula<?,?> formula) {
        this.formula = formula;
    }

    public List<CalculationInputParam> getCalculationInputParams() {
        return calculationInputParams;
    }

    public CalculationInputParam getCalculationInputParamByPlaceholderName(String placeholderName) {
        return getCalculationInputParams().stream()
                .filter(calcInput -> calcInput.getPlaceholderName().equals(placeholderName))
                .findFirst()
                .orElse(null);
    }

    public void setCalculationInputParams(List<CalculationInputParam> calculationInputParams) {
        this.calculationInputParams = calculationInputParams;
    }

    public BigDecimal getOutput() {
        return output;
    }

    public void setOutput(BigDecimal output) {
        this.output = output != null ? output.setScale(8, RoundingMode.HALF_EVEN) : null;
    }
}
