package org.ziniakov.codingenergyconsumption.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ziniakov.codingenergyconsumption.model.dto.CounterEntryRequest;
import org.ziniakov.codingenergyconsumption.model.dto.CounterEntryResponse;
import org.ziniakov.codingenergyconsumption.model.dto.CounterShortInfo;
import org.ziniakov.codingenergyconsumption.service.CounterService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
public class CounterController {
    private CounterService service;

    @PostMapping(path = "/counter_callback", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public CounterEntryResponse addCounterEntry(@RequestBody CounterEntryRequest request) {
        return service.addCounterEntry(request);
    }

    @GetMapping(path = "/counter", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public CounterShortInfo getCounterShortInfo(@RequestParam String id) {
        return service.getCounterShortInfo(id);
    }
}
