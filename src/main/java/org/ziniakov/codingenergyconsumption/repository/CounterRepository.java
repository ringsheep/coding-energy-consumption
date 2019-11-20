package org.ziniakov.codingenergyconsumption.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ziniakov.codingenergyconsumption.model.domain.Counter;

import java.util.Optional;

@Repository
public interface CounterRepository extends CrudRepository<Counter, String> {
    @Override
    Optional<Counter> findById(String counterId);
}
