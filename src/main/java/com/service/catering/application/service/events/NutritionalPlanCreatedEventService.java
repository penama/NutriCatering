package com.service.catering.application.service.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.catering.application.model.contract.CateringPlan;
import com.service.catering.application.model.contract.ContractDto;
import com.service.catering.application.model.contract.Customer;
import com.service.catering.application.model.event.EventDto;
import com.service.catering.application.service.ContractService;
import com.service.catering.domain.model.NutritionalPlanEntity;
import com.service.catering.infraestructure.event.update.ICreatedNutritionalPlanRepository;

@Service
public class NutritionalPlanCreatedEventService {

  public static final String ID = "id";
  public static final String CUSTOMER_ID = "clientId";
  public static final String NUTRICIONISTA = "nutritionistId";
  public static final String IS_DEVELIVERED = "isDelivered";
  public static final String ANALYSIS_RESULT_IDS = "analysisResultIds";
  public static final String PLAN_DETAILS = "planDetails";

  @Autowired private ICreatedNutritionalPlanRepository iCreatedNutritionalPlanRepository;
  @Autowired private ContractService contractService;

  public void customerCreatedEvent(EventDto eventDto) {
    NutritionalPlanEntity nutritionalPlanEntity = new NutritionalPlanEntity();
    nutritionalPlanEntity.setId(eventDto.getBody().get(ID));
    nutritionalPlanEntity.setCustomerId(eventDto.getBody().get(CUSTOMER_ID));
    nutritionalPlanEntity.setNutritionistId(eventDto.getBody().get(NUTRICIONISTA));
    nutritionalPlanEntity.setDelivered(
        Boolean.parseBoolean(eventDto.getBody().get(IS_DEVELIVERED)));
    nutritionalPlanEntity.setNutritionistId(eventDto.getBody().get(ANALYSIS_RESULT_IDS));
    nutritionalPlanEntity.setPlanDetails(eventDto.getBody().get(PLAN_DETAILS));

    iCreatedNutritionalPlanRepository.eventNutritionalPlanCreated(nutritionalPlanEntity);

    // posterior a crer el plan nutricional se crea el contrato con la orden para pagar.
    ContractDto contractDto = new ContractDto();
    contractDto.setDescription(nutritionalPlanEntity.planDetails);
    CateringPlan cateringPlan = new CateringPlan();
    cateringPlan.setId(nutritionalPlanEntity.id);
    contractDto.setCateringPlan(cateringPlan);
    Customer customer = new Customer();
    customer.setId(nutritionalPlanEntity.customerId);
    contractDto.setCustomer(customer);
    contractDto.setTotalAmount(1000);
    contractDto.setQuotas(1);
    try {
      contractService.newContract(contractDto);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
