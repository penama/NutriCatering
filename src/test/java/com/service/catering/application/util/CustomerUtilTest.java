package com.service.catering.application.util;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.service.catering.application.model.customer.CustomerDto;
import com.service.catering.application.model.customer.CustomerStatus;
import com.service.catering.application.utils.CustomerUtil;
import com.service.catering.domain.model.CustomerEntity;

public class CustomerUtilTest {

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCustomerDtoToCustomerEntity() {
    CustomerDto customerDto = new CustomerDto();
    customerDto.fullName = "Changos";
    customerDto.id = UUID.randomUUID().toString();
    customerDto.status = CustomerStatus.ACTIVE.name();

    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setId(UUID.randomUUID().toString());
    customerEntity.setFullName("Changos");
    customerEntity.setStatus(CustomerStatus.ACTIVE.name());

    CustomerUtil contractUtil = new CustomerUtil();
    CustomerEntity customerEntityResult = CustomerUtil.customerDtoToCustomerEntity(customerDto);

    Assertions.assertNotNull(customerEntityResult);
    Assertions.assertEquals(customerEntityResult.getId(), customerDto.id);
  }

  @Test
  public void testCustomerEntityToCustomerDto() throws Exception {
    CustomerDto customerDto = new CustomerDto();
    customerDto.fullName = "Changos";
    customerDto.id = UUID.randomUUID().toString();
    customerDto.status = CustomerStatus.ACTIVE.name();

    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setId(UUID.randomUUID().toString());
    customerEntity.setFullName("Changos");
    customerEntity.setStatus(CustomerStatus.ACTIVE.name());
    customerEntity.setAddress("por ahi");
    customerEntity.setBirtDate("20/02/2020");
    customerEntity.setPhone("42345234");

    CustomerDto customerDtoRestul = CustomerUtil.customerEntityToCustomerDto(customerEntity);

    Assertions.assertNotNull(customerDtoRestul);
    Assertions.assertEquals(customerDtoRestul.id, customerEntity.getId());
  }
}
