package com.service.catering.infraestructure.repositories.service;

import com.service.catering.domain.model.CustomerEntity;
import com.service.catering.infraestructure.event.querys.IQueryCustomerRepository;
import com.service.catering.infraestructure.repositories.interfaces.CustomerRepository;
import com.service.catering.infraestructure.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceRepository implements IQueryCustomerRepository {

    @Autowired
    public CustomerRepository repository;

    public void newCustomer(CustomerEntity customerEntity ) throws Exception{
        customerEntity.id = UUID.randomUUID().toString();
        customerEntity.createdDate = DateFormat.toDate();
        repository.save(customerEntity);
    }


    @Override
    public List<CustomerEntity> queryCustomers() {
        return repository.findAll();
    }

    @Override
    public CustomerEntity queryCustomer(String id) {
        Optional<CustomerEntity> byId = repository.findById(id);
//        System.out.println( "presente: " + byId.isPresent() );
        return ( byId.isPresent() ? byId.get() : null );
    }
}
