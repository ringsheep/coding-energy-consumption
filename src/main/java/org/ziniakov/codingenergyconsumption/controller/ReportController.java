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

    /**
     * forms a summary report of all energy which was consumed in specified duration from now
     * supports Long time amount and seconds(s), minutes(m), hours(h) and days(d) as units.
     *
     * @param duration which time offset from now we should start our report with?
     * @return report which maps consumption to other entities (e.g., villages)
     */
    @GetMapping(path = "/consumption_report", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public EnergyConsumptionReport getEnergyConsumptionReport(@RequestParam String duration) {
        return service.getEnergyConsumptionReport(converter.convert(duration));
    }

}
