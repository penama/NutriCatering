package com.service.catering.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "PaymentMethod")
public class PaymentMethodEntity {

    @Id
    public String id;
    public String name;
    public String label;
    public String descripcion;
    public String type;
    public String status;
    public String createdDate;

}
