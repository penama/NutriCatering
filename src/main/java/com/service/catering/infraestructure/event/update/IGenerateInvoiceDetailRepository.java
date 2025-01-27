package com.service.catering.infraestructure.event.update;

import com.service.catering.domain.model.InvoiceDetailEntity;

public interface IGenerateInvoiceDetailRepository {

    public void generateInvoiceDetail( InvoiceDetailEntity invoiceDetailEntity ) throws Exception;

}
