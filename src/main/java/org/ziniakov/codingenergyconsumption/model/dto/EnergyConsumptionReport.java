package org.ziniakov.codingenergyconsumption.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class EnergyConsumptionReport {

    @JsonProperty(value = "villages")
    private List<VillageReport> villageReports;

}
