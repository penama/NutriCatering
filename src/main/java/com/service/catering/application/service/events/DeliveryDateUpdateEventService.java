package com.service.catering.application.service.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.catering.application.model.event.EventDto;
import com.service.catering.domain.model.CustomerAddressEntity;
import com.service.catering.infraestructure.event.update.ICreatedCustomerAddressRepository;

@Service
public class DeliveryDateUpdateEventService {

  public static final String ADDRESS_GUID = "addressGuid";
  public static final String CUSTOMER_ID = "clientGuid";
  public static final String NEW_DATE = "newDate";
  public static final String PREVIUS_DATE = "previousDate";

  @Autowired private ICreatedCustomerAddressRepository iCreatedCustomerAddressRepository;

  public void deliveryDateUpdateUpdateEvent(EventDto eventDto) {
    CustomerAddressEntity customerAddressEntity = new CustomerAddressEntity();
    customerAddressEntity.setId(eventDto.getBody().get(ADDRESS_GUID).toString());
    customerAddressEntity.setCustomerId(eventDto.getBody().get(CUSTOMER_ID).toString());
    customerAddressEntity.setDeliveryDate(eventDto.getBody().get(NEW_DATE).toString());
    System.out.println(
        "previusDate: "
            + eventDto.getBody().get(PREVIUS_DATE)
            + ", newDate: "
            + customerAddressEntity.getDeliveryDate());

    iCreatedCustomerAddressRepository.eventCustomerDeliveryUpdate(customerAddressEntity);
  }
}
