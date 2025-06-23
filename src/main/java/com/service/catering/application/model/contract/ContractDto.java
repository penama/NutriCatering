package com.service.catering.application.model.contract;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ContractDto {

  public String id;
  @NotBlank( message = "Descripción es mandatorio" )
  public String description;
  public String creationDate;
  public String status;
	@NotNull(message = "El CateringPlan es mandatorio")
  public CateringPlan cateringPlan;
	@NotNull(message = "El cliente es mandatorio")
  public Customer customer;
	@NotNull(message = "El cliente es mandatorio")
	@Positive( message = "Monto debe ser positivo." )
  public float totalAmount;
	@NotNull( message = "Quotas es mandatorio" )
	@Positive( message = "Monto debe ser positivo." )
  public Integer quotas;
}
