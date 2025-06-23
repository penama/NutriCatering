package com.service.catering.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import com.service.catering.infraestructure.event.command.CommandEntitysEvent;

public abstract class BaseCommandHandler {

  @Autowired protected ApplicationEventPublisher applicationEventPublisher;

  public void commandHandler(Object source, Object data) {
    CommandEntitysEvent commandEntitysEvent = new CommandEntitysEvent(source, data);
    applicationEventPublisher.publishEvent(commandEntitysEvent);
  }
}
