package com.kingprice.insurance.springworkassessment.domain.shared;

import com.kingprice.insurance.springworkassessment.annotation.LinkedRepository;
import com.kingprice.insurance.springworkassessment.domain.base.BaseEntity;
import com.kingprice.insurance.springworkassessment.repository.NumberInputParamSpecRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity(name="numerical_input_param_specification")
@LinkedRepository(NumberInputParamSpecRepository.class)
public class InputParamSpecification extends BaseEntity implements Serializable {

    public static final InputParamSpecification GENERIC_NUMBER_DECIMAL_INPUT_PARAM = new InputParamSpecification()
            .withId(1L)
            .withStepSizeAllowed(0.00000000001D);

    public static final InputParamSpecification GENERIC_INTEGER_INPUT_PARAM = new InputParamSpecification()
            .withId(2L)
            .withStepSizeAllowed(1.00D);

    public static final InputParamSpecification INTEGER_POSITIVE_INPUT_PARAM = new InputParamSpecification()
            .withId(3L)
            .withMinValueAllowed(0.00D)
            .withStepSizeAllowed(1.00D);

    public static final InputParamSpecification INTEGER_NEGATIVE_INPUT_PARAM = new InputParamSpecification()
            .withId(4L)
            .withMaxValueAllowed(0.00D)
            .withStepSizeAllowed(1.00D);

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="min_value") // null implies "infinite"
    @Max(Long.MAX_VALUE)
    @NotNull
    private Double minValueAllowed = 0.0D;

    @Column(name="max_value") // null implies "infinite"
    @Max(Long.MAX_VALUE)
    @NotNull
    private Double maxValueAllowed = (double) Long.MAX_VALUE;

    @Column(name="step_size_allowed")
    @Min(0L)
    @NotNull
    private Double stepSizeAllowed = 1.00D; /*input param values must increase or decrease between the min and
                                              max value where this value can divide in with no remainder
                                            */

    public InputParamSpecification() {}

    public InputParamSpecification(Long id,
                                   Double minValueAllowed,
                                   Double maxValueAllowed,
                                   Double stepSizeAllowed) {
        this.id = id;
        this.minValueAllowed = minValueAllowed;
        this.maxValueAllowed = maxValueAllowed;
        this.stepSizeAllowed = stepSizeAllowed;
    }

    public InputParamSpecification withId(Long id) {
        this.id = id;
        return this;
    }

    public InputParamSpecification withMinValueAllowed(Double minValueAllowed) {
        this.minValueAllowed = minValueAllowed;
        return this;
    }

    public InputParamSpecification withMaxValueAllowed(Double maxValueAllowed) {
        this.maxValueAllowed = maxValueAllowed;
        return this;
    }

    public InputParamSpecification withStepSizeAllowed(Double stepSizeAllowed) {
        this.stepSizeAllowed = stepSizeAllowed;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMinValueAllowed() {
        return minValueAllowed;
    }

    public void setMinValueAllowed(Double minValueAllowed) {
        this.minValueAllowed = minValueAllowed;
    }

    public Double getMaxValueAllowed() {
        return maxValueAllowed;
    }

    public void setMaxValueAllowed(Double maxValueAllowed) {
        this.maxValueAllowed = maxValueAllowed;
    }

    public Double getStepSizeAllowed() {
        return stepSizeAllowed;
    }

    public void setStepSizeAllowed(Double stepSizeAllowed) {
        this.stepSizeAllowed = stepSizeAllowed;
    }
}
