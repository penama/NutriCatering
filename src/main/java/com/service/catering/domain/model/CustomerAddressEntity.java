package com.service.catering.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "CustomerAddress")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressEntity {

  @Id public String id;
  public String customerId;
  public String Street;
  public String City;
  public String Latituded;
  public String Longitud;
  public String deliveryDate;
}
