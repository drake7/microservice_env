package com.example.openTelemetryPOC.metricExamples;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.metrics.LongHistogram;
import org.springframework.stereotype.Component;

@Component
public class HistogramMetric {

    private final LongHistogram requestDurationHistogram;

    public HistogramMetric(OpenTelemetry openTelemetry) {
        // Create a histogram for recording request durations
        Meter meter = openTelemetry.getMeter("example-meter");
        this.requestDurationHistogram = meter.histogramBuilder("request_duration")
                .setDescription("Records request durations")
                .setUnit("ms") // The unit is milliseconds
                .ofLongs()
                .build();
    }

    // Method to record a value (such as request duration) in the histogram
    public void recordValue(long value) {
        requestDurationHistogram.record(value);  // Record the value in the histogram
    }
}
