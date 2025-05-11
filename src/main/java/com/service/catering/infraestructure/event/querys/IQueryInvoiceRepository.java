package com.service.catering.infraestructure.event.querys;

import java.util.List;

import com.service.catering.domain.model.InvoiceEntity;

public interface IQueryInvoiceRepository {

  public List<InvoiceEntity> queryInvoices() throws Exception;

  public List<InvoiceEntity> queryInvoiceByPayment(String paymentId) throws Exception;
}
