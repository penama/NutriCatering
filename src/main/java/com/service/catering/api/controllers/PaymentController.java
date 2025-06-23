package com.service.catering.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.service.catering.application.model.error.ErrorDto;
import com.service.catering.application.model.payment.PaymentDto;
import com.service.catering.application.service.PaymentService;

@RestController
@RequestMapping("/api/v1/catering")
public class PaymentController extends BaseController {

  @Autowired private PaymentService paymentService;

  @PostMapping("/payment")
  public ResponseEntity newPayment(@RequestBody PaymentDto paymentDto) {
    PaymentDto paymentDtoNew = null;
    try {
      paymentDtoNew = paymentService.newPayment2(paymentDto);
    } catch (Exception e) {
      log.error(this.getClass(), e.getMessage(), e);
      return new ResponseEntity(new ErrorDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity(paymentDtoNew, HttpStatus.OK);
  }

  @GetMapping("/payments")
  public ResponseEntity<List<PaymentDto>> getPayments() {
    List<PaymentDto> paymentDtos = null;
    try {
      paymentDtos = paymentService.getPayments();
    } catch (Exception e) {
      log.error(this.getClass(), e.getMessage(), e);
      return new ResponseEntity(new ErrorDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<List<PaymentDto>>(paymentDtos, HttpStatus.OK);
  }

  @GetMapping("/order/{orderId}/payments")
  public ResponseEntity<List<PaymentDto>> getPaymentsByOrderId(@PathVariable String orderId) {
    List<PaymentDto> paymentDtos = null;
    try {
      paymentDtos = paymentService.getPaymentsByOrderId(orderId);
    } catch (Exception e) {
      log.error(this.getClass(), e.getMessage(), e);
      return new ResponseEntity(new ErrorDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<List<PaymentDto>>(paymentDtos, HttpStatus.OK);
  }
}
