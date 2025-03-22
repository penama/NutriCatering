package com.service.catering.infraestructure.repositories.service;

import com.service.catering.application.model.order.OrderStatus;
import com.service.catering.domain.model.OrderEntity;
import com.service.catering.infraestructure.repositories.interfaces.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderServiceRepositoryTest {
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderServiceRepository orderServiceRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test(){
        OrderEntity orderEntityMock = new OrderEntity();
        orderEntityMock.setId(UUID.randomUUID().toString());
        orderEntityMock.setCreatedDate("16/02/2025");
        orderEntityMock.setStatus(OrderStatus.PAID.name());
        orderEntityMock.setContractId(UUID.randomUUID().toString());
        orderEntityMock.setAmount(50);
        orderEntityMock.setDescription("contrato");

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCreatedDate("16/02/2025");
        orderEntity.setStatus(OrderStatus.PAID.name());
        orderEntity.setContractId(UUID.randomUUID().toString());
        orderEntity.setAmount(50);
        orderEntity.setDescription("contrato");

        List<OrderEntity> listOrderMock = new ArrayList<>();
        listOrderMock.add( orderEntityMock );

        Mockito.when( orderRepository.save(Mockito.any( OrderEntity.class ) ) ).thenReturn( orderEntityMock );
        Mockito.when( orderRepository.findAll() ).thenReturn( listOrderMock );
        Mockito.when( orderRepository.findByContractId( Mockito.any( String.class ) ) ).thenReturn( listOrderMock );
        Mockito.when( orderRepository.findById( Mockito.any( String.class ) ) ).thenReturn(Optional.of( orderEntityMock ) );

        // guardar orden
        try {
            orderServiceRepository.newContract( orderEntity );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // por id
        OrderEntity orderResult = orderServiceRepository.queryOrder(orderEntityMock.getId());
        Assertions.assertNotNull( orderResult, "listado no es nulo" );
        Assertions.assertEquals( orderEntityMock, orderResult, "Objeto igual" );
        // listar
        List<OrderEntity> listResult = orderServiceRepository.queryOrders();
        Assertions.assertNotNull( listResult, "listado no es nulo" );
        Assertions.assertEquals( orderEntityMock, listResult.getFirst(), "Objeto igual" );

        // listar por contrato
        List<OrderEntity> listResult2 = orderServiceRepository.queryOrders(orderEntityMock.getContractId());
        Assertions.assertNotNull( listResult2, "listado no es nulo" );
        Assertions.assertEquals( orderEntityMock, listResult2.getFirst(), "Objeto igual" );

        //update.
        orderServiceRepository.update( orderEntity );

    }
}
