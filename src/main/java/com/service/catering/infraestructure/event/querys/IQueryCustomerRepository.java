package com.service.catering.infraestructure.event.querys;

import com.service.catering.domain.model.CustomerEntity;

import java.util.List;

public interface IQueryCustomerRepository {

    public List<CustomerEntity> queryCustomers();

}
