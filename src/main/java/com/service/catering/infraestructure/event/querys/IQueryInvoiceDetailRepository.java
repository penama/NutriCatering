package com.service.catering.infraestructure.event.querys;


import com.service.catering.domain.model.InvoiceDetailEntity;

import java.util.List;

public interface IQueryInvoiceDetailRepository {

    public List<InvoiceDetailEntity> queryInvoiceDetails() throws  Exception;
    public List<InvoiceDetailEntity> queryInvoiceDetailByInvoice( String invoiceId ) throws  Exception;

}
