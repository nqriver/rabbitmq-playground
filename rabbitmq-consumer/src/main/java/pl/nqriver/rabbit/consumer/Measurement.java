package pl.nqriver.rabbit.consumer;

import java.time.Instant;

public record Measurement(
        double temperature,
        double humidity,
        double pressure,
        double windSpeed,
        String location,
        Instant timestamp
) {
}

