package org.ziniakov.codingenergyconsumption.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ziniakov.codingenergyconsumption.model.domain.Village;

@Repository
public interface VillageRepository extends CrudRepository<Village, Long> {

    @Override
    Iterable<Village> findAll();

    @Override
    <S extends Village> S save(S entity);

}
