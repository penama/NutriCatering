package com.service.catering.infraestructure.servicebus;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.messaging.eventhubs.EventData;
import com.azure.messaging.eventhubs.EventDataBatch;
import com.azure.messaging.eventhubs.EventHubClientBuilder;
import com.azure.messaging.eventhubs.EventHubProducerClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.catering.application.model.event.EventDto;
import com.service.catering.application.service.BaseService;

@Service
public class ProducerEventHub extends BaseService implements IProducerBus {

  private final EventHubProducerClient producer;
  private final ObjectMapper objectMapper;

  public ProducerEventHub(
      @Value("${azure.eventhub.connection-string}") String connStr,
      @Value("${azure.eventhub.hub-name}") String queue,
      ObjectMapper objectMapper) {
    this.producer =
        new EventHubClientBuilder().connectionString(connStr, queue).buildProducerClient();

    this.objectMapper = new ObjectMapper();
  }

  @Override
  public void sendMessage(EventDto eventDto) {
    EventDataBatch batch = producer.createBatch();
    String json = "";
    try {
      json = objectMapper.writeValueAsString(eventDto);
      EventData eventData = new EventData(json);

      if (!batch.tryAdd(eventData)) {
        // Envío batch lleno y creo uno nuevo
        producer.send(batch);
        batch = producer.createBatch();

        //				  if (!batch.tryAdd(eventData)) {
        //					  throw new RuntimeException("Evento demasiado grande para enviar al Event Hub");
        //				  }
      }
    } catch (Exception ex) {
      throw new RuntimeException("Error serializing DomainEvent to JSON", ex);
    }
    if (batch.getCount() > 0) {
      producer.send(batch);
      log.info(this.getClass(), "Evento enviado: " + json);
    }
  }
}
