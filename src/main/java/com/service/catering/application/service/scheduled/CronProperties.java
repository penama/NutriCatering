package com.service.catering.application.service.scheduled;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties( prefix = "app.scheduled.cron")
public class CronProperties {
	private String expression;
}
