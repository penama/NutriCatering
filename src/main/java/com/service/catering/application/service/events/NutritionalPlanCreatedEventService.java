package com.service.catering.application.service.events;

import org.springframework.beans.factory.annotation.Autowired;

import com.service.catering.application.model.event.EventDto;
import com.service.catering.domain.model.NutritionalPlanEntity;
import com.service.catering.infraestructure.event.update.ICreatedNutritionalPlanRepository;

public class NutritionalPlanCreatedEventService {

  public static final String ID = "id";
  public static final String CUSTOMER_ID = "clientId";
  public static final String NUTRICIONISTA = "nutritionistId";
  public static final String IS_DEVELIVERED = "isDelivered";
  public static final String ANALYSIS_RESULT_IDS = "analysisResultIds";
  public static final String PLAN_DETAILS = "planDetails";

  @Autowired private ICreatedNutritionalPlanRepository iCreatedNutritionalPlanRepository;

  public void customerCreatedEvent(EventDto eventDto) {
    NutritionalPlanEntity nutritionalPlanEntity = new NutritionalPlanEntity();
    nutritionalPlanEntity.setId(eventDto.getBody().get(ID));
    nutritionalPlanEntity.setCustomerId(eventDto.getBody().get(CUSTOMER_ID));
    nutritionalPlanEntity.setNutritionistId(eventDto.getBody().get(NUTRICIONISTA));
    nutritionalPlanEntity.setDelivered(
        Boolean.parseBoolean(eventDto.getBody().get(IS_DEVELIVERED)));
    nutritionalPlanEntity.setNutritionistId(eventDto.getBody().get(ANALYSIS_RESULT_IDS));
    nutritionalPlanEntity.setPlanDetails(eventDto.getBody().get(PLAN_DETAILS));

    iCreatedNutritionalPlanRepository.eventNutritionalPlanrCreated(nutritionalPlanEntity);
  }
}
