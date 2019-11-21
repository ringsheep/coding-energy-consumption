package org.ziniakov.codingenergyconsumption.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.ziniakov.codingenergyconsumption.model.domain.Counter;
import org.ziniakov.codingenergyconsumption.model.domain.ConsumptionRecord;
import org.ziniakov.codingenergyconsumption.model.dto.ConsumptionRecordRequest;
import org.ziniakov.codingenergyconsumption.model.dto.ConsumptionRecordResponse;
import org.ziniakov.codingenergyconsumption.model.dto.CounterShortInfo;
import org.ziniakov.codingenergyconsumption.repository.ConsumptionRecordRepository;
import org.ziniakov.codingenergyconsumption.repository.CounterRepository;

@Service
@AllArgsConstructor
public class CounterService {

    private CounterRepository counterRepository;
    private ConsumptionRecordRepository consumptionRecordRepository;
    private DateService dateService;

    /**
     * saves a new energy consumption record if a corresponding counter exists
     * @param request with counterId and consumption amount
     * @return response with created consumption record id
     */
    public ConsumptionRecordResponse addConsumptionRecord(ConsumptionRecordRequest request) {
        var entry = consumptionRecordRepository.save(createConsumptionRecord(request));
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
    private ConsumptionRecord createConsumptionRecord(ConsumptionRecordRequest request) {
        return new ConsumptionRecord()
                .setCreationDateTime(dateService.getCurrentDate())
                .setAmount(request.getAmount())
                .setCounter(getCounter(request.getCounterId()));
    }

    private ConsumptionRecordResponse createResponse(ConsumptionRecord entry) {
        return new ConsumptionRecordResponse().setId(entry.getId().toString());
    }
}
