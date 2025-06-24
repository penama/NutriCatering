package com.service.catering.infraestructure.event.querys;

import com.service.catering.domain.model.NutritionalPlanEntity;

import java.util.List;

public interface IQueryNutritionalPlanRepository {

  public List<NutritionalPlanEntity> queryNutritionalPlan();
  
  public NutritionalPlanEntity queryNutriotionalPlantId(String nutritionalPlanId);
}
