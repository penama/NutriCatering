package com.service.catering.infraestructure.servicebus;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties("azure-service-bus")
public class ServiceBusConfig {

  private String connectionString;
  private String topicName;
  private String subscriptionName;
}
