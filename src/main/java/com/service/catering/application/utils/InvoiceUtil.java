package com.service.catering.application.utils;


import com.service.catering.application.model.invoice.InvoiceDto;
import com.service.catering.domain.model.InvoiceEntity;

public class InvoiceUtil {

    public static InvoiceEntity InvoiceDtoToInvoiceEntity(InvoiceDto invoiceDto){
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setId( invoiceDto.getId() );
        invoiceEntity.setCurrency( invoiceDto.getCurrency());
        invoiceEntity.setCustomerNit( invoiceDto.getCustomerNit()  );
        invoiceEntity.setCustomerSocialReason( invoiceDto.getCustomerSocialReason() );
        invoiceEntity.setCustomerEmail( invoiceDto.getCustomerEmail() );
        invoiceEntity.setCreatedDate( invoiceDto.getCreatedDate() );
        invoiceEntity.setTotal( invoiceDto.getTotal() );
        invoiceEntity.setPaymentId( invoiceDto.getPaymentId() );
        return invoiceEntity;
    }

    public static InvoiceDto InvoiceEntityToInvoiceDto(InvoiceEntity invoiceEntity ){
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setId( invoiceEntity.getId() );
        invoiceDto.setCurrency( invoiceEntity.getCurrency());
        invoiceDto.setCustomerNit( invoiceEntity.getCustomerNit()  );
        invoiceDto.setCustomerSocialReason( invoiceEntity.getCustomerSocialReason() );
        invoiceDto.setCustomerEmail( invoiceEntity.getCustomerEmail() );
        invoiceDto.setCreatedDate( invoiceEntity.getCreatedDate() );
        invoiceDto.setTotal( invoiceEntity.getTotal() );
        invoiceDto.setPaymentId( invoiceEntity.getPaymentId() );
        return invoiceDto;
    }

}
