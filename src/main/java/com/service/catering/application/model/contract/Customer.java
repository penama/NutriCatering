package com.service.catering.application.model.contract;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Customer {

	@NotBlank( message = "Customer es mandatorio" )
  public String id;
}
