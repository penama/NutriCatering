package com.service.catering.infraestructure.servicebus;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.azure.messaging.servicebus.*;

import jakarta.annotation.PostConstruct;

@Service
public class AzureServiceBusConsumer {

  // private final ServiceBusConfig serviceBusConfig;

  private final String connectionString =
      "Endpoint=sb://catering-bus.servicebus.windows.net/;SharedAccessKeyName=RootManageSharedAccesKey;SharedAccessKey=wLkgujkP3kSlY+JN55IRVorHNQzqiDcmv+ASbP4l2Ao=;EntityPath=contractcreated";
  private final String topicName = "contractcreated";
  private final String subscriptionName = "Azure subscription 1";

  @PostConstruct
  public void init() {
    // startSessionProcessor();
    sendMessage();
  }

  public List<ServiceBusMessage> createMessages() {
    // create a list of messages and return it to the caller
    ServiceBusMessage[] messages = {
      new ServiceBusMessage("First message"),
      new ServiceBusMessage("Second message"),
      new ServiceBusMessage("Third message")
    };
    return Arrays.asList(messages);
  }

  public void sendMessage() {
    // create a Service Bus Sender client for the queue
    ServiceBusSenderClient senderClient =
        new ServiceBusClientBuilder()
            .connectionString(connectionString)
            .sender()
            .queueName(topicName)
            .buildClient();

    // send one message to the queue
    senderClient.sendMessage(new ServiceBusMessage("Hello, World!"));
    System.out.println("Sent a single message to the queue: " + topicName);
  }

  public void sendMessageBatch() {
    // create a Service Bus Sender client for the queue
    ServiceBusSenderClient senderClient =
        new ServiceBusClientBuilder()
            .connectionString(connectionString)
            .sender()
            .queueName(topicName)
            .buildClient();

    // Creates an ServiceBusMessageBatch where the ServiceBus.
    ServiceBusMessageBatch messageBatch = senderClient.createMessageBatch();

    // create a list of messages
    List<ServiceBusMessage> listOfMessages = createMessages();

    // We try to add as many messages as a batch can fit based on the maximum size and send to
    // Service Bus when
    // the batch can hold no more messages. Create a new batch for next set of messages and repeat
    // until all
    // messages are sent.
    for (ServiceBusMessage message : listOfMessages) {
      if (messageBatch.tryAddMessage(message)) {
        continue;
      }

      // The batch is full, so we create a new batch and send the batch.
      senderClient.sendMessages(messageBatch);
      System.out.println("Sent a batch of messages to the queue: " + topicName);

      // create a new batch
      messageBatch = senderClient.createMessageBatch();

      // Add that message that we couldn't before.
      if (!messageBatch.tryAddMessage(message)) {
        System.err.printf(
            "Message is too large for an empty batch. Skipping. Max size: %s.",
            messageBatch.getMaxSizeInBytes());
      }
    }

    if (messageBatch.getCount() > 0) {
      senderClient.sendMessages(messageBatch);
      System.out.println("Sent a batch of messages to the queue: " + topicName);
    }

    // close the client
    senderClient.close();
  }

  public void startSessionProcessor() {
    ServiceBusClientBuilder clientBuilder =
        new ServiceBusClientBuilder().connectionString(connectionString);

    ServiceBusProcessorClient processorClient =
        clientBuilder
            .sessionProcessor() // Using session mode
            .topicName(topicName)
            .subscriptionName(subscriptionName)
            .processMessage(this::processMessage)
            .processError(this::processError)
            .buildProcessorClient();

    // Start session processing in asynchronous mode
    processorClient.start();
  }

  private void processMessage(ServiceBusReceivedMessageContext context) {
    ServiceBusReceivedMessage message = context.getMessage();
    System.out.printf(
        "Processing message from session: %s. Contents: %s%n",
        message.getSessionId(), message.getBody());

    // Process the message here, respecting the order of arrival in the session
    context.complete(); // Mark the message as processed
  }

  private void processError(ServiceBusErrorContext context) {
    System.err.printf("Error occurred while processing: %s%n", context.getException().getMessage());
  }
}
