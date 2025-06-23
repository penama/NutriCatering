package com.service.catering.application.model.billerdata;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Customer {
  @NotBlank( message = "CustomerId es mandatorio" )
  public String id;
}
