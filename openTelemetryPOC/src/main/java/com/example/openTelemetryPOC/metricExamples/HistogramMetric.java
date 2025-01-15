package com.example.openTelemetryPOC.metricExamples;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.metrics.Meter;
import org.springframework.stereotype.Component;

@Component
public class HistogramMetric {

    private volatile long latestRequestDuration = 0; // Store the latest request duration

    public HistogramMetric(OpenTelemetry openTelemetry) {
        // Create a gauge for tracking the latest request duration
        Meter meter = openTelemetry.getMeter("example-meter");
        meter.gaugeBuilder("latest_request_duration_deepak")
                .setDescription("Tracks the latest request duration")
                .setUnit("ms") // Unit is milliseconds
                .ofLongs()
                .buildWithCallback(measurement -> measurement.record(latestRequestDuration));
    }

    // Method to update the gauge value
    public void updateDuration(long value) {
        this.latestRequestDuration = value; // Update the latest request duration
    }
}
