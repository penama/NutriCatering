package com.service.catering.application.service.events;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.service.catering.application.model.event.EventDto;
import com.service.catering.domain.model.OrderEntity;
import com.service.catering.domain.model.PaymentEntity;
import com.service.catering.domain.model.ProducerEntity;
import com.service.catering.infraestructure.repositories.service.OrderServiceRepository;
import com.service.catering.infraestructure.repositories.service.ProducerServiceRepository;
import com.service.catering.infraestructure.utils.DateFormat;

@Service
public class ContractDispatchedForRecipeProducerService {

  public static final String EVENT_CONTRACT_DISPATCHED_FOR_RECIPE =
      "CONTRACT_DISPATCHED_FOR_RECIPE";
  EventDto eventDto = null;

  @Autowired ProducerServiceRepository producerServiceRepository;

  @Autowired OrderServiceRepository orderServiceRepository;

  public void contractDispatchedForRecipeProducer(PaymentEntity paymentEntity) {
    eventDto = new EventDto();
    eventDto.setEventType(EVENT_CONTRACT_DISPATCHED_FOR_RECIPE);
    eventDto.setEventVersion("1.0");
    eventDto.setTimestamp(DateFormat.toDate());
    eventDto.setSource("order-service");
    OrderEntity orderEntity = orderServiceRepository.queryOrder(paymentEntity.getOrderId());
    Map<String, String> body = new HashMap<>();
    body.put("id", paymentEntity.getId());
    body.put("createdAt", orderEntity.getCreatedDate());
    body.put("contractId", orderEntity.getContractId());
    body.put("orderId", orderEntity.getId());
    eventDto.setBody(body);

    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    // EventDto event = mapper.writeValueAsString( eventDto );

    ProducerEntity producerEntity = new ProducerEntity();
    try {
      producerEntity.setBody(mapper.writeValueAsString(eventDto));
      producerEntity.setStatus("pendiente");
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    producerServiceRepository.newProducer(producerEntity);
  }
}
