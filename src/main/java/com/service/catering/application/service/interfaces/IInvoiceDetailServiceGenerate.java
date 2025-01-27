package com.service.catering.application.service.interfaces;

import com.service.catering.application.model.invoice.InvoiceDetail;

public interface IInvoiceDetailServiceGenerate {

    public void generateInvoiceDetail( InvoiceDetail invoiceDetail ) throws  Exception;

}
