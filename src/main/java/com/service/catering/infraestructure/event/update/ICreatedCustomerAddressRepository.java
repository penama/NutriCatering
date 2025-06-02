package com.service.catering.infraestructure.event.update;

import com.service.catering.domain.model.CustomerAddressEntity;

public interface ICreatedCustomerAddressRepository {

  public void eventCustomerAddressCreated(CustomerAddressEntity customerAddressEntity);

  public void eventCustomerDeliveryUpdate(CustomerAddressEntity customerAddressEntity);
}
