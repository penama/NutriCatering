package com.service.catering.application.model.customer;

import com.service.catering.application.exceptions.FieldNullException;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

  public String id;
  public String fullName;
  public String userName;
  public String address;
  public String phone;
  public String email;
  public String birtDate;
  public String createdAt;
  public String status;
  public CustomerAddressDto customerAddressDto;

  public void setId(String id) {
    this.id = id;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setFullName(String fullName) throws FieldNullException {
//    System.out.println("nombre " + fullName);
//    if (fullName == null || fullName.isEmpty()) {
//      throw new FieldNullException("El cammpo 'fullName' no puede ser null");
//    }
    this.fullName = fullName;
  }

  public void setAddress(String address) throws FieldNullException {
//    if (address == null || address.isEmpty()) {
//      throw new FieldNullException("El cammpo 'address' no puede ser null");
//    }
    this.address = address;
  }

  public void setPhone(String phone) throws FieldNullException {
//    if (phone == null || phone.isEmpty()) {
//      throw new FieldNullException("El cammpo 'phone' no puede ser null");
//    }
    this.phone = phone;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setBirtDate(String birtDate) throws FieldNullException {
//    if (birtDate == null || birtDate.isEmpty()) {
//      throw new FieldNullException("El cammpo 'birtDate' no puede ser null");
//    }
    this.birtDate = birtDate;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
