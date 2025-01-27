package com.service.catering.application.service;


import com.service.catering.application.model.order.Contract;
import com.service.catering.application.model.order.OrderDto;
import com.service.catering.application.model.order.OrderStatus;
import com.service.catering.application.model.payment.PaymentDto;
import com.service.catering.application.service.interfaces.IOrderServiceCreateByContract;
import com.service.catering.application.service.interfaces.IOrderServiceUpdateStatus;
import com.service.catering.application.utils.ContractUtil;
import com.service.catering.application.utils.OrderUtil;
import com.service.catering.domain.model.ContractEntity;
import com.service.catering.domain.model.OrderEntity;
import com.service.catering.domain.model.PaymentEntity;
import com.service.catering.infraestructure.event.querys.IQueryContractRepository;
import com.service.catering.infraestructure.event.querys.IQueryOrderRepository;
import com.service.catering.infraestructure.event.querys.IQueryPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService extends BaseService implements IOrderServiceUpdateStatus, IOrderServiceCreateByContract {

    @Autowired
    private IQueryOrderRepository iQueryOrderRepository;

    @Autowired
    private IQueryPaymentRepository iQueryPaymentRepository;

    @Autowired
    private IQueryContractRepository iQueryContractRepository;

    public void newOrder(OrderDto orderDto) throws Exception {
        OrderEntity orderEntity = OrderUtil.orderDtoToOrderEntity( orderDto );
        orderEntity.setStatus(OrderStatus.UNPAID.name());
        commandHandler( this, orderEntity );
    }

    public List<OrderDto> getOrders() throws Exception {
        List<OrderEntity> orderEntities = iQueryOrderRepository.queryOrders();
        List<OrderDto> orderDtos = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            orderDtos.add( OrderUtil.orderEntityToOrderDto(orderEntity) );
        }
        return orderDtos;
    }

    public List<OrderDto> getOrders( String contractId ) throws Exception {
        List<OrderEntity> orderEntities = iQueryOrderRepository.queryOrders( contractId );
        List<OrderDto> orderDtos = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntities) {
            orderDtos.add( OrderUtil.orderEntityToOrderDto(orderEntity) );
        }
        return orderDtos;
    }

    @Override
    @Transactional( propagation = Propagation.MANDATORY)
    public void actualizarStatus(String orderId) throws  Exception{
        OrderEntity orderEntity = iQueryOrderRepository.queryOrder( orderId );
        List<PaymentEntity> paymentEntities = iQueryPaymentRepository.queryPaymentsByOrderId( orderId );
        float sum = 0;
        for (PaymentEntity paymentEntity : paymentEntities) {
            sum += paymentEntity.getAmount();
        }
        if ( orderEntity.getAmount() == sum ) {
            orderEntity.setStatus(OrderStatus.PAID.name());
        } else if (orderEntity.getAmount() > sum){
            orderEntity.setStatus(OrderStatus.PARTIAL_PAYMENT.name());
        }
        iQueryOrderRepository.update(orderEntity);
    }

    @Override
    @Transactional( propagation = Propagation.MANDATORY)
    public void generateOrdersForContract(String contractId) throws  Exception{
        ContractEntity contractEntity = iQueryContractRepository.queryContractId( contractId );
        if ( contractEntity.getQuotas() == 1 ) return;
        float amount = contractEntity.getAmount() / contractEntity.getQuotas();
        for ( int i = 0; i < contractEntity.getQuotas(); i++ ){
            OrderDto orderDto = new OrderDto();
            Contract contract = new Contract();
            contract.setId( contractEntity.getId() );
            orderDto.setContract( contract );
            orderDto.setAmount( amount );
            orderDto.setDescription( contractEntity.getDescription() + " - " + (i+1) );
            newOrder( orderDto );
        }
    }
}
