package com.service.catering.application.model.event;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDto {

  private String eventType;
  private String eventVersion;
  private String timestamp;
  private Map<String, Object> body;
  private String source;
}
