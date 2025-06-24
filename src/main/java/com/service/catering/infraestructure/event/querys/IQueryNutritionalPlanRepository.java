package com.service.catering.infraestructure.event.querys;

import java.util.List;

import com.service.catering.domain.model.NutritionalPlanEntity;

public interface IQueryNutritionalPlanRepository {

  public List<NutritionalPlanEntity> queryNutritionalPlan();

  public NutritionalPlanEntity queryNutriotionalPlantId(String nutritionalPlanId);
}
