package com.service.catering.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.service.catering.application.model.customer.CustomerDto;
import com.service.catering.application.service.CustomerService;

@RestController
@RequestMapping("/api/v1/catering")
public class CustomerController {

  @Autowired private CustomerService customerService;

  @PostMapping("/customer")
  public ResponseEntity newCustomer(@RequestBody CustomerDto customerDto) {
    CustomerDto customerDtoResponse = null;
    try {
      customerDtoResponse = customerService.newCustomer2(customerDto);
      // customerService.newCustomer(customerDto);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(customerDtoResponse, HttpStatus.OK);
  }

  @GetMapping("/customers")
  public ResponseEntity<List<CustomerDto>> getCustomers() {
    List<CustomerDto> customerDtos = null;
    try {
      customerDtos = customerService.getCustomers();
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<List<CustomerDto>>(customerDtos, HttpStatus.OK);
  }

  @GetMapping("/customer/id/{id}")
  public ResponseEntity<CustomerDto> getCustomers(@PathVariable String id) {
    CustomerDto customerDto = null;
    try {
      customerDto = customerService.getCustomer(id);
      if (customerDto == null)
        return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<CustomerDto>(customerDto, HttpStatus.OK);
  }
}
