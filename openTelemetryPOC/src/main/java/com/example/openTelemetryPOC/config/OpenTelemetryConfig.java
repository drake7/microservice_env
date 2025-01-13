package com.example.openTelemetryPOC.config;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.metrics.export.MetricExporter;
import io.opentelemetry.sdk.metrics.export.PeriodicMetricReader;
import io.opentelemetry.exporter.prometheus.PrometheusHttpServer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenTelemetryConfig {

    @Bean
    public OpenTelemetry openTelemetry() {
        // Set up Prometheus HTTP server
        PrometheusHttpServer server = PrometheusHttpServer.builder()
                .setHost("localhost")
                .setPort(9464) // Default Prometheus metrics port
                .build();

        // Use PrometheusHttpServer as the metric reader
        SdkMeterProvider meterProvider = SdkMeterProvider.builder()
                .registerMetricReader(server)
                .build();

        // Create OpenTelemetry SDK
        OpenTelemetrySdk openTelemetrySdk = OpenTelemetrySdk.builder()
                .setMeterProvider(meterProvider)
                .build();

        // Register globally
        GlobalOpenTelemetry.set(openTelemetrySdk);

        System.out.println("Prometheus HTTP Server is running on http://localhost:9464/metrics");

        return openTelemetrySdk;
    }
}
