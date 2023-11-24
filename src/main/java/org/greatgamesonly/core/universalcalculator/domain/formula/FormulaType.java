package org.greatgamesonly.core.universalcalculator.domain.formula;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.greatgamesonly.core.universalcalculator.annotation.LinkedRepository;
import org.greatgamesonly.core.universalcalculator.domain.base.BaseEntity;
import org.greatgamesonly.core.universalcalculator.repository.FormulaTypeRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import static org.greatgamesonly.core.universalcalculator.GlobalConstants.SHORT_DB_TEXT_MAX_CHAR_SIZE;
import static org.greatgamesonly.core.universalcalculator.GlobalConstants.STANDARD_DB_STRING_MAX_CHAR_SIZE;

@Entity(name = "formula_type")
@LinkedRepository(FormulaTypeRepository.class)
public class FormulaType extends BaseEntity {
    public static final String CONVERSION_FORMULA_TYPE_NAME = "CONVERSION_FORMULA_TYPE";

    @Column(name="id")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length=STANDARD_DB_STRING_MAX_CHAR_SIZE)
    @NotNull
    @Size(min = 3, max = STANDARD_DB_STRING_MAX_CHAR_SIZE)
    private String name;

    @JsonIgnore
    @Column(name = "formula_subclass_lass_name", length=STANDARD_DB_STRING_MAX_CHAR_SIZE)
    @NotNull
    @Size(min = 3, max = SHORT_DB_TEXT_MAX_CHAR_SIZE)
    private String linkedFormulaSubClassName;

    public FormulaType() {}

    public FormulaType(Long id, String name, String linkedFormulaSubClassName) {
        this.id = id;
        this.name = name;
        this.linkedFormulaSubClassName = linkedFormulaSubClassName;
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

    public String getLinkedFormulaSubClassName() {
        return linkedFormulaSubClassName;
    }

    public void setLinkedFormulaSubClassName(String linkedFormulaSubClassName) {
        this.linkedFormulaSubClassName = linkedFormulaSubClassName;
    }
}
