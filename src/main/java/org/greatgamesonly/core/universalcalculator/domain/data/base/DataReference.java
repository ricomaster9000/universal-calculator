package org.greatgamesonly.core.universalcalculator.domain.data.base;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.greatgamesonly.core.universalcalculator.domain.data.DataReferenceInfo;

import java.util.List;

@Entity(name = "data_reference")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="data_reference_type")
public abstract class DataReference {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "data_reference_info_id")
    @NotNull
    private DataReferenceInfo dataReferenceInfo;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "data_reference_to_data_records",
            joinColumns = @JoinColumn(name = "data_reference_id"),
            inverseJoinColumns = @JoinColumn(name = "data_record_id")
    )
    private List<DataRecord>

}
