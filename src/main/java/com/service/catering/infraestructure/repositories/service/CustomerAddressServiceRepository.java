package com.service.catering.infraestructure.repositories.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.catering.domain.model.CustomerAddressEntity;
import com.service.catering.infraestructure.event.querys.IQueryCustomerAddressRepository;
import com.service.catering.infraestructure.event.update.ICreatedCustomerAddressRepository;
import com.service.catering.infraestructure.repositories.interfaces.CustomerAddressRepository;

@Service
public class CustomerAddressServiceRepository
    implements ICreatedCustomerAddressRepository, IQueryCustomerAddressRepository {

  @Autowired public CustomerAddressRepository repository;

  @Override
  public void eventCustomerAddressCreated(CustomerAddressEntity customerAddressEntity) {
    repository.save(customerAddressEntity);
  }

  @Override
  public void eventCustomerDeliveryUpdate(CustomerAddressEntity customerAddressEntity) {
    repository.save(customerAddressEntity);
  }

  @Override
  public CustomerAddressEntity queryCustomerAddressByCustomer(String customerId) {
    return repository.findByCustomerId(customerId);
  }
}
