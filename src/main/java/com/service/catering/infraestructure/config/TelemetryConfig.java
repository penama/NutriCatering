package com.service.catering.infraestructure.config;

import com.microsoft.applicationinsights.extensibility.context.CloudContext;
import com.microsoft.applicationinsights.telemetry.TelemetryContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.microsoft.applicationinsights.TelemetryClient;

@Configuration
public class TelemetryConfig {

	@Value("${applicationinsights.role.name}")
	String rolName;

  @Bean
  public TelemetryClient telemetryClient() {
	  TelemetryClient client = new TelemetryClient();
	  TelemetryContext context = client.getContext();
	  CloudContext cloudContext = context.getCloud();
	  cloudContext.setRole( rolName );  // Aquí se asigna el nombre de rol
	  return client;
  }
}
