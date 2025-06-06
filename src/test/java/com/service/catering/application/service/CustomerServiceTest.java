package com.service.catering.application.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.context.ApplicationEventPublisher;

import com.service.catering.application.model.customer.CustomerDto;
import com.service.catering.application.model.customer.CustomerStatus;
import com.service.catering.application.utils.CustomerUtil;
import com.service.catering.domain.model.CustomerEntity;
import com.service.catering.infraestructure.event.command.CommandEntitysEvent;
import com.service.catering.infraestructure.event.querys.IQueryCustomerRepository;

public class CustomerServiceTest {

  @Mock private ApplicationEventPublisher applicationEventPublisher;
  @Mock private IQueryCustomerRepository iQueryCustomerRepository;

  @InjectMocks private CustomerService customerService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testNewCustomer() throws Exception {

    CustomerDto customerDto = new CustomerDto();
    customerDto.fullName = "Changos";
    customerDto.id = UUID.randomUUID().toString();
    customerDto.status = CustomerStatus.ACTIVE.name();

    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setId(UUID.randomUUID().toString());
    customerEntity.setFullName("Changos");
    customerEntity.setStatus(CustomerStatus.ACTIVE.name());

    // Simulación de métodos estáticos con mockStatic()
    try (MockedStatic<CustomerUtil> mockedStatic = mockStatic(CustomerUtil.class)) {
      mockedStatic
          .when(() -> CustomerUtil.customerDtoToCustomerEntity(customerDto))
          .thenReturn(customerEntity);
      customerService.newCustomer(customerDto);
      verify(applicationEventPublisher, times(1)).publishEvent(any(CommandEntitysEvent.class));
    }
  }

  @Test
  public void testGetCustomers() throws Exception {

    CustomerDto customerDto = new CustomerDto();
    customerDto.fullName = "Changos";
    customerDto.id = UUID.randomUUID().toString();
    customerDto.status = CustomerStatus.ACTIVE.name();

    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setId(UUID.randomUUID().toString());
    customerEntity.setFullName("Changos");
    customerEntity.setStatus(CustomerStatus.ACTIVE.name());

    List<CustomerEntity> customerEntityList = Arrays.asList(customerEntity);
    when(iQueryCustomerRepository.queryCustomers()).thenReturn(customerEntityList);

    // Simulación de métodos estáticos con mockStatic()
    try (MockedStatic<CustomerUtil> mockedStatic = mockStatic(CustomerUtil.class)) {
      mockedStatic
          .when(() -> CustomerUtil.customerEntityToCustomerDto(customerEntity))
          .thenReturn(customerDto);
      List<CustomerDto> list = customerService.getCustomers();

      Assertions.assertEquals(1, list.size());
      Assertions.assertNotNull(list);
    }
  }

  @Test
  public void testGetCustomerById() throws Exception {

    CustomerDto customerDto = new CustomerDto();
    customerDto.fullName = "Changos";
    customerDto.id = UUID.randomUUID().toString();
    customerDto.status = CustomerStatus.ACTIVE.name();

    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setId(UUID.randomUUID().toString());
    customerEntity.setFullName("Changos");
    customerEntity.setStatus(CustomerStatus.ACTIVE.name());

    //        List<CustomerEntity> customerEntityList = Arrays.asList( customerEntity );
    when(iQueryCustomerRepository.queryCustomer(Mockito.anyString())).thenReturn(customerEntity);

    // Simulación de métodos estáticos con mockStatic()
    try (MockedStatic<CustomerUtil> mockedStatic = mockStatic(CustomerUtil.class)) {
      mockedStatic
          .when(() -> CustomerUtil.customerEntityToCustomerDto(customerEntity))
          .thenReturn(customerDto);
      CustomerDto customerResult = customerService.getCustomer(customerEntity.getId());
      Assertions.assertNotNull(customerResult);
    }
  }

  @Test
  public void testGetCustomerByIdNull() throws Exception {

    CustomerDto customerDto = new CustomerDto();
    customerDto.fullName = "Changos";
    customerDto.id = UUID.randomUUID().toString();
    customerDto.status = CustomerStatus.ACTIVE.name();

    CustomerEntity customerEntity = new CustomerEntity();
    customerEntity.setId(UUID.randomUUID().toString());
    customerEntity.setFullName("Changos");
    customerEntity.setStatus(CustomerStatus.ACTIVE.name());

    //        List<CustomerEntity> customerEntityList = Arrays.asList( customerEntity );
    when(iQueryCustomerRepository.queryCustomer(Mockito.anyString())).thenReturn(null);

    // Simulación de métodos estáticos con mockStatic()
    try (MockedStatic<CustomerUtil> mockedStatic = mockStatic(CustomerUtil.class)) {
      mockedStatic
          .when(() -> CustomerUtil.customerEntityToCustomerDto(customerEntity))
          .thenReturn(customerDto);
      CustomerDto customerResult = customerService.getCustomer(customerEntity.getId());
      Assertions.assertNull(customerResult);
    }
  }
}
