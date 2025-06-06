package com.service.catering.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "NutritionalPlan")
@Data
public class NutritionalPlanEntity {

  @Id public String id;
  public String planDetails;
  public String nutritionistId;
  public String customerId;
  public boolean isDelivered;
  public String analysisResultIds;
  public String createdDate;
}
