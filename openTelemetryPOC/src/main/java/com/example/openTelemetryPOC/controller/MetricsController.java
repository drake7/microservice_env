package com.example.openTelemetryPOC.controller;

import com.example.openTelemetryPOC.metricExamples.GaugeMetrics;
import com.example.openTelemetryPOC.metricExamples.HistogramMetric;
import com.example.openTelemetryPOC.metricExamples.TimerMetric;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetricsController {

    private final LongCounter requestCounter;
    private final GaugeMetrics gaugeMetric;
    private final TimerMetric timerMetric;
    private final HistogramMetric histogramMetric;

    public MetricsController(OpenTelemetry openTelemetry, GaugeMetrics gaugeMetric, TimerMetric timerMetric, HistogramMetric histogramMetric) {
        Meter meter = openTelemetry.getMeter("example-meter");

        // Initialize Counter Metric
        this.requestCounter = meter.counterBuilder("example_request_count")
                .setDescription("Counts the number of requests to the /hello endpoint")
                .setUnit("requests")
                .build();

        this.gaugeMetric = gaugeMetric;       // Inject GaugeMetric
        this.timerMetric = timerMetric;       // Inject TimerMetric
        this.histogramMetric = histogramMetric; // Inject HistogramMetric
    }

    @GetMapping("/hello")
    public String hello() {
        // Increment the counter
        requestCounter.add(1);

        // Update Gauge Metric
        gaugeMetric.setActiveUsers((long) (Math.random() * 100)); // Random active users for testing

        // Record Timer Metric
        timerMetric.recordExecutionTime(() -> {
            try {
                Thread.sleep((long) (Math.random() * 100)); // Simulate processing time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // Record Histogram Metric
        histogramMetric.recordValue((long) (Math.random() * 500)); // Random value for testing

        return "Hello, OpenTelemetry!";
    }

    @PostConstruct
    public void init() {
        System.out.println("MetricsController initialized");
    }
}
