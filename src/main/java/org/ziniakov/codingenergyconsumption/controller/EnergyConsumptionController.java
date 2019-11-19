package org.ziniakov.codingenergyconsumption.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ziniakov.codingenergyconsumption.converter.ReportConverter;
import org.ziniakov.codingenergyconsumption.dto.EnergyConsumptionReport;
import org.ziniakov.codingenergyconsumption.service.EnergyConsumptionService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class EnergyConsumptionController {
    private EnergyConsumptionService service;

    private ReportConverter converter;

    @GetMapping(path = "/consumption_report", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public EnergyConsumptionReport getEnergyConsumptionReport(@RequestParam String duration) {
        return service.getEnergyConsumptionReport(converter.convert(duration));
    }
}
