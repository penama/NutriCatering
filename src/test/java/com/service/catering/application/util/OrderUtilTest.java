package com.service.catering.application.util;

import com.service.catering.application.model.customer.CustomerDto;
import com.service.catering.application.model.customer.CustomerStatus;
import com.service.catering.application.model.order.Contract;
import com.service.catering.application.model.order.OrderDto;
import com.service.catering.application.model.order.OrderStatus;
import com.service.catering.application.utils.CustomerUtil;
import com.service.catering.application.utils.OrderUtil;
import com.service.catering.domain.model.CustomerEntity;
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
        orderEntityMock.id = UUID.randomUUID().toString();
        orderEntityMock.contractId = UUID.randomUUID().toString();
        orderEntityMock.amount = 100;
        orderEntityMock.status = OrderStatus.PAID.name();
        orderEntityMock.description = "Catering";
        orderEntityMock.createdDate = "22/02/2025";

        OrderUtil orderUtil = new OrderUtil();
        OrderEntity orderEntityResult = OrderUtil.orderDtoToOrderEntity( orderDto );

        Assertions.assertNotNull( orderEntityResult );
        Assertions.assertEquals( orderEntityResult.id, orderDto.id );
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
        orderEntityMock.id = UUID.randomUUID().toString();
        orderEntityMock.contractId = UUID.randomUUID().toString();
        orderEntityMock.amount = 100;
        orderEntityMock.status = OrderStatus.PAID.name();
        orderEntityMock.description = "Catering";
        orderEntityMock.createdDate = "22/02/2025";

        OrderUtil orderUtil = new OrderUtil();
        OrderDto orderDtoResult = OrderUtil.orderEntityToOrderDto( orderEntityMock );

        Assertions.assertNotNull( orderDtoResult );
        Assertions.assertEquals( orderDtoResult.id, orderEntityMock.id );
    }

}
