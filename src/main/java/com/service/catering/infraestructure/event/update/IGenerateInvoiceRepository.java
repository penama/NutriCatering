package com.service.catering.infraestructure.event.update;

import com.service.catering.domain.model.BillerDataEntity;
import com.service.catering.domain.model.InvoiceEntity;

public interface IGenerateInvoiceRepository {

    public void generateInvoice( InvoiceEntity invoiceEntity );

}
