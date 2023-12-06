package org.greatgamesonly.core.universalcalculator.domain.data.base;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.greatgamesonly.core.universalcalculator.domain.base.BaseEntity;
import org.greatgamesonly.core.universalcalculator.utils.DateUtils;

import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class DataRecord extends BaseEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Long id;

    @Column(name = "create_date")
    @NotNull
    private Timestamp createDate = DateUtils.nowDbTimestamp();

    @Column(name = "modify_date")
    @NotNull
    private Timestamp modifyDate = DateUtils.nowDbTimestamp();


    public DataRecord() {}

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Timestamp modifyDate) {
        this.modifyDate = modifyDate;
    }
}
