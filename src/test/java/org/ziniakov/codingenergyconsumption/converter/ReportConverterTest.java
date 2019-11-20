package org.ziniakov.codingenergyconsumption.converter;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class ReportConverterTest {

    private ReportConverter converter = new ReportConverter();

    @Test
    void should_convert_seconds() {
        assertEquals(Duration.ofSeconds(15), converter.convert("15s"));
    }

    @Test
    void should_convert_minutes() {
        assertEquals(Duration.ofMinutes(5), converter.convert("5m"));
    }

    @Test
    void should_convert_hours() {
        assertEquals(Duration.ofHours(120), converter.convert("120h"));
    }

    @Test
    void should_convert_days() {
        assertEquals(Duration.ofDays(3), converter.convert("3d"));
    }

    @Test
    void should_throw_exception_on_invalid_format() {
        Exception thrown = assertThrows(
                Exception.class,
                () -> converter.convert("2019y"),
                "Expected convert() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Wrong duration string format. Please use any like these: 60s, 120m, 24h, 3d"));
    }
}