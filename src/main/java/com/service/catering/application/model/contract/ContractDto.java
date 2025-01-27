package com.service.catering.application.model.contract;

import lombok.Data;

@Data
public class ContractDto {

    public String id;
    public String description;
    public String creationDate;
    public String status;
    public CateringPlan cateringPlan;
    public Customer customer;
    public float totalAmount;
    public Integer quotas;

}
