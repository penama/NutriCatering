package com.service.catering.application.service.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.service.catering.application.model.event.EventDto;

@Service
public class EventService {

  public static final String EVENT_USER_CREATED = "USER_CREATED";
  public static final String EVENT_NUTRITIONAL_PLAN_CREATED = "NUTRITIONAL_PLAN_CREATED";
  public static final String EVENT_USER_ADRESS_UPDATED = "USER_ADRESS_UPDATED";
  public static final String EVENT_DELIVERY_DATE_UPDATED = "DELIVERY_DATE_UPDATED";

  @Autowired private CustomerCreatedEventService customerCreatedEventService;
  private NutritionalPlanCreatedEventService nutritionalPlanCreatedEventService;
  private CustomerAddressUpdateEventService customerAddressUpdateEventService;
  private DeliveryDateUpdateEventService deliveryDateUpdateEventService;

  public void procesarMensaje(String mensaje) throws JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule()); // Para manejar Instant
    EventDto event = mapper.readValue(mensaje, EventDto.class);

    if (event.getEventType().equalsIgnoreCase(EVENT_USER_CREATED))
      customerCreatedEventService.customerCreatedEvent(event);
    if (event.getEventType().equalsIgnoreCase(EVENT_NUTRITIONAL_PLAN_CREATED))
      nutritionalPlanCreatedEventService.customerCreatedEvent(event);
    if (event.getEventType().equalsIgnoreCase(EVENT_USER_ADRESS_UPDATED))
      customerAddressUpdateEventService.customerAddressUpdatedEvent(event);
    if (event.getEventType().equalsIgnoreCase(EVENT_DELIVERY_DATE_UPDATED))
      deliveryDateUpdateEventService.deliveryDateUpdateUpdateEvent(event);
  }
}
