package com.service.catering.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Customer")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

  @Id public String id;
  public String fullName;
  public String userName;
  public String address;
  public String phone;
  public String email;
  public String birtDate;
  public String createdAt;
  public String status;
}
