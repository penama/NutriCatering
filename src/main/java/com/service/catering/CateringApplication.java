package com.service.catering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.sentry.Sentry;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CateringApplication {

  public static void main(String[] args) {
    Sentry.captureMessage("Prueba");
    SpringApplication.run(CateringApplication.class, args);
  }
}
