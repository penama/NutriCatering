package com.service.catering.application.service.events;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.service.catering.application.model.event.EventDto;
import com.service.catering.domain.model.ContractEntity;
import com.service.catering.domain.model.NutritionalPlanEntity;
import com.service.catering.domain.model.ProducerEntity;
import com.service.catering.infraestructure.repositories.interfaces.NutritionalPlanRepository;
import com.service.catering.infraestructure.repositories.service.ProducerServiceRepository;
import com.service.catering.infraestructure.utils.DateFormat;

@Service
public class ContractCreatedProducerService {

  public static final String EVENT_CONTRACT_CREATED = "CONTRACT_CREATED";
  EventDto eventDto = null;

  @Autowired ProducerServiceRepository producerServiceRepository;
  @Autowired NutritionalPlanRepository nutritionalPlanRepository;

  public void contractCreatedProducer(ContractEntity contractEntity) {
    eventDto = new EventDto();
    eventDto.setEventType(EVENT_CONTRACT_CREATED);
    eventDto.setEventVersion("1.0");
    eventDto.setTimestamp(DateFormat.toDate());
    eventDto.setSource("contract-service");
    Map<String, Object> body = new HashMap<>();
    body.put("id", contractEntity.getId());
    body.put("createdAt", contractEntity.getCreatedDate());
    body.put("clientId", contractEntity.getCustomerId());
    body.put("nutritionalPlanId", contractEntity.getNutritionalPlanId());
    NutritionalPlanEntity nutritionalPlanEntity = nutritionalPlanRepository.findById(contractEntity.nutritionalPlanId).orElse(null);
    body.put("planDetails", ( nutritionalPlanEntity == null ? "null" : nutritionalPlanEntity.getPlanDetails() ) );
    eventDto.setBody(body);
    // iProducerBus.sendMessage( eventDto );
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
