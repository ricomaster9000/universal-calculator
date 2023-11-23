package com.kingprice.insurance.springworkassessment.domain.formula;

import com.kingprice.insurance.springworkassessment.GlobalConstants;
import com.kingprice.insurance.springworkassessment.domain.base.BaseEntity;
import com.kingprice.insurance.springworkassessment.domain.shared.InputParamSpecification;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class PossibleFormulaParameter extends BaseEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Size(min = 2, max = GlobalConstants.STANDARD_DB_STRING_MAX_CHAR_SIZE)
    @NotNull
    private String name;

    @Column(name = "description")
    @Size(max = GlobalConstants.SHORT_DB_TEXT_MAX_CHAR_SIZE)
    @NotNull
    private String description;

    @ManyToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
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
