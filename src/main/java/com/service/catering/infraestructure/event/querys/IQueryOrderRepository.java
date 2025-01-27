package com.service.catering.infraestructure.event.querys;

import com.service.catering.domain.model.OrderEntity;

import java.util.List;

public interface IQueryOrderRepository {

    public List<OrderEntity> queryOrders();

    public List<OrderEntity> queryOrders(String contractId);

    public OrderEntity queryOrder(String orderId);

    public void update(OrderEntity orderEntity);
}
