package com.service.catering.application.service.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.catering.application.model.event.EventDto;
import com.service.catering.domain.model.CustomerAddressEntity;
import com.service.catering.infraestructure.event.update.ICreatedCustomerAddressRepository;

@Service
public class CustomerAddressUpdateEventService {

  public static final String ADDRESS_GUID = "addressId";
  public static final String CUSTOMER_ID = "clientGuid";
  public static final String STREET = "street";
  public static final String CITY = "city";
  public static final String LATITUDED = "latitude";
  public static final String LONGITUD = "longitude";

  @Autowired private ICreatedCustomerAddressRepository iCreatedCustomerAddressRepository;

  public void customerAddressUpdatedEvent(EventDto eventDto) {
    CustomerAddressEntity customerAddressEntity = new CustomerAddressEntity();
    customerAddressEntity.setId(eventDto.getBody().get(ADDRESS_GUID).toString());
    customerAddressEntity.setCustomerId(eventDto.getBody().get(CUSTOMER_ID).toString());
    customerAddressEntity.setStreet(eventDto.getBody().get(STREET).toString());
    customerAddressEntity.setCity(eventDto.getBody().get(CITY).toString());
    customerAddressEntity.setLongitud(eventDto.getBody().get(LONGITUD).toString());
    customerAddressEntity.setLatituded(eventDto.getBody().get(LATITUDED).toString());

    iCreatedCustomerAddressRepository.eventCustomerAddressCreated(customerAddressEntity);
  }
}
