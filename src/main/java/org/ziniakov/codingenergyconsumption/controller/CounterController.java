package org.ziniakov.codingenergyconsumption.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ziniakov.codingenergyconsumption.model.dto.ConsumptionRecordRequest;
import org.ziniakov.codingenergyconsumption.model.dto.ConsumptionRecordResponse;
import org.ziniakov.codingenergyconsumption.model.dto.CounterShortInfo;
import org.ziniakov.codingenergyconsumption.service.CounterService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
public class CounterController {
    private CounterService service;

    /**
     * saves a new energy consumption record if a corresponding counter exists
     *
     * @param request with counterId and consumption amount
     * @return response with created consumption record id
     */
    @PostMapping(path = "/counter_callback", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ConsumptionRecordResponse addConsumptionRecord(@RequestBody ConsumptionRecordRequest request) {
        return service.addConsumptionRecord(request);
    }

    /**
     * tells us to which village this counter is linked
     *
     * @param id of an existing counter
     * @return short info with counter id and village name
     */
    @GetMapping(path = "/counter", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public CounterShortInfo getCounterShortInfo(@RequestParam String id) {
        return service.getCounterShortInfo(id);
    }
}
