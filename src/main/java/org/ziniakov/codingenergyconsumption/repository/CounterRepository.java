package org.ziniakov.codingenergyconsumption.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ziniakov.codingenergyconsumption.model.domain.Counter;

import java.util.List;
import java.util.Optional;

@Repository
public interface CounterRepository extends CrudRepository<Counter, Long> {

    @Override
    Optional<Counter> findById(Long counterId);

    @Override
    <S extends Counter> S save(S entity);

    @Override
    List<Counter> findAll();
}
