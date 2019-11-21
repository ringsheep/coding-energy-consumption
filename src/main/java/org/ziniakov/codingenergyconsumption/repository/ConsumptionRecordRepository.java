package org.ziniakov.codingenergyconsumption.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ziniakov.codingenergyconsumption.model.domain.ConsumptionRecord;

import java.util.Date;
import java.util.List;

@Repository
public interface ConsumptionRecordRepository extends CrudRepository<ConsumptionRecord, String> {

    @Override
    ConsumptionRecord save(ConsumptionRecord consumptionRecord);

    @Query("SELECT e FROM ConsumptionRecord e WHERE e.creationDateTime >= ?1 AND e.creationDateTime <= ?2")
    List<ConsumptionRecord> findBetweenDates(Date start, Date stop);
}
