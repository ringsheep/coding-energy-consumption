package org.ziniakov.codingenergyconsumption.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.ziniakov.codingenergyconsumption.converter.ReportConverter;
import org.ziniakov.codingenergyconsumption.model.dto.EnergyConsumptionReport;
import org.ziniakov.codingenergyconsumption.service.ReportService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
public class ReportController {

    private ReportService service;

    private ReportConverter converter;

    @GetMapping(path = "/consumption_report", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public EnergyConsumptionReport getEnergyConsumptionReport(@RequestParam String duration) {
        return service.getEnergyConsumptionReport(converter.convert(duration));
    }

}
