package org.greatgamesonly.core.universalcalculator.model.domain.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.greatgamesonly.core.universalcalculator.configuration.DataSpecificIdsListConverter;
import org.greatgamesonly.core.universalcalculator.model.domain.base.BaseEntity;

import java.sql.Timestamp;
import java.util.List;

@Entity(name="data_size_info")
public class DataReferenceInfo extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_row_id")
    private Long fromRowId;

    @Column(name = "to_row_id")
    private Long toRowId;

    @Column(name = "from_date")
    private Timestamp fromDate;

    @Column(name = "to_date")
    private Timestamp toDate;

    @Column(name = "specific_id_list")
    @Convert(converter = DataSpecificIdsListConverter.class)
    private List<Long> specificIdList;

    public DataReferenceInfo() {}

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public void setId(Long id) {

    }
}
