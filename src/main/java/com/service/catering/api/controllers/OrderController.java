package com.service.catering.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.service.catering.application.model.error.ErrorDto;
import com.service.catering.application.model.order.OrderDto;
import com.service.catering.application.service.OrderService;

@RestController
@RequestMapping("/api/v1/catering")
public class OrderController extends BaseController {

  @Autowired private OrderService orderService;

  @GetMapping("/orders/{contractId}")
  public ResponseEntity<List<OrderDto>> getOrdersByContractId(@PathVariable String contractId) {
    List<OrderDto> orderDtos = null;
    try {
      orderDtos = orderService.getOrders(contractId);
    } catch (Exception e) {
      log.error(this.getClass(), e.getMessage(), e);
      return new ResponseEntity(new ErrorDto(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<List<OrderDto>>(orderDtos, HttpStatus.OK);
  }

  @GetMapping("/orders")
  public ResponseEntity<List<OrderDto>> getOrders() {
    List<OrderDto> orderDtos = null;
    try {
      orderDtos = orderService.getOrders();
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<List<OrderDto>>(orderDtos, HttpStatus.OK);
  }
}
