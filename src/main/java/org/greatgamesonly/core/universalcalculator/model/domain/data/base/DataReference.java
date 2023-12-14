package org.greatgamesonly.core.universalcalculator.model.domain.data.base;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.greatgamesonly.core.universalcalculator.model.domain.base.BaseEntity;
import org.greatgamesonly.core.universalcalculator.model.domain.data.DataReferenceInfo;

import java.util.List;

@Entity(name = "data_reference")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="data_reference_type")
public abstract class DataReference<DATA_RECORD_TYPE extends DataRecord> extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "data_reference_info_id")
    @NotNull
    private DataReferenceInfo dataReferenceInfo;

    public DataReference() {}

    public abstract void setDataRecords(List<DATA_RECORD_TYPE> dataRecords);
    public abstract List<DATA_RECORD_TYPE> getDataRecords();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DataReferenceInfo getDataReferenceInfo() {
        return dataReferenceInfo;
    }

    public void setDataReferenceInfo(DataReferenceInfo dataReferenceInfo) {
        this.dataReferenceInfo = dataReferenceInfo;
    }
}
