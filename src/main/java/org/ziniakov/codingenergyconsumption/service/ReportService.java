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

    /**
     * forms a summary report of all energy which was consumed in specified duration from now
     * @param duration which time offset from now we should start our report with?
     * @return report which maps consumption to other entities (e.g., villages)
     */
    public EnergyConsumptionReport getEnergyConsumptionReport(Duration duration) {
        var entries = getAllEntriesInDuration(duration);
        return new EnergyConsumptionReport()
                .setVillageReports(mapEntriesToVillageReports(entries));
    }

    /**
     * @param duration will be used to create a time period before now
     * @return all entities matching specified period
     */
    private List<CounterEntry> getAllEntriesInDuration(Duration duration) {
        var stopDate = dateService.getCurrentDate();
        var startDate = getDateBeforeDate(stopDate, duration);
        return counterEntryRepository.findBetweenDates(startDate, stopDate);
    }

    /**
     * flattens all consumption records to village consumption reports, merging all counters' records in each village
     * @param entries consumption records
     * @return village consumption reports
     */
    private List<VillageReport> mapEntriesToVillageReports(List<CounterEntry> entries) {
        return createVillageConsumptionMap(entries).entrySet()
                .stream()
                .map(villageConsumptionEntry -> new VillageReport()
                        .setVillageName(villageConsumptionEntry.getKey().getName())
                        .setConsumption(villageConsumptionEntry.getValue())
                )
                .collect(Collectors.toList());

    }

    /**
     * finds all villages in records and summs their consumption
     * @param entries consumption records
     * @return map village -> consumption
     */
    private Map<Village, Float> createVillageConsumptionMap(List<CounterEntry> entries) {
        Map<Village, Float> villagesConsumptionMap = new HashMap<>();

        entries.forEach(
                counterEntry -> {
                    var village = counterEntry.getCounter().getVillage();
                    var currentConsumption = villagesConsumptionMap.getOrDefault(village, 0F);
                    villagesConsumptionMap.put(village, currentConsumption + counterEntry.getAmount());
                }
        );

        return villagesConsumptionMap;
    }

    /**
     * @param stopDate date to be subtracted from
     * @param duration time amount to be subtracted
     * @return stopDate minus duration
     */
    private Date getDateBeforeDate(Date stopDate, Duration duration) {
        return Date.from(
                stopDate.toInstant().minusSeconds(duration.toSeconds())
        );
    }
}
