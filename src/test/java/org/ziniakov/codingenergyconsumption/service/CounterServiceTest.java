package org.ziniakov.codingenergyconsumption.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.ziniakov.codingenergyconsumption.model.domain.Counter;
import org.ziniakov.codingenergyconsumption.model.domain.CounterEntry;
import org.ziniakov.codingenergyconsumption.model.dto.CounterEntryRequest;
import org.ziniakov.codingenergyconsumption.model.dto.CounterEntryResponse;
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

    private Date date = Date.from(Instant.ofEpochSecond(1574281064));
    private Counter counter = Counter.builder()
            .id("counter 1")
            .build();
    private CounterEntry counterEntryToCreate = CounterEntry.builder()
            .amount(100.00f)
            .counter(counter)
            .creationDateTime(date)
            .build();
    private CounterEntry createdCounterEntry = CounterEntry.builder()
            .amount(100.00f)
            .id("entry 1")
            .counter(counter)
            .creationDateTime(date)
            .build();
    private CounterEntryResponse counterEntryResponse = CounterEntryResponse.builder()
            .id("entry 1")
            .build();

    @BeforeEach
    void setUp() {
        service = new CounterService(counterRepository, counterEntryRepository, dateService);
        doReturn(date).when(dateService).getDate();
        doReturn(Optional.ofNullable(counter)).when(counterRepository).findById("counter 1");
    }

    @Test
    void addCounterEntry_should_add_counterEntry() {
        doReturn(createdCounterEntry).when(counterEntryRepository).save(counterEntryToCreate);

        var result = service.addCounterEntry(
                CounterEntryRequest.builder()
                .amount(100.00f)
                .counterId("counter 1")
                .build()
        );

        assertEquals(counterEntryResponse, result);
    }

    @Test
    void getCounter_should_return_correct_counter() {
        var result = service.getCounter("counter 1");

        assertEquals(counter, result);
    }
}