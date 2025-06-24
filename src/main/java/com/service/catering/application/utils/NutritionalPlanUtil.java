package com.service.catering.application.utils;

import com.service.catering.application.model.nutritionalplan.Customer;
import com.service.catering.application.model.nutritionalplan.NutritionalPlanDto;
import com.service.catering.domain.model.NutritionalPlanEntity;

public class NutritionalPlanUtil {

  public static NutritionalPlanEntity nutritionalPlanDtoToNutritionalPlanEntity(
      NutritionalPlanDto nutritionalPlanDto) {
    NutritionalPlanEntity nutritionalPlanEntity = new NutritionalPlanEntity();
    nutritionalPlanEntity.setId(nutritionalPlanDto.getId());
    nutritionalPlanEntity.setPlanDetails(nutritionalPlanDto.getPlanDetails());
    nutritionalPlanEntity.setDelivered(nutritionalPlanDto.isDelivered);
    nutritionalPlanEntity.setCustomerId(nutritionalPlanDto.getCustomer().getId());
    nutritionalPlanEntity.setAnalysisResultIds(nutritionalPlanDto.analysisResultIds);
    nutritionalPlanEntity.setCreatedDate(nutritionalPlanDto.createdDate);

    return nutritionalPlanEntity;
  }

  public static NutritionalPlanDto nutritionalPlanEntityToNutritionalPlanDto(
      NutritionalPlanEntity nutritionalPlanEntity) {
    NutritionalPlanDto nutritionalPlanDto = new NutritionalPlanDto();
    nutritionalPlanDto.setId(nutritionalPlanEntity.getId());
    nutritionalPlanDto.setPlanDetails(nutritionalPlanEntity.getPlanDetails());
    nutritionalPlanDto.setDelivered(nutritionalPlanEntity.isDelivered());
    nutritionalPlanDto.setCustomer(new Customer(nutritionalPlanEntity.customerId));
    nutritionalPlanDto.setAnalysisResultIds(nutritionalPlanEntity.analysisResultIds);
    nutritionalPlanDto.setCreatedDate(nutritionalPlanEntity.createdDate);

    return nutritionalPlanDto;
  }
}
