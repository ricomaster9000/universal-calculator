package org.greatgamesonly.core.universalcalculator.domain.calculation;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.greatgamesonly.core.universalcalculator.GlobalConstants;

import java.io.Serializable;

@Entity(name = "calculation_shorthand_template_info")
public class CalculationShorthandTemplateInfo implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "template_description")
    @NotNull
    @Size(min=3,max= GlobalConstants.SHORT_DB_TEXT_MAX_CHAR_SIZE)
    private String templateDescription;

    public CalculationShorthandTemplateInfo() {}

    public CalculationShorthandTemplateInfo(String templateDescription) {
        this.templateDescription = templateDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateDescription() {
        return templateDescription;
    }

    public void setTemplateDescription(String templateDescription) {
        this.templateDescription = templateDescription;
    }
}
