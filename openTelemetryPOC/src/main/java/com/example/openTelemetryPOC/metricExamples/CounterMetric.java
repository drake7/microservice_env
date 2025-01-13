package com.example.openTelemetryPOC.metricExamples;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import org.springframework.stereotype.Component;

@Component
public class CounterMetric {

    private final LongCounter requestCounter;

    public CounterMetric(OpenTelemetry openTelemetry) {
        Meter meter = openTelemetry.getMeter("counter-metric");
        this.requestCounter = meter.counterBuilder("requests_count")
                .setDescription("Counts the number of requests")
                .setUnit("requests")
                .build();
    }

    public void increment() {
        requestCounter.add(1);
    }
}
