package com.service.catering.infraestructure.repositories.service;

import com.service.catering.domain.model.InvoiceDetailEntity;
import com.service.catering.domain.model.InvoiceEntity;
import com.service.catering.infraestructure.event.querys.IQueryInvoiceDetailRepository;
import com.service.catering.infraestructure.event.update.IGenerateInvoiceDetailRepository;
import com.service.catering.infraestructure.repositories.interfaces.InvoiceDetailRepository;
import com.service.catering.infraestructure.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InvoiceDetailServiceRepository implements IQueryInvoiceDetailRepository, IGenerateInvoiceDetailRepository {

    @Autowired
    public InvoiceDetailRepository repository;

    public void newInvoice(InvoiceDetailEntity invoiceDetailEntity) throws Exception{
        invoiceDetailEntity.setId(UUID.randomUUID().toString());
        invoiceDetailEntity.setCreatedDate(DateFormat.toDate());
        repository.save(invoiceDetailEntity);
    }

    @Override
    public List<InvoiceDetailEntity> queryInvoiceDetails() throws Exception {
        return repository.findAll();
    }

    @Override
    public List<InvoiceDetailEntity> queryInvoiceDetailByInvoice(String invoiceId) throws Exception {
        return repository.findByInvoiceId( invoiceId );
    }


    @Override
    public void generateInvoiceDetail(InvoiceDetailEntity invoiceDetailEntity) throws Exception {
        invoiceDetailEntity.setId(UUID.randomUUID().toString());
        invoiceDetailEntity.setCreatedDate(DateFormat.toDate());
        repository.save(invoiceDetailEntity);
    }
}
