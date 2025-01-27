package com.service.catering.application.model.billerdata;

import lombok.Data;

@Data
public class BillerDataDto {

    public String id;
    public String socialReazon;
    public String nit;
    public String email;
    public Customer customer;
    public String createdDate;

}
