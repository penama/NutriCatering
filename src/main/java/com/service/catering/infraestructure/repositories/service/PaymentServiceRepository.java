package com.service.catering.infraestructure.repositories.service;

import com.service.catering.application.model.invoice.InvoiceDto;
import com.service.catering.domain.model.PaymentEntity;
import com.service.catering.domain.model.PaymentMethodEntity;
import com.service.catering.infraestructure.event.querys.IQueryPaymentRepository;
import com.service.catering.infraestructure.repositories.interfaces.PaymentMethodRepository;
import com.service.catering.infraestructure.repositories.interfaces.PaymentRepository;
import com.service.catering.infraestructure.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentServiceRepository implements IQueryPaymentRepository {

    @Autowired
    public PaymentRepository repository;

    @Autowired
    public PaymentMethodRepository paymentMethodRepository;

    public void newPayment(PaymentEntity paymentEntity ) throws Exception{
        paymentEntity.setId(UUID.randomUUID().toString());
        paymentEntity.setCreatedDate(DateFormat.toDate());
        Optional<PaymentMethodEntity> optionalPaymentMethodEntity = paymentMethodRepository.findById( paymentEntity.getPaymentMethodEntity().getId() );
        PaymentMethodEntity paymentMethodEntity = null;
        if ( !optionalPaymentMethodEntity.isPresent() )
            throw new Exception("PaymentMethod not exist");
        paymentMethodEntity = optionalPaymentMethodEntity.get();
        paymentEntity.setPaymentMethodEntity(  paymentMethodEntity  );
        repository.save(paymentEntity);
    }

    @Override
    public List<PaymentEntity> queryPayment() {
        return repository.findAll();
    }

    @Override
    public List<PaymentEntity> queryPaymentsByOrderId(String orderId) {
        return repository.findByOrderId( orderId );
    }


}
