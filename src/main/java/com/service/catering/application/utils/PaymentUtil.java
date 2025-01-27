package com.service.catering.application.utils;

import com.service.catering.application.model.payment.Order;
import com.service.catering.application.model.payment.PaymentDto;
import com.service.catering.application.model.payment.PaymentMethod;
import com.service.catering.application.model.payment.Price;
import com.service.catering.domain.model.PaymentEntity;
import com.service.catering.domain.model.PaymentMethodEntity;

public class PaymentUtil {

    public static PaymentEntity paymentDtoToPaymentEntity(PaymentDto paymentDto){
        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setId(paymentDto.getId() );
        paymentEntity.setCreatedDate(paymentDto.getCreatedDate() );
        PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
        paymentMethodEntity.setId(paymentDto.getPaymentMethod().getId() );
        paymentEntity.setPaymentMethodEntity( paymentMethodEntity );
        paymentEntity.setStatus(paymentDto.getStatus() );
        paymentEntity.setCurrency( paymentDto.getPrice().getCurrency() );
        paymentEntity.setAmount( paymentDto.getPrice().getAmount() );
        paymentEntity.setOrderId(  paymentDto.getOrder().getId() );
        return paymentEntity;
    }

    public static PaymentDto paymentEntityToPaymentDto(PaymentEntity paymentEntity){
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setId(paymentEntity.getId() );
        paymentDto.setCreatedDate(paymentEntity.getCreatedDate() );
        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setId(paymentEntity.getPaymentMethodEntity().getId() );
        paymentDto.setPaymentMethod( paymentMethod );
        paymentDto.setStatus(paymentEntity.getStatus() );
        Price price = new Price();
        price.setAmount( paymentEntity.getAmount() );
        price.setCurrency( paymentEntity.getCurrency() );
        paymentDto.setPrice( price );
        Order order = new Order();
        order.setId(paymentEntity.getOrderId() );
        paymentDto.setOrder( order );
        return paymentDto;
    }

}
