package com.service.catering.application.service.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.catering.application.model.customer.CustomerStatus;
import com.service.catering.application.model.event.EventDto;
import com.service.catering.domain.model.CustomerEntity;
import com.service.catering.infraestructure.event.update.ICreatedCustomerRepository;

@Service
public class CustomerCreatedEventService {

  public static final String ID = "id";
  public static final String USERNAME = "username";
  public static final String EMAIL = "email";
  public static final String FULLNAME = "fullName";
  public static final String CREATEDAT = "createdAt";

  @Autowired private ICreatedCustomerRepository iCreatedCustomerRepository;

  public void customerCreatedEvent(EventDto eventDto) {
    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setId(eventDto.getBody().get(ID));
    customerEntity.setUserName(eventDto.getBody().get(USERNAME));
    customerEntity.setEmail(eventDto.getBody().get(EMAIL));
    customerEntity.setFullName(eventDto.getBody().get(FULLNAME));
    customerEntity.setCreatedAt(eventDto.getBody().get(CREATEDAT));

    customerEntity.setStatus(CustomerStatus.ACTIVE.name());
    iCreatedCustomerRepository.eventCustomerCreated(customerEntity);
  }
}
