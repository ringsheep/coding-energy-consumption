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

    /**
     * saves a new energy consumption record if a corresponding counter exists
     * @param request with counterId and consumption amount
     * @return response with created consumption record id
     */
    public CounterEntryResponse addCounterEntry(CounterEntryRequest request) {
        var entry = counterEntryRepository.save(createCounterEntry(request));
        return createResponse(entry);
    }

    /**
     * tells us to which village this counter is linked
     * @param id of an existing counter
     * @return short info with counter id and village name
     */
    public CounterShortInfo getCounterShortInfo(String id) {
        var counter = getCounter(id);
        return new CounterShortInfo()
                .setId(counter.getId().toString())
                .setVillageName(counter.getVillage().getName());
    }

    /**
     * get full info of a counter
     * @param id of an existing counter
     * @return counter db model
     */
    @SneakyThrows
    private Counter getCounter(String id) {
        return counterRepository.findById(Long.parseLong(id)).orElseThrow(
                () -> new Exception("No counter with id " + id + " was found")
        );
    }

    /**
     * creates a new record linked with current time
     * @param request an input model with new recording data
     * @return created record
     */
    private CounterEntry createCounterEntry(CounterEntryRequest request) {
        return new CounterEntry()
                .setCreationDateTime(dateService.getCurrentDate())
                .setAmount(request.getAmount())
                .setCounter(getCounter(request.getCounterId()));
    }

    private CounterEntryResponse createResponse(CounterEntry entry) {
        return new CounterEntryResponse().setId(entry.getId().toString());
    }
}
