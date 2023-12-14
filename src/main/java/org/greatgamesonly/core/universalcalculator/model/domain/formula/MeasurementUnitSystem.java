package org.greatgamesonly.core.universalcalculator.model.domain.formula;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.greatgamesonly.core.universalcalculator.model.annotation.LinkedRepository;
import org.greatgamesonly.core.universalcalculator.model.domain.base.BaseEntity;
import org.greatgamesonly.core.universalcalculator.model.repository.MeasurementUnitSystemRepository;
import org.hibernate.validator.constraints.Length;

import static org.greatgamesonly.core.universalcalculator.GlobalConstants.STANDARD_DB_STRING_MAX_CHAR_SIZE;

@Entity(name = "measurement_unit_system")
@LinkedRepository(MeasurementUnitSystemRepository.class)
public class MeasurementUnitSystem extends BaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length=STANDARD_DB_STRING_MAX_CHAR_SIZE, unique=true)
    @NotNull
    @Length(min = 3, max = STANDARD_DB_STRING_MAX_CHAR_SIZE)
    private String name;

    public MeasurementUnitSystem() {}

    public MeasurementUnitSystem(Long id, String name) {
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
