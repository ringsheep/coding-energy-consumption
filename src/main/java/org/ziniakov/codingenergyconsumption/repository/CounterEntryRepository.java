package org.ziniakov.codingenergyconsumption.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ziniakov.codingenergyconsumption.model.domain.CounterEntry;

@Repository
public interface CounterEntryRepository extends CrudRepository<CounterEntry, String> {
    @Override
    CounterEntry save(CounterEntry counterEntry);
}
