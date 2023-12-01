package org.greatgamesonly.core.universalcalculator.domain.calculation;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.greatgamesonly.core.universalcalculator.GlobalConstants;

import java.io.Serializable;

@Entity(name = "calculation_shorthand_template")
public class CalculationShorthandTemplate implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull
    @Size(min=3,max= GlobalConstants.STANDARD_DB_STRING_MAX_CHAR_SIZE)
    private String name;

    @OneToOne()
    @JoinColumn(name = "calculation_shorthand_template_info_id")
    @NotNull()
    private CalculationShorthandTemplateInfo calculationShorthandTemplateInfo;



    public CalculationShorthandTemplate() {}


}
