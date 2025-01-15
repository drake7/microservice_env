package com.example.openTelemetryPOC.config;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.exporter.otlp.http.metrics.OtlpHttpMetricExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.metrics.Aggregation;
import io.opentelemetry.sdk.metrics.InstrumentSelector;
import io.opentelemetry.sdk.metrics.SdkMeterProvider;
import io.opentelemetry.sdk.metrics.View;
import io.opentelemetry.sdk.metrics.export.PeriodicMetricReader;
import io.opentelemetry.exporter.prometheus.PrometheusHttpServer;
import io.opentelemetry.exporter.otlp.metrics.OtlpGrpcMetricExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class OpenTelemetryConfig {

    @Bean
    public OpenTelemetry openTelemetry() {
        // Set up Prometheus HTTP server
        PrometheusHttpServer prometheusServer = PrometheusHttpServer.builder()
                .setHost("localhost")
                .setPort(9464) // Default Prometheus metrics port
                .build();

        // Use the OTLP HTTP exporter instead of the deprecated Dynatrace exporter
        OtlpHttpMetricExporter otlpExporter = OtlpHttpMetricExporter.builder()
                .setEndpoint("https://unf86453.live.dynatrace.com/api/v2/otlp/v1/metrics")
                .addHeader("Authorization", "Api-Token dt0c01.MWLA7WFXYEOOWI4BBW4F6FBG.FXNI3SZVAE7QYL44E5N5ICQ2CLMZU3DJWJLSOJP6ELYFHBGWVFEIHSDN7IASYKH5") // Replace with a secure method of storing the token
                .build();

        // Set up Meter Provider with Prometheus and OTLP exporters
        SdkMeterProvider meterProvider = SdkMeterProvider.builder()
                // Prometheus metrics
                .registerMetricReader(prometheusServer)
                // Dynatrace metrics via OTLP
                .registerMetricReader(PeriodicMetricReader.builder(otlpExporter)
                        .setInterval(10, TimeUnit.SECONDS) // Export metrics every 60 seconds
                        .build())
                .registerView(
                        InstrumentSelector.builder()
                                .setName("request_duration_deepak_milliseconds") // Match histogram metric
                                .build(),
                        View.builder()
                                .setAggregation(Aggregation.sum()) // Summarize histogram as sum
                                .build()
                )
                .build();

        // Create OpenTelemetry SDK
        OpenTelemetrySdk openTelemetrySdk = OpenTelemetrySdk.builder()
                .setMeterProvider(meterProvider)
                .build();

        // Register globally
        GlobalOpenTelemetry.set(openTelemetrySdk);

        return openTelemetrySdk;
    }
}
