package com.service.catering.application.util;

import com.service.catering.application.model.order.Contract;
import com.service.catering.application.model.order.OrderDto;
import com.service.catering.application.model.order.OrderStatus;
import com.service.catering.application.utils.OrderUtil;
import com.service.catering.domain.model.OrderEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

public class OrderUtilTest {

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void TestOrderDtoToOrderEntity(){
        Contract contract = new Contract();
        contract.id = UUID.randomUUID().toString();

        OrderDto orderDto = new OrderDto();
        orderDto.id = UUID.randomUUID().toString();
        orderDto.contract = contract;
        orderDto.amount = 100;
        orderDto.status = OrderStatus.PAID.name();
        orderDto.description = "Catering";
        orderDto.creationDate = "22/02/2025";

        OrderEntity orderEntityMock = new OrderEntity();
        orderEntityMock.setId(UUID.randomUUID().toString());
        orderEntityMock.setContractId(UUID.randomUUID().toString());
        orderEntityMock.setAmount(100);
        orderEntityMock.setStatus(OrderStatus.PAID.name());
        orderEntityMock.setDescription("Catering");
        orderEntityMock.setCreatedDate("22/02/2025");

        OrderUtil orderUtil = new OrderUtil();
        OrderEntity orderEntityResult = OrderUtil.orderDtoToOrderEntity( orderDto );

        Assertions.assertNotNull( orderEntityResult );
        Assertions.assertEquals(orderEntityResult.getId(), orderDto.id );
    }

    @Test
    public void testCustomerEntityToCustomerDto() throws  Exception{
        Contract contract = new Contract();
        contract.id = UUID.randomUUID().toString();

        OrderDto orderDto = new OrderDto();
        orderDto.id = UUID.randomUUID().toString();
        orderDto.contract = contract;
        orderDto.amount = 100;
        orderDto.status = OrderStatus.PAID.name();
        orderDto.description = "Catering";
        orderDto.creationDate = "22/02/2025";

        OrderEntity orderEntityMock = new OrderEntity();
        orderEntityMock.setId(UUID.randomUUID().toString());
        orderEntityMock.setContractId(UUID.randomUUID().toString());
        orderEntityMock.setAmount(100);
        orderEntityMock.setStatus(OrderStatus.PAID.name());
        orderEntityMock.setDescription("Catering");
        orderEntityMock.setCreatedDate("22/02/2025");

        OrderUtil orderUtil = new OrderUtil();
        OrderDto orderDtoResult = OrderUtil.orderEntityToOrderDto( orderEntityMock );

        Assertions.assertNotNull( orderDtoResult );
        Assertions.assertEquals( orderDtoResult.id, orderEntityMock.getId());
    }

}
