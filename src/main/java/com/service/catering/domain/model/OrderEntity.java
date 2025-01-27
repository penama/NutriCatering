package com.service.catering.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity( name = "Ordenes")
@Data
public class OrderEntity {

    @Id
    public String id;
    public String description;
    public String contractId;
    public float amount;
    public String createdDate;
    public String status;

}
