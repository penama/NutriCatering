package com.service.catering.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "Invoices")
@Data
public class InvoiceEntity {

    @Id
    public String id;
    public String companyName;
    public String companyBranch;
    public String companyAddress;
    public String companyPhone;
    public String companyCity;
    public String companyCountry;
    public String companyNit;
    public String number;
    public String aaauthorization;
    public String placeOfEmission;
    public String createdDate;
    public String issueDate;
    public String customerSocialReason;
    public String customerNit;
    public String customerAddress;
    public String customerEmail;
    public String period;
    public String contractId;
    public float totalDiscounts;
    public float total;
    public String currency;
    public float taxCreditAmount;
    public String literalAmount;
    public String legend1;
    public String legend2;
    public String legend3;
    public String status;
    public String paymentId;

}
