package com.service.catering.application.service.events;

import org.springframework.beans.factory.annotation.Autowired;

import com.service.catering.application.model.event.EventDto;
import com.service.catering.domain.model.CustomerAddressEntity;
import com.service.catering.infraestructure.event.update.ICreatedCustomerAddressRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerAddressUpdateEventService {

  public static final String ADDRESS_GUID = "AddresGuid";
  public static final String CUSTOMER_ID = "IdClient";
  public static final String STREET = "Street";
  public static final String CITY = "City";
  public static final String LATITUDED = "Latituded";
  public static final String LONGITUD = "Longitud";

  @Autowired private ICreatedCustomerAddressRepository iCreatedCustomerAddressRepository;

  public void customerAddressUpdatedEvent(EventDto eventDto) {
    CustomerAddressEntity customerAddressEntity = new CustomerAddressEntity();
    customerAddressEntity.setId(eventDto.getBody().get(ADDRESS_GUID));
    customerAddressEntity.setCustomerId(eventDto.getBody().get(CUSTOMER_ID));
    customerAddressEntity.setStreet(eventDto.getBody().get(STREET));
    customerAddressEntity.setCity(eventDto.getBody().get(CITY));
    customerAddressEntity.setLongitud(eventDto.getBody().get(LONGITUD));
    customerAddressEntity.setLatituded(eventDto.getBody().get(LATITUDED));

    iCreatedCustomerAddressRepository.eventCustomerAddressCreated(customerAddressEntity);
  }
}
