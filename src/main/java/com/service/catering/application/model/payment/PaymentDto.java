package com.service.catering.application.model.payment;

import lombok.Data;

@Data
public class PaymentDto {

    private String id;
    private String createdDate;
    private String status;
//    private Invoice invoice;
    private PaymentMethod paymentMethod;
    private Price price;
    private Order order;
    private BillingInvoice billingInvoice;

}
