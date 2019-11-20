package org.ziniakov.codingenergyconsumption.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.ziniakov.codingenergyconsumption.model.domain.Counter;
import org.ziniakov.codingenergyconsumption.model.domain.CounterEntry;
import org.ziniakov.codingenergyconsumption.model.domain.Village;
import org.ziniakov.codingenergyconsumption.model.dto.CounterEntryRequest;
import org.ziniakov.codingenergyconsumption.model.dto.CounterEntryResponse;
import org.ziniakov.codingenergyconsumption.model.dto.CounterShortInfo;
import org.ziniakov.codingenergyconsumption.repository.CounterEntryRepository;
import org.ziniakov.codingenergyconsumption.repository.CounterRepository;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

class CounterServiceTest {

    private CounterRepository counterRepository = Mockito.mock(CounterRepository.class);
    private CounterEntryRepository counterEntryRepository = Mockito.mock(CounterEntryRepository.class);
    private DateService dateService = Mockito.mock(DateService.class);

    private CounterService service;

    // TODO: moved to separate test data class
    private Date date = Date.from(Instant.ofEpochSecond(1574281064));
    private Village village = new Village().setId(2L).setName("Villarriba");
    private CounterShortInfo counterShortInfo = new CounterShortInfo().setId("1").setVillageName(village.getName());
    private Counter counter = new Counter().setId(1L).setVillage(village);
    private CounterEntry counterEntryToCreate = new CounterEntry()
            .setAmount(100.00f)
            .setCounter(counter)
            .setCreationDateTime(date);
    private CounterEntry createdCounterEntry = new CounterEntry()
            .setAmount(100.00f)
            .setId(1L)
            .setCounter(counter)
            .setCreationDateTime(date);
    private CounterEntryResponse counterEntryResponse = new CounterEntryResponse().setId("1");

    @BeforeEach
    void setUp() {
        service = new CounterService(counterRepository, counterEntryRepository, dateService);
        doReturn(date).when(dateService).getCurrentDate();
        doReturn(Optional.ofNullable(counter)).when(counterRepository).findById(1L);
    }

    @Test
    void addCounterEntry_should_add_counterEntry() {
        doReturn(createdCounterEntry).when(counterEntryRepository).save(counterEntryToCreate);

        var result = service.addCounterEntry(
                new CounterEntryRequest()
                .setAmount(100.00f)
                .setCounterId("1")
        );

        assertEquals(counterEntryResponse, result);
    }

    // TODO: add case for unexisting counter

    @Test
    void getCounter_should_return_correct_counter() {
        var result = service.getCounterShortInfo("1");

        assertEquals(counterShortInfo, result);
    }
}