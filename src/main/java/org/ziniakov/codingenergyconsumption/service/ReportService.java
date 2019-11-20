package org.ziniakov.codingenergyconsumption.service;

import org.ziniakov.codingenergyconsumption.model.dto.EnergyConsumptionReport;
import org.ziniakov.codingenergyconsumption.model.dto.ReportTimePeriod;

public class ReportService {
    public EnergyConsumptionReport getEnergyConsumptionReport(ReportTimePeriod timePeriod) {
        return new EnergyConsumptionReport();
    }
}
