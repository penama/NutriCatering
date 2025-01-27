package com.service.catering.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity( name = "BillerData")
@Data
public class BillerDataEntity {

    @Id
    public String id;
    public String socialReazon;
    public String nit;
    public String email;
    public String customerId;
    public String createdDate;

}
