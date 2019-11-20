package org.ziniakov.codingenergyconsumption.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ziniakov.codingenergyconsumption.model.domain.CounterEntry;

import java.util.Date;
import java.util.List;

@Repository
public interface CounterEntryRepository extends CrudRepository<CounterEntry, String> {

    @Override
    CounterEntry save(CounterEntry counterEntry);

    @Query("SELECT e FROM CounterEntry e WHERE e.creationDateTime >= ?1 AND e.creationDateTime <= ?2")
    List<CounterEntry> findBetweenDates(Date start, Date stop);
}
