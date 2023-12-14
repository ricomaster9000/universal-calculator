package org.greatgamesonly.core.universalcalculator.model.domain.base;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
    public abstract Long getId();
    public abstract void setId(Long id);

}
