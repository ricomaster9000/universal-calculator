package org.greatgamesonly.core.universalcalculator.model.domain.data.market;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import org.greatgamesonly.core.universalcalculator.model.domain.data.base.DataReference;

import java.util.List;

@DiscriminatorValue("market_data_ref")
public class MarketDataReference extends DataReference<MarketDataRecord> {

    @OneToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "market_reference_id"),
            inverseJoinColumns = @JoinColumn(name = "market_data_id")
    )
    List<@NotNull MarketDataRecord> marketData;

    public MarketDataReference() {}

    @Override
    public void setDataRecords(List<MarketDataRecord> dataRecords) {
        this.marketData = dataRecords;
    }

    @Override
    public List<MarketDataRecord> getDataRecords() {
        return this.marketData;
    }
}
