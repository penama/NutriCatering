package com.service.catering.application.model.contract;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CateringPlan {

  @NotBlank(message = "CateringPlan es mandatorio")
  public String id;
}
