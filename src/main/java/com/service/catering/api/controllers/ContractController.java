package com.service.catering.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.service.catering.application.model.contract.ContractDto;
import com.service.catering.application.service.ContractService;

import io.sentry.Sentry;

@RestController
@RequestMapping("/api/v1/catering")
public class ContractController {

  @Autowired private ContractService contractService;

  @PostMapping("/contract")
  public ResponseEntity newContract(@RequestBody ContractDto contractDto) {
    try {
      Sentry.captureMessage("Create newContract");
      contractService.newContract(contractDto);
    } catch (Exception e) {
      Sentry.captureException(e);
      e.printStackTrace();
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(HttpStatus.OK);
  }

  @GetMapping("/contracts")
  public ResponseEntity<List<ContractDto>> getContracts() {
    List<ContractDto> contractDtos = null;

    try {
      throw new Exception("This is a test.");
    } catch (Exception e) {
      Sentry.captureException(e);
      e.printStackTrace();
    }

    try {
      Sentry.captureMessage("list Contracts");
      contractDtos = contractService.getContracts();
    } catch (Exception e) {
      Sentry.captureException(e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<List<ContractDto>>(contractDtos, HttpStatus.OK);
  }

  @GetMapping("/contract/{contractId}")
  public ResponseEntity<ContractDto> getContractId(@PathVariable String contractId) {
    ContractDto contractDto = null;
    try {
      Sentry.captureMessage("get Contract ID");
      contractDto = contractService.getContract(contractId);
    } catch (Exception e) {
      Sentry.captureException(e);
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<ContractDto>(contractDto, HttpStatus.OK);
  }

  @GetMapping("/customer/{customerId}/contracts")
  public ResponseEntity<List<ContractDto>> getContractsByCustomerId(
      @PathVariable String customerId) {
    Sentry.captureMessage("get Contract for Customer ID");
    List<ContractDto> contractDtos = null;
    try {
      contractDtos = contractService.getContractsByCustomerId(customerId);
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<List<ContractDto>>(contractDtos, HttpStatus.OK);
  }
}
