package com.service.catering.infraestructure.repositories.service;

import com.service.catering.domain.model.ContractEntity;
import com.service.catering.domain.model.OrderEntity;
import com.service.catering.infraestructure.event.querys.IQueryContractRepository;
import com.service.catering.infraestructure.event.querys.IQueryOrderRepository;
import com.service.catering.infraestructure.repositories.interfaces.ContractRepository;
import com.service.catering.infraestructure.repositories.interfaces.OrderRepository;
import com.service.catering.infraestructure.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceRepository implements IQueryOrderRepository {

    @Autowired
    public OrderRepository repository;

    public void newContract(OrderEntity orderEntity ) throws Exception{
        orderEntity.setId(UUID.randomUUID().toString());
        orderEntity.setCreatedDate(DateFormat.toDate());
        repository.save(orderEntity);
    }

    @Override
    public List<OrderEntity> queryOrders() {
        return repository.findAll();
    }

    @Override
    public List<OrderEntity> queryOrders(String contractId) {
        return repository.findByContractId( contractId );
    }

    @Override
    public OrderEntity queryOrder(String orderId) {
        return repository.findById( orderId ).get();
    }

    @Override
    public void update(OrderEntity orderEntity) {
        repository.save( orderEntity );
    }
}
