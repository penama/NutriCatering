package com.service.catering.infraestructure.servicebus;

import com.service.catering.application.model.event.EventDto;

public interface IProducerBus {
  public void sendMessage(EventDto eventDto);
}
