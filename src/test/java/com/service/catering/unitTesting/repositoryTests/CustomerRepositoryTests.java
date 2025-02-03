package com.service.catering.unitTesting.repositoryTests;

import com.service.catering.application.model.billerdata.Customer;
import com.service.catering.application.model.customer.CustomerStatus;
import com.service.catering.domain.model.CustomerEntity;
import com.service.catering.infraestructure.repositories.interfaces.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.UUID;

@DataJpaTest
public class CustomerRepositoryTests {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    @Transactional
    @Rollback
    public void testSaveCustomer(){
        String nombre = "Test1";
        CustomerEntity customerEntity = CustomerEntity.builder()
                .name(nombre)
                .id( UUID.randomUUID().toString() )
                .phone("77393029")
                .address( "por ahi" )
                .status(CustomerStatus.ACTIVE.name())
                .birtDate( "HOY" )
                .createdDate( "HOY" )
                .build();

        CustomerEntity customerSaved = customerRepository.save( customerEntity );
        Assert.notNull( customerSaved, "Customer not null" );
        Assert.notNull( customerSaved.getId(), "ID Customer not null" );
        Assert.isTrue( nombre.equals( customerSaved.getName() ), "Name is equal" );
    }

    @Test
    @Transactional
    @Rollback
    public void testSaveCustomerFoundId(){
        String nombre = "Test1";
        String id = UUID.randomUUID().toString();
        CustomerEntity customerEntity = CustomerEntity.builder()
                .name(nombre)
                .id( id )
                .phone("77393029")
                .address( "por ahi" )
                .status(CustomerStatus.ACTIVE.name())
                .birtDate( "HOY" )
                .createdDate( "HOY" )
                .build();

        customerRepository.save( customerEntity );
        CustomerEntity customerFound = customerRepository.findById( id ).get();
        Assert.notNull( customerFound, "Customer not null" );
        Assert.notNull( customerFound.getId(), "ID Customer not null" );
        Assert.isTrue( nombre.equals( customerFound.getName() ), "Name is equal" );
    }

}
