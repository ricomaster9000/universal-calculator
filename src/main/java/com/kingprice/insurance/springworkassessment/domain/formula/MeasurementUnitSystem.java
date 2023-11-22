package com.kingprice.insurance.springworkassessment.domain.formula;

import com.kingprice.insurance.springworkassessment.annotation.LinkedRepository;
import com.kingprice.insurance.springworkassessment.domain.base.BaseEntity;
import com.kingprice.insurance.springworkassessment.repository.MeasurementUnitSystemRepository;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

import static com.kingprice.insurance.springworkassessment.GlobalConstants.STANDARD_DB_STRING_MAX_CHAR_SIZE;

@Entity(name = "measurement_unit_system")
@LinkedRepository(MeasurementUnitSystemRepository.class)
public class MeasurementUnitSystem extends BaseEntity implements Serializable {
    public static final MeasurementUnitSystem IMPERIAL_SYSTEM = new MeasurementUnitSystem("IMPERIAL_SYSTEM");
    public static final MeasurementUnitSystem METRIC_SYSTEM = new MeasurementUnitSystem("METRIC_SYSTEM");

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length=STANDARD_DB_STRING_MAX_CHAR_SIZE)
    @NotNull
    @Length(min = 3, max = STANDARD_DB_STRING_MAX_CHAR_SIZE)
    private String name;

    public MeasurementUnitSystem() {}

    public MeasurementUnitSystem(String name) {
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
