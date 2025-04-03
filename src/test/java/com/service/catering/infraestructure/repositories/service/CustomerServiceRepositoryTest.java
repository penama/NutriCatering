package com.service.catering.infraestructure.repositories.service;

import com.service.catering.application.model.customer.CustomerStatus;
import com.service.catering.domain.model.CustomerEntity;
import com.service.catering.infraestructure.repositories.interfaces.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerServiceRepositoryTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceRepository customerServiceRepository;

    @BeforeEach
    public void setUp(){
        org.mockito.MockitoAnnotations.openMocks( this );
    }

    @Test
    public void test(){
        String nombre = "Marco Augusto";
        String address = "por ahi";
        String birtDate = "10/10/1980";
        String phone = "78787878";

        CustomerEntity customerEntityMock = new CustomerEntity();
        customerEntityMock.setId(UUID.randomUUID().toString());
        customerEntityMock.setName(nombre);
        customerEntityMock.setAddress(address);
        customerEntityMock.setBirtDate(birtDate);
        customerEntityMock.setPhone(phone);
        customerEntityMock.setStatus(CustomerStatus.ACTIVE.name());
        customerEntityMock.setCreatedDate("16/02/2025");

        List<CustomerEntity> listCustomer = new ArrayList<>();
        listCustomer.add( customerEntityMock );

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(nombre);
        customerEntity.setAddress(address);
        customerEntity.setBirtDate(birtDate);
        customerEntity.setPhone(phone);

        Mockito.when( customerRepository.save( Mockito.any( CustomerEntity.class ) ) ).thenReturn( customerEntityMock );
        Mockito.when( customerRepository.findAll() ).thenReturn( listCustomer );
        Mockito.when( customerRepository.findById( Mockito.any( String.class ) ) ).thenReturn( Optional.of( customerEntityMock ) );
        try {
            customerServiceRepository.newCustomer( customerEntity );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // listado de clientes registrados.
        List<CustomerEntity> listEntityResult = customerServiceRepository.queryCustomers();

        assertNotNull( listEntityResult, "Listado no es nulo" );
        assertEquals( customerEntityMock, listEntityResult.getFirst() , "Datos del Mock son los mismos");

        CustomerEntity customerEntityResult = customerServiceRepository.queryCustomer(customerEntityMock.getId());

        assertNotNull( customerEntityResult, "Customer no es nulo..." );
        assertEquals( customerEntityMock, customerEntityResult, "objetos iguales..." );
    }

    @Test
    public void testCustomerNull(){
        // caso nulo.
        CustomerEntity customerEntityNull = null;
        Mockito.when( customerRepository.findById( Mockito.any( String.class ) ) ).thenReturn( Optional.ofNullable( customerEntityNull ) );
        CustomerEntity customerEntityResultNulo = customerServiceRepository.queryCustomer( "34534523" );
        assertNull( customerEntityResultNulo, "Customer es nulo..." );
    }

}
