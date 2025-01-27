package com.service.catering.api.controllers;

import com.service.catering.application.model.billerdata.BillerDataDto;
import com.service.catering.application.model.contract.ContractDto;
import com.service.catering.application.service.BillerDataService;
import com.service.catering.application.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/catering" )
public class BillerDataController {

    @Autowired
    private BillerDataService billerDataService;

    @PostMapping( "/billerData" )
    public ResponseEntity newBillerData(@RequestBody BillerDataDto billerDataDto){
        try {
            billerDataService.newBillerData( billerDataDto );
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping( "/billersData" )
    public ResponseEntity<List<BillerDataDto>> getContracts(){
        List<BillerDataDto> billerDataDtos = null;
        try {
            billerDataDtos = billerDataService.getBillersData();
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<BillerDataDto>>(billerDataDtos, HttpStatus.OK);
    }

}