package org.greatgamesonly.core.universalcalculator.model.domain.calculation;

import org.greatgamesonly.core.universalcalculator.GlobalConstants;
import org.greatgamesonly.core.universalcalculator.model.annotation.LinkedRepository;
import org.greatgamesonly.core.universalcalculator.model.domain.base.BaseEntity;
import org.greatgamesonly.core.universalcalculator.model.repository.CalculationRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.greatgamesonly.core.universalcalculator.GlobalConstants.SQL_MAX_DOUBLE_COLUMN_DEFINITION;

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

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "calculation_to_calculation_input_params",
            joinColumns = @JoinColumn(name = "calculation_id"),
            inverseJoinColumns = @JoinColumn(name = "calculation_input_param_id")
    )
    private List<@NotNull CalculationInputParam> calculationInputParams;

    @Column(name="output_value", columnDefinition=SQL_MAX_DOUBLE_COLUMN_DEFINITION)
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

    public List<CalculationInputParam> getCalculationInputParams() {
        return calculationInputParams;
    }

    public CalculationInputParam getCalculationInputParamByPlaceholderName(String placeholderName) {
        return getCalculationInputParamByPlaceholderName(placeholderName, 0);
    }

    public CalculationInputParam getCalculationInputParamByPlaceholderName(String placeholderName, int index) {
        return getCalculationInputParams().stream()
                .filter(calcInput -> calcInput.getPlaceholderName().equals(placeholderName))
                .skip(index)
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
