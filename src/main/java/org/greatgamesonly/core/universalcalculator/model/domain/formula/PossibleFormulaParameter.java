package org.greatgamesonly.core.universalcalculator.model.domain.formula;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.greatgamesonly.core.universalcalculator.GlobalConstants;
import org.greatgamesonly.core.universalcalculator.model.domain.base.BaseEntity;
import org.greatgamesonly.core.universalcalculator.model.domain.shared.InputParamSpecification;

@Entity(name="possible_formula_parameter")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "formula_parameter_type")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "formulaParameterType")
@JsonTypeName("generic")
public class PossibleFormulaParameter extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique=true)
    @Size(min = 2, max = GlobalConstants.STANDARD_DB_STRING_MAX_CHAR_SIZE)
    @NotNull
    @Pattern(regexp = "^[^-]*$", message = "The name must not contain hyphens (-)")
    private String name;

    @Column(name = "description")
    @Size(max = GlobalConstants.SHORT_DB_TEXT_MAX_CHAR_SIZE)
    @NotNull
    private String description;

    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    @JoinColumn(name = "number_input_param_specification_id")
    @NotNull
    private InputParamSpecification inputParamSpecification;

    public PossibleFormulaParameter() {}

    public PossibleFormulaParameter(
            Long id,
            String name,
            String description,
            InputParamSpecification inputParamSpecification
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.inputParamSpecification = inputParamSpecification;
    }

    public String getFormulaParameterType() {
        return "generic";
    }

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

    public InputParamSpecification getInputParamSpecification() {
        return inputParamSpecification;
    }

    public void setInputParamSpecification(InputParamSpecification inputParamSpecification) {
        this.inputParamSpecification = inputParamSpecification;
    }
}
