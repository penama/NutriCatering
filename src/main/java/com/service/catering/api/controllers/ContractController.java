package com.service.catering.api.controllers;

import java.util.List;

import com.service.catering.application.model.error.ErrorDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.service.catering.application.model.contract.ContractDto;
import com.service.catering.application.service.ContractService;

@RestController
@RequestMapping("/api/v1/catering")
public class ContractController extends BaseController{

  @Autowired private ContractService contractService;

  @PostMapping("/contract")
  public ResponseEntity newContract(@Valid @RequestBody ContractDto contractDto) {
	ContractDto contractDtoNew = null;
	try {
      contractDtoNew = contractService.newContract2(contractDto);
    } catch (Exception e) {
      log.error( this.getClass(), e.getMessage(), e );
      return new ResponseEntity( new ErrorDto( e.getMessage() ), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(contractDtoNew, HttpStatus.OK);
  }

  @GetMapping("/contracts")
  public ResponseEntity<List<ContractDto>> getContracts() {
    List<ContractDto> contractDtos = null;
    try {
      contractDtos = contractService.getContracts();
    } catch (Exception e) {
      log.error( this.getClass(), e.getMessage(), e );
      return new ResponseEntity( new ErrorDto( e.getMessage() ), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<List<ContractDto>>(contractDtos, HttpStatus.OK);
  }

  @GetMapping("/contract/{contractId}")
  public ResponseEntity<ContractDto> getContractId(@PathVariable String contractId) {
    ContractDto contractDto = null;
    try {
      contractDto = contractService.getContract(contractId);
    } catch (Exception e) {
      log.error( this.getClass(), e.getMessage(), e );
      return new ResponseEntity( new ErrorDto( e.getMessage() ), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<ContractDto>(contractDto, HttpStatus.OK);
  }

  @GetMapping("/customer/{customerId}/contracts")
  public ResponseEntity<List<ContractDto>> getContractsByCustomerId(
      @PathVariable String customerId) {
    List<ContractDto> contractDtos = null;
    try {
      contractDtos = contractService.getContractsByCustomerId(customerId);
    } catch (Exception e) {
		log.error( this.getClass(), e.getMessage(), e );
      return new ResponseEntity( new ErrorDto( e.getMessage() ), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<List<ContractDto>>(contractDtos, HttpStatus.OK);
  }
}
