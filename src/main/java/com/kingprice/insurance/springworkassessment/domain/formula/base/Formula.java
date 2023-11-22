package com.kingprice.insurance.springworkassessment.domain.formula.base;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.kingprice.insurance.springworkassessment.GlobalConstants;
import com.kingprice.insurance.springworkassessment.domain.base.BaseEntity;
import com.kingprice.insurance.springworkassessment.domain.formula.CalculationParamPlaceholder;
import com.kingprice.insurance.springworkassessment.domain.formula.FormulaInputParam;
import com.kingprice.insurance.springworkassessment.domain.formula.FormulaType;
import com.kingprice.insurance.springworkassessment.domain.formula.conversion.ConversionFormula;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

import static com.kingprice.insurance.springworkassessment.domain.formula.FormulaType.CONVERSION_FORMULA_TYPE_NAME;

@MappedSuperclass
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "formulaTypeName")
@JsonSubTypes({
		@JsonSubTypes.Type(value = ConversionFormula.class, name = CONVERSION_FORMULA_TYPE_NAME)
		// Add other formula types here
		// TODO find a way to add support for custom formula types added by users
})
public abstract class Formula<T extends FormulaInputParam> extends BaseEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long id;

	@Column(name = "name")
	@Size(min = 3, max = GlobalConstants.STANDARD_DB_STRING_MAX_CHAR_SIZE)
	@NotNull
	private String name;

	@Column(name = "description")
	@Size(min = 3, max = GlobalConstants.LONG_DB_TEXT_MAX_CHAR_SIZE)
	@NotNull
	private String description;

	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name = "formula_type_id")
	@NotNull
	private FormulaType formulaType;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(
			name = "formula_to_formula_input_params",
			joinColumns = @JoinColumn(name = "formula_id"),
			inverseJoinColumns = @JoinColumn(name = "formula_input_param_id")
	)
	protected List<FormulaInputParam> formulaInputParameters;

	@Transient
	protected List<CalculationParamPlaceholder> calculationParamPlaceholder;

	@PrePersist
	public void prePersist() {
		if (formulaType == null) {
			// Set the formulaType based on the subclass's formulaType
			formulaType = getFormulaType();
		}
	}

	public abstract FormulaType getFormulaType();

	public abstract FormulaCalculator getFormulaCalculator();

	public Formula() {}

	public Formula(Long id,
				   String name,
				   String description,
				   FormulaType formulaType
	) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.formulaType = formulaType;
	}

	public List<FormulaInputParam> getFormulaParameters() {
		return formulaInputParameters;
	}

	public void setFormulaParameters(List<FormulaInputParam> formulaInputParameters) {
		this.formulaInputParameters = formulaInputParameters;
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

	public String getFormulaTypeName() {
		return getFormulaType().getName();
	}

	public void setFormulaType(FormulaType formulaType) {
		this.formulaType = formulaType;
	}

	public List<CalculationParamPlaceholder> getCalculationParamPlaceholder() {
		return calculationParamPlaceholder;
	}

	public void setCalculationParamPlaceholder(List<CalculationParamPlaceholder> calculationParamPlaceholder) {
		this.calculationParamPlaceholder = calculationParamPlaceholder;
	}
}


