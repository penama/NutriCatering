package com.service.catering.application.model.payment;

import lombok.Data;

@Data
public class BillingInvoice {

    public String customerId;
    public String contract;
    public BillerData billerData;

}
