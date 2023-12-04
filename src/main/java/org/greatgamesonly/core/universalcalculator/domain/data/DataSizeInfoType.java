package org.greatgamesonly.core.universalcalculator.domain.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.greatgamesonly.core.universalcalculator.GlobalConstants;

@Entity(name = "data_size_info_type")
public class DataSizeInfoType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull
    @Size(min=3,max= GlobalConstants.STANDARD_DB_STRING_MAX_CHAR_SIZE)
    private String name;

    public DataSizeInfoType() {}

    public DataSizeInfoType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
