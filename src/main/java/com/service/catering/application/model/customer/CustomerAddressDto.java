package com.service.catering.application.model.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerAddressDto {
  public String id;
  public String CustomerId;
  public String Street;
  public String City;
  public String Latituded;
  public String Longitud;
  public String DeliveryDate;
}
