package com.service.catering.application.model.order;

import lombok.Data;

@Data
public class OrderDto {

    public String id;
    public String description;
    public String creationDate;
    public String status;
    public float amount;
    public Contract contract;

}
