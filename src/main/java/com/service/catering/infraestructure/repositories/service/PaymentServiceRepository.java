package com.service.catering.infraestructure.repositories.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.catering.domain.model.PaymentEntity;
import com.service.catering.domain.model.PaymentMethodEntity;
import com.service.catering.infraestructure.event.querys.IQueryPaymentRepository;
import com.service.catering.infraestructure.repositories.interfaces.PaymentMethodRepository;
import com.service.catering.infraestructure.repositories.interfaces.PaymentRepository;
import com.service.catering.infraestructure.utils.DateFormat;

@Service
public class PaymentServiceRepository implements IQueryPaymentRepository {

  @Autowired public PaymentRepository repository;

  @Autowired public PaymentMethodRepository paymentMethodRepository;

  public void newPayment(PaymentEntity paymentEntity) throws Exception {
    paymentEntity.id = UUID.randomUUID().toString();
    paymentEntity.createdDate = DateFormat.toDate();
    Optional<PaymentMethodEntity> optionalPaymentMethodEntity =
        paymentMethodRepository.findById(paymentEntity.paymentMethodEntity.id);
    PaymentMethodEntity paymentMethodEntity = null;
    if (!optionalPaymentMethodEntity.isPresent()) throw new Exception("PaymentMethod not exist");
    paymentMethodEntity = optionalPaymentMethodEntity.get();
    paymentEntity.paymentMethodEntity = paymentMethodEntity;
    repository.save(paymentEntity);
  }

  @Override
  public List<PaymentEntity> queryPayment() {
    return repository.findAll();
  }

  @Override
  public List<PaymentEntity> queryPaymentsByOrderId(String orderId) {
    return repository.findByOrderId(orderId);
  }
}
