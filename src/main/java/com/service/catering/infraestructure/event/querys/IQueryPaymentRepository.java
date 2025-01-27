package com.service.catering.infraestructure.event.querys;

import com.service.catering.application.model.invoice.InvoiceDto;
import com.service.catering.domain.model.PaymentEntity;

import java.util.List;

public interface IQueryPaymentRepository {

    public List<PaymentEntity> queryPayment();
    public List<PaymentEntity> queryPaymentsByOrderId( String orderId );

}
