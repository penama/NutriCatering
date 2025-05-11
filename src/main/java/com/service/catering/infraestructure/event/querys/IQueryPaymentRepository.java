package com.service.catering.infraestructure.event.querys;

import java.util.List;

import com.service.catering.domain.model.PaymentEntity;

public interface IQueryPaymentRepository {

  public List<PaymentEntity> queryPayment();

  public List<PaymentEntity> queryPaymentsByOrderId(String orderId);
}
