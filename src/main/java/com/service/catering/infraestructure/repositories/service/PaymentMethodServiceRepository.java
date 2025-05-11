package com.service.catering.infraestructure.repositories.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.catering.domain.model.PaymentMethodEntity;
import com.service.catering.infraestructure.event.querys.IQueryPaymentMethodRepository;
import com.service.catering.infraestructure.repositories.interfaces.PaymentMethodRepository;
import com.service.catering.infraestructure.utils.DateFormat;

@Service
public class PaymentMethodServiceRepository implements IQueryPaymentMethodRepository {

  @Autowired public PaymentMethodRepository repository;

  public void newPaymentMethod(PaymentMethodEntity paymentMethodEntity) throws Exception {
    paymentMethodEntity.id = UUID.randomUUID().toString();
    paymentMethodEntity.createdDate = DateFormat.toDate();
    repository.save(paymentMethodEntity);
  }

  @Override
  public List<PaymentMethodEntity> queryPaymentMethod() {
    return repository.findAll();
  }
}
