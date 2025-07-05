package com.service.catering.application.service.events;

import com.service.catering.application.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.service.catering.application.model.event.EventDto;

@Service
public class SubscribersEventService extends BaseService {

  public static final String EVENT_USER_CREATED = "USER_CREATED";
  public static final String EVENT_NUTRITIONAL_PLAN_CREATED = "NUTRITIONAL_PLAN_CREATED";
  public static final String EVENT_USER_ADRESS_UPDATED = "USER_ADDRESS_UPDATE";
  public static final String EVENT_DELIVERY_DATE_UPDATED = "DELIVERY_DATE_UPDATE";

  @Autowired private CustomerCreatedEventService customerCreatedEventService;
  @Autowired private NutritionalPlanCreatedEventService nutritionalPlanCreatedEventService;
  @Autowired private CustomerAddressUpdateEventService customerAddressUpdateEventService;
  @Autowired private DeliveryDateUpdateEventService deliveryDateUpdateEventService;

  public void procesarMensaje(String mensaje) {

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    EventDto event = null;
    try {
      event = mapper.readValue(mensaje, EventDto.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    if (event.getEventType().equalsIgnoreCase(EVENT_USER_CREATED))
      customerCreatedEventService.customerCreatedEvent(event);
    else if (event.getEventType().equalsIgnoreCase(EVENT_NUTRITIONAL_PLAN_CREATED))
      nutritionalPlanCreatedEventService.customerCreatedEvent(event);
    else if (event.getEventType().equalsIgnoreCase(EVENT_USER_ADRESS_UPDATED))
      customerAddressUpdateEventService.customerAddressUpdatedEvent(event);
    else if (event.getEventType().equalsIgnoreCase(EVENT_DELIVERY_DATE_UPDATED))
      deliveryDateUpdateEventService.deliveryDateUpdateUpdateEvent(event);
    else {
		log.info( this.getClass(), "Evento no mapeado: " + mensaje );
    }
  }
}
