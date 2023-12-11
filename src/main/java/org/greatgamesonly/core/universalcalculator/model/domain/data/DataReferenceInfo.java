package org.greatgamesonly.core.universalcalculator.model.domain.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.greatgamesonly.core.universalcalculator.configuration.DataSpecificIdsListConverter;

import java.sql.Timestamp;
import java.util.List;

@Entity(name="data_size_info")
public class DataReferenceInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "data_size_info_type_id")
    @NotNull
    private DataSizeInfoType dataSizeInfoType;

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
}
