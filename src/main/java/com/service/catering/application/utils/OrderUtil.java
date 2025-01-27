package com.service.catering.application.utils;


import com.service.catering.application.model.contract.CateringPlan;
import com.service.catering.application.model.contract.ContractDto;
import com.service.catering.application.model.contract.Customer;
import com.service.catering.application.model.order.Contract;
import com.service.catering.application.model.order.OrderDto;
import com.service.catering.domain.model.ContractEntity;
import com.service.catering.domain.model.OrderEntity;

public class OrderUtil {

    public static OrderEntity orderDtoToOrderEntity(OrderDto orderDto){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId( orderDto.getId() );
        orderEntity.setDescription(orderDto.getDescription());
        orderEntity.setContractId( orderDto.getContract().getId() );
        orderEntity.setAmount( orderDto.getAmount() );
        orderEntity.setStatus( orderDto.getStatus() );
        orderEntity.setCreatedDate( orderDto.getCreationDate() );
        return orderEntity;
    }

    public static OrderDto orderEntityToOrderDto(OrderEntity orderEntity ){
        OrderDto orderDto = new OrderDto();
        orderDto.setId( orderEntity.getId() );
        orderDto.setDescription(orderEntity.getDescription());
        Contract contract = new Contract();
        contract.setId( orderEntity.getContractId() );
        orderDto.setContract( contract );
        orderDto.setAmount( orderEntity.getAmount() );
        orderDto.setStatus( orderEntity.getStatus() );
        orderDto.setCreationDate( orderEntity.getCreatedDate() );
        return orderDto;
    }

}
