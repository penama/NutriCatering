package com.service.catering.infraestructure.event.querys;


import com.service.catering.domain.model.InvoiceEntity;

import java.util.List;

public interface IQueryInvoiceRepository {

    public List<InvoiceEntity> queryInvoices() throws  Exception;
    public List<InvoiceEntity> queryInvoiceByPayment( String paymentId )throws  Exception;

}
