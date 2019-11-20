package org.ziniakov.codingenergyconsumption.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.ziniakov.codingenergyconsumption.model.domain.CounterEntry;
import org.ziniakov.codingenergyconsumption.model.domain.Village;
import org.ziniakov.codingenergyconsumption.model.dto.EnergyConsumptionReport;
import org.ziniakov.codingenergyconsumption.model.dto.VillageReport;
import org.ziniakov.codingenergyconsumption.repository.CounterEntryRepository;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReportService {

    private CounterEntryRepository counterEntryRepository;
    private DateService dateService;

    public EnergyConsumptionReport getEnergyConsumptionReport(Duration duration) {
        var entries = getAllEntriesInDuration(duration);
        return new EnergyConsumptionReport()
                .setVillageReports(mapEntriesToVillageReports(entries));
    }

    private List<CounterEntry> getAllEntriesInDuration(Duration duration) {
        var stopDate = dateService.getCurrentDate();
        var startDate = getDateBeforeDate(stopDate, duration);
        return counterEntryRepository.findBetweenDates(startDate, stopDate);
    }

    private List<VillageReport> mapEntriesToVillageReports(List<CounterEntry> entries) {
        return createVillageConsumptionMap(entries).entrySet()
                .stream()
                .map(villageConsumptionEntry -> new VillageReport()
                        .setVillageName(villageConsumptionEntry.getKey().getName())
                        .setConsumption(villageConsumptionEntry.getValue())
                )
                .collect(Collectors.toList());

    }

    private Map<Village, Float> createVillageConsumptionMap(List<CounterEntry> entries) {
        Map<Village, Float> villageConsumptionMap = new HashMap<>();

        entries.forEach(
                counterEntry -> {
                    var village = counterEntry.getCounter().getVillage();
                    var currentConsumption = villageConsumptionMap.getOrDefault(village, 0F);
                    villageConsumptionMap.put(village, currentConsumption + counterEntry.getAmount());
                }
        );

        return villageConsumptionMap;
    }

    private Date getDateBeforeDate(Date stopDate, Duration duration) {
        return Date.from(
                stopDate.toInstant().minusSeconds(duration.toSeconds())
        );
    }
}
