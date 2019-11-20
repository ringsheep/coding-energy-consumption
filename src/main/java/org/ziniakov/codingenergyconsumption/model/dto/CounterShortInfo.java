package org.ziniakov.codingenergyconsumption.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CounterShortInfo {

    @JsonProperty
    String id;

    @JsonProperty(value = "village_name")
    String villageName;
}
