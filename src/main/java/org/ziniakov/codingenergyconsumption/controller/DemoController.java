package org.ziniakov.codingenergyconsumption.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ziniakov.codingenergyconsumption.model.domain.Counter;
import org.ziniakov.codingenergyconsumption.model.domain.Village;
import org.ziniakov.codingenergyconsumption.model.dto.CounterEntryRequest;
import org.ziniakov.codingenergyconsumption.repository.CounterRepository;
import org.ziniakov.codingenergyconsumption.repository.VillageRepository;
import org.ziniakov.codingenergyconsumption.service.CounterService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@AllArgsConstructor
public class DemoController {

    private CounterRepository counterRepository;
    private VillageRepository villageRepository;
    private CounterService service;

    /**
     * a handy endpoint to populate data for demonstration purposes
     */
    // TODO: refactor to use a sql file for db initialization
    //  https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-database-initialization
    @GetMapping(path = "/populate_sample_data", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public void populateSampleData() {
        var villageOne = new Village().setName("Villarriba");
        var villageTwo = new Village().setName("Villabajo");

        villageOne = villageRepository.save(villageOne);
        villageTwo = villageRepository.save(villageTwo);

        var counterOne = new Counter().setVillage(villageOne);
        var counterTwo = new Counter().setVillage(villageOne);
        var counterThree = new Counter().setVillage(villageTwo);

        counterOne = counterRepository.save(counterOne);
        counterTwo = counterRepository.save(counterTwo);
        counterThree = counterRepository.save(counterThree);

        service.addCounterEntry(new CounterEntryRequest().setAmount(100.003F).setCounterId(counterOne.getId().toString()));
        service.addCounterEntry(new CounterEntryRequest().setAmount(10.33F).setCounterId(counterOne.getId().toString()));
        service.addCounterEntry(new CounterEntryRequest().setAmount(420.103F).setCounterId(counterTwo.getId().toString()));
        service.addCounterEntry(new CounterEntryRequest().setAmount(30.124F).setCounterId(counterThree.getId().toString()));
    }
}
