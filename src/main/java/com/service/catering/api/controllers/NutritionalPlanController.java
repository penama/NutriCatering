package com.service.catering.api.controllers;

import com.service.catering.application.model.nutritionalplan.NutritionalPlanDto;
import com.service.catering.application.model.error.ErrorDto;
import com.service.catering.application.service.NutritionalPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catering")
public class NutritionalPlanController extends BaseController {

  @Autowired private NutritionalPlanService nutritionalPlanService;

  @PostMapping("/nutritionalplan")
  public ResponseEntity newNutritionalPlan(@RequestBody NutritionalPlanDto nutritionalPlanDto) {
	  NutritionalPlanDto nutritionalPlanDtoNew = null;
    try {
		nutritionalPlanDtoNew = nutritionalPlanService.newNutritionalPlan( nutritionalPlanDto );
    } catch (Exception e) {
      log.error(this.getClass(), e.getMessage(), e);
      return new ResponseEntity(new ErrorDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(nutritionalPlanDtoNew, HttpStatus.OK);
  }

  @GetMapping("/nutritionalplans")
  public ResponseEntity<List<NutritionalPlanDto>> getNutritionalPlan() {
    List<NutritionalPlanDto> nutritionalPlanDtos = null;
    try {
		nutritionalPlanDtos = nutritionalPlanService.getNutritionalPlan();
    } catch (Exception e) {
      log.error(this.getClass(), e.getMessage(), e);
      return new ResponseEntity(new ErrorDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<List<NutritionalPlanDto>>(nutritionalPlanDtos, HttpStatus.OK);
  }
}
