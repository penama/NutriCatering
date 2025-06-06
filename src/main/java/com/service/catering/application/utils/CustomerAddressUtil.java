package com.service.catering.application.utils;

import com.service.catering.application.model.customer.CustomerAddressDto;
import com.service.catering.domain.model.CustomerAddressEntity;

public class CustomerAddressUtil {

  public static CustomerAddressEntity customerAddressDtoToCustomerAddressEntity(
      CustomerAddressDto customerAddressDto) {
    CustomerAddressEntity customerAddressEntity = new CustomerAddressEntity();
    customerAddressEntity.setId(customerAddressDto.getId());
    customerAddressEntity.setCustomerId(customerAddressDto.getCustomerId());
    customerAddressEntity.setStreet(customerAddressDto.getStreet());
    customerAddressEntity.setCity(customerAddressDto.City);
    customerAddressEntity.setLatituded(customerAddressDto.getLatituded());
    customerAddressEntity.setLongitud(customerAddressDto.getLongitud());
    customerAddressEntity.setDeliveryDate(customerAddressDto.getDeliveryDate());
    return customerAddressEntity;
  }

  public static CustomerAddressDto customerEntityToCustomerDto(
      CustomerAddressEntity customerAddressEntity) {
    CustomerAddressDto customerAddressDto = new CustomerAddressDto();
    customerAddressDto.setId(customerAddressEntity.getId());
    customerAddressDto.setCustomerId(customerAddressEntity.getCustomerId());
    customerAddressDto.setStreet(customerAddressEntity.getStreet());
    customerAddressDto.setCity(customerAddressEntity.City);
    customerAddressDto.setLatituded(customerAddressEntity.getLatituded());
    customerAddressDto.setLongitud(customerAddressEntity.getLongitud());
    customerAddressDto.setDeliveryDate(customerAddressEntity.getDeliveryDate());
    return customerAddressDto;
  }
}
