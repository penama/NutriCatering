package com.service.catering.api.controllers;

import com.service.catering.application.model.contract.ContractDto;
import com.service.catering.application.model.customer.CustomerDto;
import com.service.catering.application.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( "/api/v1/catering" )
public class ContractController {

    @Autowired
    private ContractService contractService;

    @PostMapping( "/contract" )
    public ResponseEntity newContract(@RequestBody ContractDto contractDto){
        try {
            contractService.newContract( contractDto );
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping( "/contracts" )
    public ResponseEntity<List<ContractDto>> getContracts(){
        List<ContractDto> contractDtos = null;
        try {
            contractDtos = contractService.getContracts();
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<ContractDto>>(contractDtos, HttpStatus.OK);
    }

    @GetMapping( "/contract/{contractId}" )
    public ResponseEntity<ContractDto> getContractId(@PathVariable String contractId ){
        ContractDto contractDto = null;
        try {
            contractDto = contractService.getContract( contractId );
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ContractDto>(contractDto, HttpStatus.OK);
    }

    @GetMapping( "/customer/{customerId}/contracts" )
    public ResponseEntity<List<ContractDto>> getContractsByCustomerId(@PathVariable String customerId ){
        List<ContractDto> contractDtos = null;
        try {
            contractDtos = contractService.getContractsByCustomerId( customerId );
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<ContractDto>>(contractDtos, HttpStatus.OK);
    }

}