package com.service.catering.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity( name = "Contract")
@Data
public class ContractEntity {

    @Id
    public String id;
    public String description;
    public String cateringPlanId;
    public String customerId;
    public int quotas;
    public float amount;
    public String createdDate;
    public String status;

}
