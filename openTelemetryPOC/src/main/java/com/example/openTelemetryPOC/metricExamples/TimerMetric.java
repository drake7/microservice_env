package com.example.openTelemetryPOC.metricExamples;


import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.metrics.LongHistogram;
import org.springframework.stereotype.Component;

@Component
public class TimerMetric {

    private final LongHistogram executionTimeHistogram;

    public TimerMetric(OpenTelemetry openTelemetry) {
        // Get the meter from OpenTelemetry
        Meter meter = openTelemetry.getMeter("timer-metric");

        // Create a histogram to record execution times
        this.executionTimeHistogram = meter.histogramBuilder("execution_time_deepak")
                .setDescription("Records execution time of tasks")
                .setUnit("ms") // The unit of measurement is milliseconds
                .ofLongs()
                .build();
    }

    // Method to record the execution time of a task (in milliseconds)
    public void recordExecutionTime(Runnable task) {
        long startTime = System.currentTimeMillis();  // Record start time
        try {
            task.run();  // Run the task
        } finally {
            long endTime = System.currentTimeMillis();  // Record end time
            executionTimeHistogram.record(endTime - startTime);  // Record the time taken in the histogram
        }
    }
}

