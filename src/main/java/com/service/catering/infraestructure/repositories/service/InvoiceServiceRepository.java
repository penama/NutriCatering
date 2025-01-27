package com.service.catering.infraestructure.repositories.service;

import com.service.catering.domain.model.InvoiceEntity;
import com.service.catering.infraestructure.event.querys.IQueryInvoiceRepository;
import com.service.catering.infraestructure.event.update.IGenerateInvoiceRepository;
import com.service.catering.infraestructure.repositories.interfaces.InvoiceRepository;
import com.service.catering.infraestructure.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InvoiceServiceRepository implements IQueryInvoiceRepository , IGenerateInvoiceRepository {

    @Autowired
    public InvoiceRepository repository;

    public void newInvoice(InvoiceEntity invoiceEntity) throws Exception{
        invoiceEntity.setId(UUID.randomUUID().toString());
        invoiceEntity.setCreatedDate(DateFormat.toDate());
        repository.save(invoiceEntity);
    }

    @Override
    public List<InvoiceEntity> queryInvoices() throws Exception {
        return repository.findAll();
    }

    @Override
    public List<InvoiceEntity> queryInvoiceByPayment(String paymentId) throws Exception {
        return repository.findByPaymentId( paymentId );
    }

    @Override
    public void generateInvoice(InvoiceEntity invoiceEntity) {
        invoiceEntity.setId(UUID.randomUUID().toString());
        invoiceEntity.setCreatedDate(DateFormat.toDate());
        repository.save( invoiceEntity );
    }
}
