package com.service.catering.application.model.payment;

import lombok.Data;

@Data
public class PaymentDto {

    public String id;
    public String createdDate;
    public String status;
//    private Invoice invoice;
    public PaymentMethod paymentMethod;
    public Price price;
    public Order order;
    public BillingInvoice billingInvoice;

}
