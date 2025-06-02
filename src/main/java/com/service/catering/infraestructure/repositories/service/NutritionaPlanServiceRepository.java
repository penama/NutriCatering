package com.service.catering.infraestructure.repositories.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.catering.domain.model.NutritionalPlanEntity;
import com.service.catering.infraestructure.event.update.ICreatedNutritionalPlanRepository;
import com.service.catering.infraestructure.repositories.interfaces.NutritionalPlanRepository;

@Service
public class NutritionaPlanServiceRepository implements ICreatedNutritionalPlanRepository {

  @Autowired public NutritionalPlanRepository repository;

  @Override
  public void eventNutritionalPlanrCreated(NutritionalPlanEntity nutritionalPlanEntity) {
    repository.save(nutritionalPlanEntity);
  }
}
