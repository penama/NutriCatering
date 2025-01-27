package com.service.catering.application.service.interfaces;

import com.service.catering.application.model.invoice.InvoiceDetail;
import com.service.catering.application.model.invoice.InvoiceDto;

public interface IInvoiceServiceGenerate {

    public void generateInvoice(InvoiceDto invoiceDto) throws  Exception;

}
