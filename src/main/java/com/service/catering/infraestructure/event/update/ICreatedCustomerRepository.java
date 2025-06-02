package com.service.catering.infraestructure.event.update;

import com.service.catering.domain.model.CustomerEntity;

public interface ICreatedCustomerRepository {

  public void eventCustomerCreated(CustomerEntity customerEntity);
}
