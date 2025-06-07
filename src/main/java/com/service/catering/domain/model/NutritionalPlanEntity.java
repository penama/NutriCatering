package com.service.catering.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "NutritionalPlan")
@Data
public class NutritionalPlanEntity {

  @Id public String id;

  @Column(name = "planDetails", length = 1000)
  public String planDetails;

  public String nutritionistId;
  public String customerId;
  public boolean isDelivered;
  public String analysisResultIds;
  public String createdDate;
}
