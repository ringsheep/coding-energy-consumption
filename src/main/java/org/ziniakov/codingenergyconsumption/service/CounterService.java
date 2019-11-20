package org.ziniakov.codingenergyconsumption.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.ziniakov.codingenergyconsumption.model.domain.Counter;
import org.ziniakov.codingenergyconsumption.model.domain.CounterEntry;
import org.ziniakov.codingenergyconsumption.model.dto.CounterEntryRequest;
import org.ziniakov.codingenergyconsumption.model.dto.CounterEntryResponse;
import org.ziniakov.codingenergyconsumption.repository.CounterEntryRepository;
import org.ziniakov.codingenergyconsumption.repository.CounterRepository;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class CounterService {
    private CounterRepository counterRepository;
    private CounterEntryRepository counterEntryRepository;

    public CounterEntryResponse addCounterEntry(CounterEntryRequest request) {
        CounterEntry entry = counterEntryRepository.save(createCounterEntry(request));
        return createResponse(entry);
    }

    @SneakyThrows
    public Counter getCounter(String id) {
        return counterRepository.findById(id).orElseThrow(
                () -> new Exception("No counter with id " + id + " was found")
        );
    }

    private CounterEntry createCounterEntry(CounterEntryRequest request) {
        return CounterEntry.builder()
                .creationDateTime(new Date())
                .amount(request.getAmount())
                .counter(getCounter(request.getCounterId()))
                .build();
    }

    private CounterEntryResponse createResponse(CounterEntry entry) {
        return CounterEntryResponse.builder()
                .id(entry.getId())
                .build();
    }
}
