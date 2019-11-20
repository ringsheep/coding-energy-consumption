package org.ziniakov.codingenergyconsumption.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.ziniakov.codingenergyconsumption.model.domain.Village;

import java.util.List;

@Repository
public interface VillageRepository extends CrudRepository<Village, Long> {

    @Override
    List<Village> findAll();

    @Override
    <S extends Village> S save(S entity);

}
