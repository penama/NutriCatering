package com.service.catering.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity( name = "Customer")
@Data
public class CustomerEntity {

    @Id
    public String id;
    public String name;
    public String address;
    public String phone;
    public String birtDate;
    public String createdDate;
    public String status;

}
