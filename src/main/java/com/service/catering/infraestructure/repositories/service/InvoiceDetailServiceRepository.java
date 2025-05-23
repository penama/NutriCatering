package com.service.catering.infraestructure.repositories.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.catering.domain.model.InvoiceDetailEntity;
import com.service.catering.infraestructure.event.querys.IQueryInvoiceDetailRepository;
import com.service.catering.infraestructure.event.update.IGenerateInvoiceDetailRepository;
import com.service.catering.infraestructure.repositories.interfaces.InvoiceDetailRepository;
import com.service.catering.infraestructure.utils.DateFormat;

@Service
public class InvoiceDetailServiceRepository
    implements IQueryInvoiceDetailRepository, IGenerateInvoiceDetailRepository {

  @Autowired public InvoiceDetailRepository repository;

  public void newInvoice(InvoiceDetailEntity invoiceDetailEntity) throws Exception {
    invoiceDetailEntity.id = UUID.randomUUID().toString();
    invoiceDetailEntity.createdDate = DateFormat.toDate();
    repository.save(invoiceDetailEntity);
  }

  @Override
  public List<InvoiceDetailEntity> queryInvoiceDetails() throws Exception {
    return repository.findAll();
  }

  @Override
  public List<InvoiceDetailEntity> queryInvoiceDetailByInvoice(String invoiceId) throws Exception {
    return repository.findByInvoiceId(invoiceId);
  }

  @Override
  public void generateInvoiceDetail(InvoiceDetailEntity invoiceDetailEntity) throws Exception {
    invoiceDetailEntity.id = UUID.randomUUID().toString();
    invoiceDetailEntity.createdDate = DateFormat.toDate();
    repository.save(invoiceDetailEntity);
  }
}
