package com.service.catering.application.model.invoice;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class InvoiceDto {

    public String id;
    public String companyName;
    public String companyBranch;
    public String companyAddress;
    public String companyPhone;
    public String companyCity;
    public String companyCountry;
    public String companyNit;
    public String number;
    public String authorization;
    public String placeOfEmission;
    public String issueDate;
    public String customerSocialReason;
    public String customerNit;
    public String customerAddress;
    public String customerEmail;
    public String period;
    public String contratId;
    public float totalDiscounts;
    public float total;
    public String currency;
    public String taxCreditAmount;
    public String literalAmount;
    public String legend1;
    public String legend2;
    public String legend3;
    public String status;
    public String paymentId;
    public String createdDate;
    private List<InvoiceDetail> invoiceDetails;

    public void addInvoiceDetail( InvoiceDetail invoiceDetail ){
        if (invoiceDetails == null || invoiceDetails.isEmpty() ){
            invoiceDetails = new ArrayList<>();
        }
        invoiceDetails.add( invoiceDetail );
    }
}
