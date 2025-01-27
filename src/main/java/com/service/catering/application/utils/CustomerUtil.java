package com.service.catering.application.utils;


import com.service.catering.application.model.customer.CustomerDto;
import com.service.catering.domain.model.CustomerEntity;

public class CustomerUtil {

    public static CustomerEntity customerDtoToCustomerEntity(CustomerDto customerDto){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId( customerDto.getId() );
        customerEntity.setName(customerDto.getName() );
        customerEntity.setAddress( customerDto.getAddress()  );
        customerEntity.setPhone( customerDto.getPhone() );
        customerEntity.setBirtDate( customerDto.getBirtDate() );
        customerEntity.setStatus( customerDto.getStatus() );
        customerEntity.setCreatedDate( customerDto.getCreatedDate() );
        return customerEntity;
    }

    public static CustomerDto customerEntityToCustomerDto(CustomerEntity customerEntity ){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customerEntity.getId() );
        customerDto.setName(customerEntity.getName() );
        customerDto.setAddress(customerEntity.getAddress() );
        customerDto.setPhone( customerEntity.getPhone() );
        customerDto.setBirtDate(customerEntity.getBirtDate() );
        customerDto.setStatus(customerEntity.getStatus() );
        customerDto.setCreatedDate(customerEntity.getCreatedDate() );
        return customerDto;
    }

}
