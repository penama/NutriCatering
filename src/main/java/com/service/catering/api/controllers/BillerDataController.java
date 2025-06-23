package com.service.catering.api.controllers;

import java.util.List;

import com.service.catering.application.model.error.ErrorDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.service.catering.application.model.billerdata.BillerDataDto;
import com.service.catering.application.service.BillerDataService;

@RestController
@RequestMapping("/api/v1/catering")
public class BillerDataController extends BaseController{

  @Autowired private BillerDataService billerDataService;

  @PostMapping("/billerData")
  public ResponseEntity newBillerData(@Valid @RequestBody BillerDataDto billerDataDto ) {
	  BillerDataDto billerDataDtoNew = null;
	  try {
      billerDataDtoNew = billerDataService.newBillerData2(billerDataDto);
    } catch (Exception e) {
		  ErrorDto errorDto = new ErrorDto( e.getMessage() );
		  log.error( this.getClass(), e.getMessage(), e );
      return ResponseEntity.internalServerError().body( errorDto );
    }
    return new ResponseEntity(billerDataDtoNew,HttpStatus.OK);
  }

  @GetMapping("/billersData")
  public ResponseEntity<List<BillerDataDto>> getContracts() {
    List<BillerDataDto> billerDataDtos = null;
    try {
      billerDataDtos = billerDataService.getBillersData();
    } catch (Exception e) {
		log.error( this.getClass(), e.getMessage(), e );
      return new ResponseEntity( new ErrorDto( e.getMessage() ), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<List<BillerDataDto>>(billerDataDtos, HttpStatus.OK);
  }
}
