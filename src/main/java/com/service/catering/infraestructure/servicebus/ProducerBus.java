package com.service.catering.infraestructure.servicebus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.messaging.servicebus.ServiceBusClientBuilder;
import com.azure.messaging.servicebus.ServiceBusMessage;
import com.azure.messaging.servicebus.ServiceBusSenderClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.catering.application.model.event.EventDto;

//@Service
public class ProducerBus { // implements IProducerBus {

  private final ServiceBusSenderClient sender;
  private final ObjectMapper objectMapper;

  public ProducerBus(
      @Value("${azure.eventhub.connection-string}") String connStr,
      @Value("${azure.eventhub.hub-name}") String queue,
      ObjectMapper objectMapper) {
    this.sender =
        new ServiceBusClientBuilder()
            .connectionString(connStr)
            .sender()
            .queueName(queue)
            .buildClient();
    this.objectMapper = objectMapper;
  }

  //@Override
  public void sendMessage(EventDto eventDto) {
    try {
      String json = objectMapper.writeValueAsString(eventDto);
      ServiceBusMessage msg = new ServiceBusMessage(json);
      sender.sendMessage(msg);
      System.out.println("Evento enviado: " + json);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
