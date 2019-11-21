package org.ziniakov.codingenergyconsumption.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.ziniakov.codingenergyconsumption.model.domain.ConsumptionRecord;
import org.ziniakov.codingenergyconsumption.model.domain.Counter;
import org.ziniakov.codingenergyconsumption.model.domain.Village;
import org.ziniakov.codingenergyconsumption.model.dto.EnergyConsumptionReport;
import org.ziniakov.codingenergyconsumption.model.dto.VillageReport;
import org.ziniakov.codingenergyconsumption.repository.ConsumptionRecordRepository;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

class ReportServiceTest {

    private ConsumptionRecordRepository consumptionRecordRepository = Mockito.mock(ConsumptionRecordRepository.class);
    private DateService dateService = Mockito.mock(DateService.class);
    private ReportService service;

    // TODO: moved to separate test data class
    private Date date = Date.from(Instant.ofEpochSecond(1574281064));
    private Village village = new Village().setId(2L).setName("Villarriba");
    private Counter counter = new Counter().setId(1L).setVillage(village);
    private List<ConsumptionRecord> createdConsumptionRecords = Arrays.asList(
            new ConsumptionRecord()
                    .setAmount(100.00f)
                    .setId(1L)
                    .setCounter(counter)
                    .setCreationDateTime(date),
            new ConsumptionRecord()
                    .setAmount(100.00f)
                    .setId(3L)
                    .setCounter(counter)
                    .setCreationDateTime(date)
    );
    private EnergyConsumptionReport energyConsumptionReport = new EnergyConsumptionReport().setVillageReports(
            Collections.singletonList(
                    new VillageReport().setConsumption(200.00f).setVillageName("Villarriba")
            )
    );

    @BeforeEach
    void setUp() {
        service = new ReportService(consumptionRecordRepository, dateService);
        doReturn(date).when(dateService).getCurrentDate();
    }

    @Test
    void getEnergyConsumptionReport_should_give_a_report() {
        doReturn(createdConsumptionRecords).when(consumptionRecordRepository).findBetweenDates(date, date);

        var result = service.getEnergyConsumptionReport(Duration.ZERO);

        assertEquals(energyConsumptionReport, result);
    }
}