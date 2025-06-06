package com.service.catering.infraestructure.repositories.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.catering.domain.model.OrderEntity;
import com.service.catering.infraestructure.event.querys.IQueryOrderRepository;
import com.service.catering.infraestructure.repositories.interfaces.OrderRepository;
import com.service.catering.infraestructure.utils.DateFormat;

@Service
public class OrderServiceRepository implements IQueryOrderRepository {

  @Autowired public OrderRepository repository;

  public void newContract(OrderEntity orderEntity) throws Exception {
    orderEntity.id = UUID.randomUUID().toString();
    orderEntity.createdDate = DateFormat.toDate();
    repository.save(orderEntity);
  }

  @Override
  public List<OrderEntity> queryOrders() {
    return repository.findAll();
  }

  @Override
  public List<OrderEntity> queryOrders(String contractId) {
    return repository.findByContractId(contractId);
  }

  @Override
  public OrderEntity queryOrder(String orderId) {
    return repository.findById(orderId).get();
  }

  @Override
  public void update(OrderEntity orderEntity) {
    repository.save(orderEntity);
  }
}
