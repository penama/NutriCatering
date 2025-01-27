package com.service.catering.api.controllers;

import com.service.catering.application.model.paymentmethod.PaymentMethodDto;
import com.service.catering.application.service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/catering" )
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @PostMapping( "/paymentMethod" )
    public ResponseEntity newPaymentMethod(@RequestBody PaymentMethodDto paymentMethodDto){
        try {
            paymentMethodService.newPaymentMethod( paymentMethodDto );
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping( "/paymentMethods" )
    public ResponseEntity<List<PaymentMethodDto>> getPaymentMethods(){
        List<PaymentMethodDto> paymentMethodDtos = null;
        try {
            paymentMethodDtos = paymentMethodService.getPaymentMethods();
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<PaymentMethodDto>>(paymentMethodDtos, HttpStatus.OK);
    }

}