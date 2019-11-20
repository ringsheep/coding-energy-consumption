package org.ziniakov.codingenergyconsumption.converter;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.function.Function;

import static java.util.Map.entry;

@Component
public class ReportConverter {

    private static final Map<String, Function<Long, Duration>> measurementToDurationMapping = Map.ofEntries(
            entry("s", Duration::ofSeconds),
            entry("m", Duration::ofMinutes),
            entry("h", Duration::ofHours),
            entry("d", Duration::ofDays)
    );

    @SneakyThrows
    public Duration convert(String duration) {
        var durationValue = duration.substring(0, duration.length() - 1);
        var durationMeasure = duration.substring(duration.length() - 1);

        // TODO: replace validation by smth more reliable and flexible like regex
        if (measurementToDurationMapping.get(durationMeasure) == null) {
            throw new Exception("Wrong duration string format. Please use any like these: 60s, 120m, 24h, 3d");
        }

        return measurementToDurationMapping.get(durationMeasure)
                .apply(Long.parseLong(durationValue));
    }
}
