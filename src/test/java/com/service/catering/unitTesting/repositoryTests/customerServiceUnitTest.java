package com.service.catering.unitTesting.repositoryTests;

import com.service.catering.application.model.customer.CustomerDto;
import com.service.catering.application.model.customer.CustomerStatus;
import com.service.catering.application.service.CustomerService;
import com.service.catering.domain.model.CustomerEntity;
import com.service.catering.infraestructure.repositories.interfaces.CustomerRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@ExtendWith(MockitoExtension.class)
public class customerServiceUnitTest {

//    @Mock
//    private CustomerRepository customerRepository;

//    @Mock
    @Autowired
    private CustomerService customerService;

    @BeforeAll
    public static void beforeAll() {
        MockitoAnnotations.openMocks(customerServiceUnitTest.class);
    }

    //@Test
    public void testNewCustomer() throws Exception {
        // Given
        String nombre = "Test1";
        CustomerDto c = new CustomerDto();
        c.setName(nombre);
        c.setAddress("Por ahi");
        c.setPhone("77390243");
        c.setBirtDate("10/10/2000");
        // When
        customerService.newCustomer(c);
        // Then
        List<CustomerDto> list = customerService.getCustomers();
        CustomerDto customerf = list.stream().filter( cu -> cu.name.equals( nombre ) )
                .findFirst()
                .orElse(null);
        Assert.isTrue( customerf.name.equals( nombre ), "Customer encontrado." );
    }

    //@Test
    public void testSaveCustomer() {
        // Given
        String nombre = "Test1";
        CustomerEntity customerEntity = CustomerEntity.builder()
                .name(nombre)
                .id(UUID.randomUUID().toString())
                .phone("77393029")
                .address("por ahi")
                .status(CustomerStatus.ACTIVE.name())
                .birtDate("HOY")
                .createdDate("HOY")
                .build();

        // When
//        customerRepository.save(customerEntity);

        // Then
//        Optional<CustomerEntity> customerSaved = customerRepository.findById(customerEntity.id);
//        Assert.isNull( customerSaved, "es nulo..." );
//        Assert.notNull(customerSaved.get(), "Customer not null" );
//        Assert.notNull( customerSaved.getId(), "ID Customer not null" );
//        Assert.isTrue( nombre.equals( customerSaved.get().name ), "Name is equal" );
//        Assertions.assertEquals(  );
    }


}
