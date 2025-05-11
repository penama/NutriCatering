package com.service.catering.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.catering.application.model.customer.CustomerDto;
import com.service.catering.application.model.customer.CustomerStatus;
import com.service.catering.application.utils.CustomerUtil;
import com.service.catering.domain.model.CustomerEntity;
import com.service.catering.infraestructure.event.querys.IQueryCustomerRepository;

@Service
public class CustomerService extends BaseService {

  @Autowired private IQueryCustomerRepository iQueryCustomerRepository;

  public void newCustomer(CustomerDto customerDto) throws Exception {
    CustomerEntity customerEntity = CustomerUtil.customerDtoToCustomerEntity(customerDto);
    customerEntity.setStatus(CustomerStatus.ACTIVE.name());
    commandHandler(this, customerEntity);
    //        CommandEntitysEvent commandEntitysEvent = new CommandEntitysEvent( this,
    // customerEntity);
    //        applicationEventPublisher.publishEvent( commandEntitysEvent );
  }

  public List<CustomerDto> getCustomers() throws Exception {
    List<CustomerEntity> customerEntities = iQueryCustomerRepository.queryCustomers();
    List<CustomerDto> customerDtos = new ArrayList<>();
    System.out.println(customerEntities.size());
    for (CustomerEntity customerEntity : customerEntities) {
      customerDtos.add(CustomerUtil.customerEntityToCustomerDto(customerEntity));
    }
    return customerDtos;
  }

  public CustomerDto getCustomer(String id) throws Exception {
    CustomerEntity customerEntity = iQueryCustomerRepository.queryCustomer(id);
    if (customerEntity == null) return null;
    return CustomerUtil.customerEntityToCustomerDto(customerEntity);
  }
}
