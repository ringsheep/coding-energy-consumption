package org.ziniakov.codingenergyconsumption.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.ziniakov.codingenergyconsumption.model.domain.Counter;
import org.ziniakov.codingenergyconsumption.model.domain.CounterEntry;
import org.ziniakov.codingenergyconsumption.model.dto.CounterEntryRequest;
import org.ziniakov.codingenergyconsumption.model.dto.CounterEntryResponse;
import org.ziniakov.codingenergyconsumption.model.dto.CounterShortInfo;
import org.ziniakov.codingenergyconsumption.repository.CounterEntryRepository;
import org.ziniakov.codingenergyconsumption.repository.CounterRepository;

@Service
@AllArgsConstructor
public class CounterService {

    private CounterRepository counterRepository;
    private CounterEntryRepository counterEntryRepository;
    private DateService dateService;

    public CounterEntryResponse addCounterEntry(CounterEntryRequest request) {
        var entry = counterEntryRepository.save(createCounterEntry(request));
        return createResponse(entry);
    }

    public CounterShortInfo getCounterShortInfo(String id) {
        var counter = getCounter(id);
        return new CounterShortInfo()
                .setId(counter.getId().toString())
                .setVillageName(counter.getVillage().getName());
    }

    @SneakyThrows
    private Counter getCounter(String id) {
        return counterRepository.findById(Long.parseLong(id)).orElseThrow(
                () -> new Exception("No counter with id " + id + " was found")
        );
    }

    private CounterEntry createCounterEntry(CounterEntryRequest request) {
        return new CounterEntry()
                .setCreationDateTime(dateService.getDate())
                .setAmount(request.getAmount())
                .setCounter(getCounter(request.getCounterId()));
    }

    private CounterEntryResponse createResponse(CounterEntry entry) {
        return new CounterEntryResponse().setId(entry.getId().toString());
    }
}
