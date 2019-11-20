package org.ziniakov.codingenergyconsumption.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CounterEntryRequest {

    @JsonProperty("counter_id")
    private String counterId;

    @JsonProperty
    private Float amount;
}
