package com.service.catering.application.service.logger;

import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import io.sentry.Sentry;
import io.sentry.SentryLevel;

@Service
public class LoggerService {

  private String logEntry(String msg) {
    String traceId = MDC.get("traceId");
    String logEntry = String.format("[TraceID: %s] [Message] %s ", traceId, msg);
    return logEntry;
  }

  public void info(Class classs, String message) {
    String msg = logEntry(message);
    org.slf4j.LoggerFactory.getLogger(classs).info(msg);
    Sentry.captureMessage(msg, SentryLevel.INFO);
  }

  public void warn(Class classs, String message) {
    String msg = logEntry(message);
    org.slf4j.LoggerFactory.getLogger(classs).warn(msg);
    Sentry.captureMessage(msg, SentryLevel.WARNING);
  }

  public void debug(Class classs, String message) {
    String msg = logEntry(message);
    org.slf4j.LoggerFactory.getLogger(classs).debug(msg);
    Sentry.captureMessage(msg, SentryLevel.DEBUG);
  }

  public void error(Class classs, String message) {
    String msg = logEntry(message);
    org.slf4j.LoggerFactory.getLogger(classs).error(msg);
    Sentry.captureMessage(msg, SentryLevel.ERROR);
  }

  public void error(Class classs, String message, Throwable e) {
    String msg = logEntry(message);
    org.slf4j.LoggerFactory.getLogger(classs).error(msg, e);
    Sentry.captureMessage(msg, SentryLevel.ERROR);
  }
}
