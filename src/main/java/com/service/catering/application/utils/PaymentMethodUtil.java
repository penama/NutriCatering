package com.service.catering.application.utils;

import com.service.catering.application.model.paymentmethod.PaymentMethodDto;
import com.service.catering.application.model.paymentmethod.PaymentMethodType;
import com.service.catering.domain.model.PaymentMethodEntity;

public class PaymentMethodUtil {

  public static PaymentMethodEntity paymentMethodDtoToPaymentMethodEntity(
      PaymentMethodDto paymentMethodDto) {
    PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
    paymentMethodEntity.setId(paymentMethodDto.id);
    paymentMethodEntity.setName(paymentMethodDto.getName());
    paymentMethodEntity.setLabel(paymentMethodDto.getLabel());
    paymentMethodEntity.setDescripcion(paymentMethodDto.getDescription());
    paymentMethodEntity.setType(PaymentMethodType.valueOf(paymentMethodDto.getType()).name());
    //        paymentMethodEntity.setStatus(PaymentMethodStatus.ACTIVE.fullName());
    return paymentMethodEntity;
  }

  public static PaymentMethodDto paymentEntityToPaymentMethodDto(
      PaymentMethodEntity paymentMethodEntity) {
    PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
    paymentMethodDto.setId(paymentMethodEntity.getId());
    paymentMethodDto.setName(paymentMethodEntity.getName());
    paymentMethodDto.setLabel(paymentMethodEntity.getLabel());
    paymentMethodDto.setDescription(paymentMethodEntity.getDescripcion());
    paymentMethodDto.setType(paymentMethodEntity.getType());
    paymentMethodDto.setStatus(paymentMethodEntity.getStatus());
    paymentMethodDto.setCreatedDate(paymentMethodEntity.getCreatedDate());
    return paymentMethodDto;
  }
}
