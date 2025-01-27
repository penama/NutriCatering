package com.service.catering.application.service;

import com.service.catering.infraestructure.event.command.CommandEntitysEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

public abstract class BaseService {


    @Autowired
    protected ApplicationEventPublisher applicationEventPublisher;

    public void commandHandler( Object source, Object data ){
        CommandEntitysEvent commandEntitysEvent = new CommandEntitysEvent( source, data);
        applicationEventPublisher.publishEvent( commandEntitysEvent );
    }

}
