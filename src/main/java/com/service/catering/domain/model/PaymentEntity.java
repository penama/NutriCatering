package com.service.catering.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity( name = "Payment")
@Data
public class PaymentEntity {

    @Id
    public String id;
    public String createdDate;
    public String status;
//    public String invoiceId;

    @ManyToOne
    @JoinColumn(name = "paymentMethodId", referencedColumnName = "id")
    public PaymentMethodEntity paymentMethodEntity;

    public float amount;
    public String currency;
    public String orderId;


}
