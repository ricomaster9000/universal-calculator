package org.greatgamesonly.core.universalcalculator.domain.data.base;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.greatgamesonly.core.universalcalculator.domain.data.DataSizeInfo;

@Entity(name = "data_reference")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="data_reference_type")
public abstract class DataReference {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "data_size_info_id")
    @NotNull
    private DataSizeInfo dataSizeInfo;



    // formula -> DataReference -> DataRecord

}
