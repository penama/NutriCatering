package com.service.catering.infraestructure.event.querys;

import java.util.List;

import com.service.catering.domain.model.InvoiceDetailEntity;

public interface IQueryInvoiceDetailRepository {

  public List<InvoiceDetailEntity> queryInvoiceDetails() throws Exception;

  public List<InvoiceDetailEntity> queryInvoiceDetailByInvoice(String invoiceId) throws Exception;
}
