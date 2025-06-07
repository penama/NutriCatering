package com.service.catering.application.service.events;

import java.util.HashMap;
import java.util.Map;

import com.service.catering.domain.model.*;
import com.service.catering.infraestructure.repositories.interfaces.NutritionalPlanRepository;
import com.service.catering.infraestructure.repositories.service.ContractServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.service.catering.application.model.event.EventDto;
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
  @Autowired ContractServiceRepository contractServiceRepository;
	@Autowired
	NutritionalPlanRepository nutritionalPlanRepository;

  public void contractDispatchedForRecipeProducer(PaymentEntity paymentEntity) {
	  OrderEntity orderEntity = orderServiceRepository.queryOrder(paymentEntity.getOrderId());
	  ContractEntity contractEntity = contractServiceRepository.queryContractId( orderEntity.getContractId() );
	  if ( contractEntity.isNotifedDispatchedForRecipe() ){
		  System.out.println( "OrdernId=" + orderEntity.id + ", contratId=" + orderEntity.getContractId() + ", ya fue notificado anteriormente." );
		  return;
	  }
	  contractEntity.setNotifedDispatchedForRecipe( true );


	  eventDto = new EventDto();
    eventDto.setEventType(EVENT_CONTRACT_DISPATCHED_FOR_RECIPE);
    eventDto.setEventVersion("1.0");
    eventDto.setTimestamp(DateFormat.toDate());
    eventDto.setSource("order-service");
//    OrderEntity orderEntity = orderServiceRepository.queryOrder(paymentEntity.getOrderId());
    Map<String, Object> body = new HashMap<>();
    body.put("id", paymentEntity.getId());
    body.put("createdAt", orderEntity.getCreatedDate());
    body.put("contractId", orderEntity.getContractId());
    body.put("orderId", orderEntity.getId());
	body.put("clientId", contractEntity.getCustomerId());
	NutritionalPlanEntity nutritionalPlanEntity = nutritionalPlanRepository.getReferenceById( contractEntity.nutritionalPlanId );
	System.out.println( "plansDetails: " + nutritionalPlanEntity.getPlanDetails() );
	body.put("planDetails", nutritionalPlanEntity.getPlanDetails());
    eventDto.setBody(body);

	  contractServiceRepository.updateContract( contractEntity );

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

  public boolean notify( String contractId ){
	  ContractEntity contractEntity = contractServiceRepository.queryContractId( contractId );
	  if ( contractEntity.isNotifedDispatchedForRecipe() )
		  return false;
	  contractEntity.setNotifedDispatchedForRecipe( true );
	  contractServiceRepository.updateContract( contractEntity );
	  return true;
  }

}
