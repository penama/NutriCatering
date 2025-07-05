package com.service.catering.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microsoft.applicationinsights.TelemetryClient;

@Configuration
public class TelemetryConfig {

  @Bean
  public TelemetryClient telemetryClient() {
    return new TelemetryClient();
  }
}
