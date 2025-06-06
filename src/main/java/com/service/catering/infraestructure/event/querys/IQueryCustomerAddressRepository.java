package com.service.catering.infraestructure.event.querys;

import com.service.catering.domain.model.CustomerAddressEntity;

public interface IQueryCustomerAddressRepository {

  public CustomerAddressEntity queryCustomerAddressByCustomer(String customerId);
}
