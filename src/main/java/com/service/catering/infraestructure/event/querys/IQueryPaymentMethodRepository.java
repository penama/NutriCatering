package com.service.catering.infraestructure.event.querys;

import java.util.List;

import com.service.catering.domain.model.PaymentMethodEntity;

public interface IQueryPaymentMethodRepository {

  public List<PaymentMethodEntity> queryPaymentMethod();
}
