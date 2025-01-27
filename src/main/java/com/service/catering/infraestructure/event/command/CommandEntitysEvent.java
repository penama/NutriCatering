package com.service.catering.infraestructure.event.command;


import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class CommandEntitysEvent extends ApplicationEvent {

    private Object data;

    public CommandEntitysEvent(Object source, Object data) {
        super(source);
        this.data = data;
    }

}
