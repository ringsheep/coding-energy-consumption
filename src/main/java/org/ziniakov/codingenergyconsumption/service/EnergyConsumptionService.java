package org.ziniakov.codingenergyconsumption.service;

import org.ziniakov.codingenergyconsumption.dto.EnergyConsumptionReport;
import org.ziniakov.codingenergyconsumption.dto.ReportDuration;

public class EnergyConsumptionService {
    public EnergyConsumptionReport getEnergyConsumptionReport(ReportDuration duration) {
        return new EnergyConsumptionReport();
    }
}
