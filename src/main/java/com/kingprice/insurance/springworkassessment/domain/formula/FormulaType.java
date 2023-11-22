package com.kingprice.insurance.springworkassessment.domain.formula;

import com.kingprice.insurance.springworkassessment.annotation.LinkedRepository;
import com.kingprice.insurance.springworkassessment.domain.base.BaseEntity;
import com.kingprice.insurance.springworkassessment.repository.FormulaTypeRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import static com.kingprice.insurance.springworkassessment.GlobalConstants.STANDARD_DB_STRING_MAX_CHAR_SIZE;

@Entity(name = "formula_type")
@LinkedRepository(FormulaTypeRepository.class)
public class FormulaType extends BaseEntity {
    public static final String CONVERSION_FORMULA_TYPE_NAME = "CONVERSION_FORMULA_TYPE";
    public static final FormulaType CONVERSION_FORMULA_TYPE = new FormulaType(1L,CONVERSION_FORMULA_TYPE_NAME);

    @Column(name="id")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length=STANDARD_DB_STRING_MAX_CHAR_SIZE)
    @NotNull
    @Size(min = 3, max = STANDARD_DB_STRING_MAX_CHAR_SIZE)
    private String name;

    public FormulaType() {}

    public FormulaType(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
