package com.service.catering.infraestructure.event.querys;

import com.service.catering.domain.model.PaymentMethodEntity;

import java.util.List;

public interface IQueryPaymentMethodRepository {

    public List<PaymentMethodEntity> queryPaymentMethod();

}
