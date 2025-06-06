package com.service.catering.infraestructure.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.catering.domain.model.NutritionalPlanEntity;

@Repository
public interface NutritionalPlanRepository extends JpaRepository<NutritionalPlanEntity, String> {}
