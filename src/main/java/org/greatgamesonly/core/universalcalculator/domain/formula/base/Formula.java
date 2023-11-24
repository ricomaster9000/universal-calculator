package org.greatgamesonly.core.universalcalculator.domain.formula.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.greatgamesonly.core.universalcalculator.GlobalConstants;
import org.greatgamesonly.core.universalcalculator.domain.base.BaseEntity;
import org.greatgamesonly.core.universalcalculator.domain.formula.FormulaParameterInputSpecification;
import org.greatgamesonly.core.universalcalculator.domain.formula.FormulaType;
import org.greatgamesonly.core.universalcalculator.domain.formula.PossibleFormulaParameter;
import org.greatgamesonly.core.universalcalculator.domain.formula.conversion.ConversionFormula;
import org.greatgamesonly.core.universalcalculator.repository.base.BaseFormulaRepository;
import org.greatgamesonly.core.universalcalculator.service.calculation.FormulaCalculator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.context.ApplicationContext;

import java.util.List;

@MappedSuperclass
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "formulaTypeName")
@JsonSubTypes({
		@JsonSubTypes.Type(value = ConversionFormula.class, name = FormulaType.CONVERSION_FORMULA_TYPE_NAME)
		// Add other formula types here
		// TODO find a better way to add support for custom formula types added by users
})
public abstract class Formula<T extends PossibleFormulaParameter, TYPE extends Formula<T,TYPE>> extends BaseEntity {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
			name = "formula_to_formula_param_usage_info",
			joinColumns = @JoinColumn(name = "formula_id"),
			inverseJoinColumns = @JoinColumn(name = "formula_param_usage_id")
	)
	protected List<FormulaParameterInputSpecification> formulaParameterUsageInfo;

	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(
			name = "formula_to_possible_formula_params",
			joinColumns = @JoinColumn(name = "formula_id"),
			inverseJoinColumns = @JoinColumn(name = "possible_formula_param_id")
	)
	protected List<T> possibleFormulaParameters;

	@PrePersist
	public void prePersist() {
		if (formulaType == null) {
			// Set the formulaType based on the subclass's formulaType
			formulaType = getFormulaType();
		}
	}

	public FormulaType getFormulaType() {
		return formulaType;
	}

	@JsonIgnore
	public abstract Class<? extends FormulaCalculator> getFormulaCalculatorClass();

	@JsonIgnore
	public abstract Class<? extends BaseFormulaRepository<? extends Formula<?,?>>> getFormulaRepositoryClass();

	public FormulaCalculator getFormulaCalculator(ApplicationContext applicationContext) {
		FormulaCalculator result = applicationContext.getBean(getFormulaCalculatorClass());
		result.setLinkedFormula(this);
		return result;
	}

	public Formula() {
	}

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

	public TYPE withId(Long id) {
		this.id = id;
		return (TYPE) this;
	}

	public TYPE withName(String name) {
		this.name = name;
		return (TYPE) this;
	}

	public TYPE withDescription(String description) {
		this.description = description;
		return (TYPE) this;
	}

	public TYPE withFormulaType(FormulaType formulaType) {
		this.formulaType = formulaType;
		return (TYPE) this;
	}

	public TYPE withFormulaParameterUsageInfo(List<FormulaParameterInputSpecification> formulaParameterUsageInfo) {
		this.formulaParameterUsageInfo = formulaParameterUsageInfo;
		return (TYPE) this;
	}

	public TYPE withPossibleFormulaParams(List<T> possibleFormulaParameters) {
		this.possibleFormulaParameters = possibleFormulaParameters;
		return (TYPE) this;
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

	public void setFormulaType(FormulaType formulaType) {
		this.formulaType = formulaType;
	}

	public List<FormulaParameterInputSpecification> getFormulaParameterUsageInfo() {
		return formulaParameterUsageInfo;
	}

	public void setFormulaParameterUsageInfo(List<FormulaParameterInputSpecification> formulaParameterUsageInfo) {
		this.formulaParameterUsageInfo = formulaParameterUsageInfo;
	}

	public List<T> getPossibleFormulaParameters() {
		return possibleFormulaParameters;
	}

	public void setPossibleFormulaParameters(List<T> possibleFormulaParameters) {
		this.possibleFormulaParameters = possibleFormulaParameters;
	}
}


