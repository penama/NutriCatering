package com.service.catering.infraestructure.repositories.service;

import com.service.catering.infraestructure.event.querys.IQueryNutritionalPlanRepository;
import com.service.catering.infraestructure.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.catering.domain.model.NutritionalPlanEntity;
import com.service.catering.infraestructure.event.update.ICreatedNutritionalPlanRepository;
import com.service.catering.infraestructure.repositories.interfaces.NutritionalPlanRepository;

import java.util.List;
import java.util.UUID;

@Service
public class NutritionalPlanServiceRepository implements ICreatedNutritionalPlanRepository, IQueryNutritionalPlanRepository {

  @Autowired public NutritionalPlanRepository repository;

  public void newNutritionalPlan( NutritionalPlanEntity nutritionalPlanEntity ){
	  nutritionalPlanEntity.id = UUID.randomUUID().toString();
	  nutritionalPlanEntity.createdDate = DateFormat.toDate();
	  repository.save( nutritionalPlanEntity );
  }

  @Override
  public void eventNutritionalPlanCreated(NutritionalPlanEntity nutritionalPlanEntity) {
    repository.save(nutritionalPlanEntity);
  }

	@Override
	public List<NutritionalPlanEntity> queryNutritionalPlan() {
		return repository.findAll();
	}

	@Override
	public NutritionalPlanEntity queryNutriotionalPlantId(String nutritionalPlanId) {
		return repository.findById( nutritionalPlanId ).orElse(null);
	}
}
