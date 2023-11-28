package org.greatgamesonly.core.universalcalculator.domain.formula;

import org.greatgamesonly.core.universalcalculator.GlobalConstants;
import org.greatgamesonly.core.universalcalculator.annotation.LinkedRepository;
import org.greatgamesonly.core.universalcalculator.domain.base.BaseEntity;
import org.greatgamesonly.core.universalcalculator.repository.FormulaParameterInputSpecificationRepo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity(name = "formula_parameter_input_specification")
@LinkedRepository(FormulaParameterInputSpecificationRepo.class)
public class FormulaParameterInputSpecification extends BaseEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parameter_placeholder_name")
    @Size(min = 2, max = GlobalConstants.STANDARD_DB_STRING_MAX_CHAR_SIZE)
    @NotNull
    private String parameterPlaceholderName;

    @Column(name = "description", nullable=true)
    @Size(max = GlobalConstants.SHORT_DB_TEXT_MAX_CHAR_SIZE)
    @NotNull
    private String description;

    public FormulaParameterInputSpecification() {}

    public FormulaParameterInputSpecification(
            Long id,
            String parameterPlaceholderName,
            String description
    ) {
        this.id = id;
        this.parameterPlaceholderName = parameterPlaceholderName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParameterPlaceholderName() {
        return parameterPlaceholderName;
    }

    public void setParameterPlaceholderName(String parameterPlaceholderName) {
        this.parameterPlaceholderName = parameterPlaceholderName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
