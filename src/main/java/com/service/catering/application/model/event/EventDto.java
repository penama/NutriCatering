package com.service.catering.application.model.event;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDto {

  private String eventType;
  private String eventVersion;
  private String timestamp;
  private Map<String, String> body;
  private String source;
}
