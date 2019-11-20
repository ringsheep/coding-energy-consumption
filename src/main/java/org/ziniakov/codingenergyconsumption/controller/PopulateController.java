package org.ziniakov.codingenergyconsumption.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ziniakov.codingenergyconsumption.model.domain.Counter;
import org.ziniakov.codingenergyconsumption.model.domain.Village;
import org.ziniakov.codingenergyconsumption.repository.CounterRepository;
import org.ziniakov.codingenergyconsumption.repository.VillageRepository;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
public class PopulateController {

    private CounterRepository counterRepository;
    private VillageRepository villageRepository;

    // TODO: refactor to use a sql file for db initialization
    //  https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-database-initialization
    @GetMapping(path = "/populate_sample_data", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public void populateSampleData() {
        var villageOne = new Village().setName("Villarriba");
        var villageTwo = new Village().setName("Villabajo");

        villageOne = villageRepository.save(villageOne);
        villageTwo = villageRepository.save(villageTwo);

        counterRepository.save(new Counter().setVillage(villageOne));
        counterRepository.save(new Counter().setVillage(villageOne));
        counterRepository.save(new Counter().setVillage(villageTwo));
    }
}
