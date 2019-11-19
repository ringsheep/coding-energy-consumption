package org.ziniakov.codingenergyconsumption.service;

import org.springframework.stereotype.Service;
import org.ziniakov.codingenergyconsumption.dto.Counter;
import org.ziniakov.codingenergyconsumption.dto.CounterEntryRequest;
import org.ziniakov.codingenergyconsumption.dto.CounterEntryResponse;

@Service
public class CounterService {
    public CounterEntryResponse addCounterEntry(CounterEntryRequest entry) {
        return new CounterEntryResponse();
    }

    public Counter getCounter(String id) {
        return new Counter();
    }
}
