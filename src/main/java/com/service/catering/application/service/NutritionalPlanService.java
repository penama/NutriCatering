package com.service.catering.application.service;

import com.service.catering.application.model.nutritionalplan.NutritionalPlanDto;
import com.service.catering.application.utils.NutritionalPlanUtil;
import com.service.catering.domain.model.NutritionalPlanEntity;
import com.service.catering.infraestructure.event.querys.IQueryNutritionalPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NutritionalPlanService extends BaseCommandHandler {

	@Autowired
	private IQueryNutritionalPlanRepository iQueryNutritionalPlanRepository;

	public NutritionalPlanDto newNutritionalPlan(NutritionalPlanDto nutritionalPlanDto) throws Exception {
		NutritionalPlanEntity nutritionalPlanEntity = NutritionalPlanUtil.nutritionalPlanDtoToNutritionalPlanEntity( nutritionalPlanDto );
		commandHandler(this, nutritionalPlanEntity);
		return NutritionalPlanUtil.nutritionalPlanEntityToNutritionalPlanDto( nutritionalPlanEntity );
	}

	public List<NutritionalPlanDto> getNutritionalPlan() throws Exception {
		List<NutritionalPlanEntity> nutritionalPlanDtoList = iQueryNutritionalPlanRepository.queryNutritionalPlan();
		List<NutritionalPlanDto> nutritionalPlanDtos = new ArrayList<>();
		for (NutritionalPlanEntity nutritionalPlanEntity : nutritionalPlanDtoList) {
			nutritionalPlanDtos.add(NutritionalPlanUtil.nutritionalPlanEntityToNutritionalPlanDto(nutritionalPlanEntity));
		}
		return nutritionalPlanDtos;
	}
}
