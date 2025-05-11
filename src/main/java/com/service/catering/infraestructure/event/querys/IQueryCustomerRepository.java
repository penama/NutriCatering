package com.service.catering.infraestructure.event.querys;

import java.util.List;

import com.service.catering.domain.model.CustomerEntity;

public interface IQueryCustomerRepository {

  public List<CustomerEntity> queryCustomers();

  public CustomerEntity queryCustomer(String id);
}
