package com.service.catering.application.service.scheduled;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app.scheduled.cron")
public class CronProperties {
  private String expression;
}
