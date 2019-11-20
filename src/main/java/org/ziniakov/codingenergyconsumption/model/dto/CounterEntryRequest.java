package org.ziniakov.codingenergyconsumption.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CounterEntryRequest {

    @JsonProperty("counter_id")
    private String counterId;

    @JsonProperty
    private Float amount;
}
