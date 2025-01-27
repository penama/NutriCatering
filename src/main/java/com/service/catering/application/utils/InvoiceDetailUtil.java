package com.service.catering.application.utils;


import com.service.catering.application.model.invoice.InvoiceDetail;
import com.service.catering.application.model.invoice.InvoiceDto;
import com.service.catering.domain.model.InvoiceDetailEntity;
import com.service.catering.domain.model.InvoiceEntity;

public class InvoiceDetailUtil {

    public static InvoiceDetailEntity InvoiceDetailToInvoiceDetailEntity(InvoiceDetail invoiceDetail){
        InvoiceDetailEntity invoiceDetailEntity = new InvoiceDetailEntity();
        invoiceDetailEntity.setId( invoiceDetail.getId() );
        invoiceDetailEntity.setInvoiceId( invoiceDetail.getInvoiceId());
        invoiceDetailEntity.setConcepts( invoiceDetail.getConcepts());
        invoiceDetailEntity.setDescription( invoiceDetail.getDescription());
        invoiceDetailEntity.setCurrency( invoiceDetail.getCurrency());
        invoiceDetailEntity.setAmount( invoiceDetail.getAmount()  );
        invoiceDetailEntity.setDiscount( invoiceDetail.getAmount() );
        invoiceDetailEntity.setUnitPrice( invoiceDetail.getUnitPrice() );
        invoiceDetailEntity.setSubtotal( invoiceDetail.getSubtotal() );
        invoiceDetailEntity.setCreatedDate( invoiceDetail.getCreatedDate() );
        return invoiceDetailEntity;
    }

    public static InvoiceDetail InvoiceDetailEntityToInvoiceDetail(InvoiceDetailEntity invoiceDetailEntity ){
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.setId( invoiceDetailEntity.getId() );
        invoiceDetail.setInvoiceId( invoiceDetailEntity.getInvoiceId());
        invoiceDetail.setConcepts( invoiceDetailEntity.getConcepts());
        invoiceDetail.setDescription( invoiceDetailEntity.getDescription());
        invoiceDetail.setCurrency( invoiceDetailEntity.getCurrency());
        invoiceDetail.setAmount( invoiceDetailEntity.getAmount()  );
        invoiceDetail.setDiscount( invoiceDetailEntity.getAmount() );
        invoiceDetail.setUnitPrice( invoiceDetailEntity.getUnitPrice() );
        invoiceDetail.setSubtotal( invoiceDetailEntity.getSubtotal() );
        invoiceDetail.setCreatedDate( invoiceDetailEntity.getCreatedDate() );
        return invoiceDetail;
    }

}
