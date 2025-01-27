package com.service.catering.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "InvoiceDetail")
@Data
public class InvoiceDetailEntity {

    @Id
    public String id;
    public String code;
    public float amount;
    public String concepts;
    public String description;
    public float unitPrice;
    public float discount;
    public float subtotal;
    public String currency;
    public String invoiceId;
    public String createdDate;

}
