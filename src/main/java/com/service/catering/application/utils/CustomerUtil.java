package com.service.catering.application.utils;

import com.service.catering.application.exceptions.FieldNullException;
import com.service.catering.application.model.customer.CustomerDto;
import com.service.catering.domain.model.CustomerEntity;

public class CustomerUtil {

  public static CustomerEntity customerDtoToCustomerEntity(CustomerDto customerDto) {
    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setId(customerDto.getId());
    customerEntity.setFullName(customerDto.getFullName());
    customerEntity.setUserName(customerDto.getUserName());
    customerEntity.setAddress(customerDto.getAddress());
    customerEntity.setPhone(customerDto.getPhone());
    customerEntity.setEmail(customerDto.getEmail());
    customerEntity.setBirtDate(customerDto.getBirtDate());
    customerEntity.setStatus(customerDto.getStatus());
    customerEntity.setCreatedAt(customerDto.getCreatedAt());
    return customerEntity;
  }

  public static CustomerDto customerEntityToCustomerDto(CustomerEntity customerEntity)
      throws FieldNullException {
    CustomerDto customerDto = new CustomerDto();
    customerDto.setId(customerEntity.getId());
    customerDto.setFullName(customerEntity.getFullName());
    customerDto.setUserName(customerEntity.getUserName());
    customerDto.setAddress(customerEntity.getAddress());
    customerDto.setPhone(customerEntity.getPhone());
    customerDto.setEmail(customerEntity.getEmail());
    customerDto.setBirtDate(customerEntity.getBirtDate());
    customerDto.setStatus(customerEntity.getStatus());
    customerDto.setCreatedAt(customerEntity.getCreatedAt());
    return customerDto;
  }
}
