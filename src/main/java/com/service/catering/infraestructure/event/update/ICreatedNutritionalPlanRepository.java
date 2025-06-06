package com.service.catering.infraestructure.event.update;

import com.service.catering.domain.model.NutritionalPlanEntity;

public interface ICreatedNutritionalPlanRepository {

  public void eventNutritionalPlanCreated(NutritionalPlanEntity nutritionalPlanEntity);
}
