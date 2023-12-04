package org.greatgamesonly.core.universalcalculator.domain.data;

import jakarta.persistence.*;
import org.greatgamesonly.core.universalcalculator.domain.base.BaseEntity;

import java.io.Serializable;

@MappedSuperclass
public class DataRecord extends BaseEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;



    public DataRecord() {}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
