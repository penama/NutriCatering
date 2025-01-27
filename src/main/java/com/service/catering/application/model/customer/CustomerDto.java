package com.service.catering.application.model.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private String id;
    private String name;
    private String address;
    private String phone;
    private String birtDate;
    private String createdDate;
    private String status;

}
