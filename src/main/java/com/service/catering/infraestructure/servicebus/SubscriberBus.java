package com.service.catering.infraestructure.servicebus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.messaging.servicebus.*;
import com.service.catering.application.service.events.SubscribersEventService;

import jakarta.annotation.PostConstruct;

@Service
public class SubscriberBus {

  @Value("${azure.eventhub.connection-string}")
  private String connectionString;

  @Value("${azure.eventhub.hub-name}")
  private String queueName;

  @Autowired private SubscribersEventService subscribersEventService;

  @PostConstruct
  public void init() {
    startListening();
  }

  public void startListening() {
    // Cliente para recibir mensajes
    ServiceBusProcessorClient processorClient =
        new ServiceBusClientBuilder()
            .connectionString(connectionString)
            .processor()
            .queueName(queueName) // Usa .topicName() y .subscriptionName() si es un tópico
			.disableAutoComplete()
            .processMessage(this::processMessage) // Método que procesa cada mensaje
            .processError(this::handleError) // Manejo de errores
            .buildProcessorClient();

    processorClient.start(); // Inicia la escucha
  }

  private void processMessage(ServiceBusReceivedMessageContext context) {
    ServiceBusReceivedMessage message = context.getMessage();
    String messageBody = message.getBody().toString();
    subscribersEventService.procesarMensaje(messageBody);
    System.out.println("Mensaje recibido: " + messageBody);
    // Marca el mensaje como completado (lo elimina de la cola)
    // context.complete();
  }

  private void handleError(ServiceBusErrorContext context) {
    System.err.println("Error al procesar mensaje: " + context.getException());
  }
}
