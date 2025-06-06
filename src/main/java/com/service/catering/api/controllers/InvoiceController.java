package com.service.catering.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.service.catering.application.model.invoice.InvoiceDto;
import com.service.catering.application.service.InvoiceService;

@RestController
@RequestMapping("/api/v1/catering")
public class InvoiceController {

  @Autowired private InvoiceService invoiceService;

  @GetMapping("/invoices")
  public ResponseEntity<List<InvoiceDto>> getInvoices() {
    List<InvoiceDto> invoiceDtos = null;
    try {
      invoiceDtos = invoiceService.getInvoices();
    } catch (Exception e) {
      return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<List<InvoiceDto>>(invoiceDtos, HttpStatus.OK);
  }
}
