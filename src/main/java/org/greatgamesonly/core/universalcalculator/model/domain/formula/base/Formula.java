package org.greatgamesonly.core.universalcalculator.model.domain.formula.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.greatgamesonly.core.universalcalculator.GlobalConstants;
import org.greatgamesonly.core.universalcalculator.model.domain.base.BaseEntity;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.FormulaParameterInputSpecification;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.FormulaType;
import org.greatgamesonly.core.universalcalculator.model.domain.formula.PossibleFormulaParameter;
import org.greatgamesonly.core.universalcalculator.model.repository.base.BaseFormulaRepository;
import org.greatgamesonly.core.universalcalculator.model.service.calculator.base.FormulaCalculator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.context.ApplicationContext;

import java.util.List;

@Entity(name="formula")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "formula_type")
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS,
		include = JsonTypeInfo.As.PROPERTY,
		property = "formulaType")
public abstract class Formula<TYPE extends Formula<TYPE>> extends BaseEntity {
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

	@ManyToMany(cascade = {CascadeType.DETACH,CascadeType.REFRESH}, fetch=FetchType.EAGER)
	@JoinTable(
			name = "formula_to_formula_param_usage_info",
			joinColumns = @JoinColumn(name = "formula_id"),
			inverseJoinColumns = @JoinColumn(name = "formula_param_usage_id")
	)
	protected List<FormulaParameterInputSpecification> formulaParameterUsageInfo;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch=FetchType.EAGER)
	@JoinTable(
			name = "formula_to_possible_formula_params",
			joinColumns = @JoinColumn(name = "formula_id"),
			inverseJoinColumns = @JoinColumn(name = "possible_formula_param_id")
	)
	protected List<PossibleFormulaParameter> possibleFormulaParameters;

	public abstract String getFormulaType();

	@JsonIgnore
	public abstract Class<? extends FormulaCalculator> getFormulaCalculatorClass();

	@JsonIgnore
	public abstract Class<? extends BaseFormulaRepository<? extends Formula<?>>> getFormulaRepositoryClass();

	public FormulaCalculator getFormulaCalculator(ApplicationContext applicationContext) {
		FormulaCalculator result = applicationContext.getBean(getFormulaCalculatorClass());
		result.setLinkedFormula(this);
		return result;
	}

	public Formula() {
	}

	public Formula(Long id,
				   String name,
				   String description
	) {
		this.id = id;
		this.name = name;
		this.description = description;
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

	public TYPE withFormulaParameterUsageInfo(List<FormulaParameterInputSpecification> formulaParameterUsageInfo) {
		this.formulaParameterUsageInfo = formulaParameterUsageInfo;
		return (TYPE) this;
	}

	public TYPE withPossibleFormulaParams(List<PossibleFormulaParameter> possibleFormulaParameters) {
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

	public List<FormulaParameterInputSpecification> getFormulaParameterUsageInfo() {
		return formulaParameterUsageInfo;
	}

	public void setFormulaParameterUsageInfo(List<FormulaParameterInputSpecification> formulaParameterUsageInfo) {
		this.formulaParameterUsageInfo = formulaParameterUsageInfo;
	}

	public List<PossibleFormulaParameter> getPossibleFormulaParameters() {
		return possibleFormulaParameters;
	}

	public void setPossibleFormulaParameters(List<PossibleFormulaParameter> possibleFormulaParameters) {
		this.possibleFormulaParameters = possibleFormulaParameters;
	}
}


