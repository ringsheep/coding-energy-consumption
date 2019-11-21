package org.ziniakov.codingenergyconsumption.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.ziniakov.codingenergyconsumption.model.domain.Counter;
import org.ziniakov.codingenergyconsumption.model.domain.ConsumptionRecord;
import org.ziniakov.codingenergyconsumption.model.domain.Village;
import org.ziniakov.codingenergyconsumption.model.dto.ConsumptionRecordRequest;
import org.ziniakov.codingenergyconsumption.model.dto.ConsumptionRecordResponse;
import org.ziniakov.codingenergyconsumption.model.dto.CounterShortInfo;
import org.ziniakov.codingenergyconsumption.repository.ConsumptionRecordRepository;
import org.ziniakov.codingenergyconsumption.repository.CounterRepository;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

class CounterServiceTest {

    private CounterRepository counterRepository = Mockito.mock(CounterRepository.class);
    private ConsumptionRecordRepository consumptionRecordRepository = Mockito.mock(ConsumptionRecordRepository.class);
    private DateService dateService = Mockito.mock(DateService.class);

    private CounterService service;

    // TODO: moved to separate test data class
    private Date date = Date.from(Instant.ofEpochSecond(1574281064));
    private Village village = new Village().setId(2L).setName("Villarriba");
    private CounterShortInfo counterShortInfo = new CounterShortInfo().setId("1").setVillageName(village.getName());
    private Counter counter = new Counter().setId(1L).setVillage(village);
    private ConsumptionRecord consumptionRecordToCreate = new ConsumptionRecord()
            .setAmount(100.00f)
            .setCounter(counter)
            .setCreationDateTime(date);
    private ConsumptionRecord createdConsumptionRecord = new ConsumptionRecord()
            .setAmount(100.00f)
            .setId(1L)
            .setCounter(counter)
            .setCreationDateTime(date);
    private ConsumptionRecordResponse consumptionRecordResponse = new ConsumptionRecordResponse().setId("1");

    @BeforeEach
    void setUp() {
        service = new CounterService(counterRepository, consumptionRecordRepository, dateService);
        doReturn(date).when(dateService).getCurrentDate();
        doReturn(Optional.ofNullable(counter)).when(counterRepository).findById(1L);
    }

    @Test
    void addConsumptionRecord_should_add_record() {
        doReturn(createdConsumptionRecord).when(consumptionRecordRepository).save(consumptionRecordToCreate);

        var result = service.addConsumptionRecord(
                new ConsumptionRecordRequest()
                .setAmount(100.00f)
                .setCounterId("1")
        );

        assertEquals(consumptionRecordResponse, result);
    }

    // TODO: add case for unexisting counter

    @Test
    void getCounter_should_return_correct_counter() {
        var result = service.getCounterShortInfo("1");

        assertEquals(counterShortInfo, result);
    }
}