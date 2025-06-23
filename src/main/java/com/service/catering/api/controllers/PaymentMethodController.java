package com.service.catering.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.service.catering.application.model.error.ErrorDto;
import com.service.catering.application.model.paymentmethod.PaymentMethodDto;
import com.service.catering.application.service.PaymentMethodService;

@RestController
@RequestMapping("/api/v1/catering")
public class PaymentMethodController extends BaseController {

  @Autowired private PaymentMethodService paymentMethodService;

  @PostMapping("/paymentMethod")
  public ResponseEntity newPaymentMethod(@RequestBody PaymentMethodDto paymentMethodDto) {
    PaymentMethodDto paymentMethodDtoNew = null;
    try {
      paymentMethodDtoNew = paymentMethodService.newPaymentMethod2(paymentMethodDto);
    } catch (Exception e) {
      log.error(this.getClass(), e.getMessage(), e);
      return new ResponseEntity(new ErrorDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(paymentMethodDtoNew, HttpStatus.OK);
  }

  @GetMapping("/paymentMethods")
  public ResponseEntity<List<PaymentMethodDto>> getPaymentMethods() {
    List<PaymentMethodDto> paymentMethodDtos = null;
    try {
      paymentMethodDtos = paymentMethodService.getPaymentMethods();
    } catch (Exception e) {
      log.error(this.getClass(), e.getMessage(), e);
      return new ResponseEntity(new ErrorDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<List<PaymentMethodDto>>(paymentMethodDtos, HttpStatus.OK);
  }
}
