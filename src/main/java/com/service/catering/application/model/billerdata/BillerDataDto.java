package com.service.catering.application.model.billerdata;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BillerDataDto {

  public String id;

  @NotBlank(message = "Razón social es obligatorio")
  public String socialReazon;

  @NotBlank(message = "Nit es obligatorio")
  public String nit;

  @NotBlank(message = "La razón social es obligatorio")
  public String email;

  @NotNull(message = "Cliente es mandatorio")
  public Customer customer;

  public String createdDate;
}
