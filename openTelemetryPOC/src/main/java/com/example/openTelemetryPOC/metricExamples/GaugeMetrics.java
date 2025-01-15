package com.example.openTelemetryPOC.metricExamples;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.metrics.Meter;
import org.springframework.stereotype.Component;

@Component
public class GaugeMetrics {

    private long activeUsers = 0;

    public GaugeMetrics(OpenTelemetry openTelemetry) {
        Meter meter = openTelemetry.getMeter("gauge-metric");

        // Use callback to provide the current value of activeUsers
        meter.gaugeBuilder("active_users_deepak")
                .setDescription("Tracks the number of active users")
                .setUnit("users")
                .ofLongs()
                .buildWithCallback(measurement -> measurement.record(activeUsers));
    }

    public void setActiveUsers(long activeUsers) {
        this.activeUsers = activeUsers;
    }
}
