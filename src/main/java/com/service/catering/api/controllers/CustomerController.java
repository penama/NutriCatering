package com.service.catering.api.controllers;

import com.service.catering.application.model.customer.CustomerDto;
import com.service.catering.application.model.paymentmethod.PaymentMethodDto;
import com.service.catering.application.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/catering" )
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping( "/customer" )
    public ResponseEntity newCustomer(@RequestBody CustomerDto customerDto){
        try {
            customerService.newCustomer( customerDto );
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping( "/customers" )
    public ResponseEntity<List<CustomerDto>> getCustomers(){
        List<CustomerDto> customerDtos = null;
        try {
            customerDtos = customerService.getCustomers();
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<CustomerDto>>(customerDtos, HttpStatus.OK);
    }

}