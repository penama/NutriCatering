package com.service.catering.application.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.catering.application.model.paymentmethod.PaymentMethodDto;
import com.service.catering.application.model.paymentmethod.PaymentMethodStatus;
import com.service.catering.application.utils.PaymentMethodUtil;
import com.service.catering.domain.model.PaymentMethodEntity;
import com.service.catering.infraestructure.event.querys.IQueryPaymentMethodRepository;

@Service
public class PaymentMethodService extends BaseCommandHandler {

  @Autowired private IQueryPaymentMethodRepository iQueryPaymentMethodRepository;

  public void newPaymentMethod(PaymentMethodDto paymentMethodDto) throws Exception {
    PaymentMethodEntity paymentMethodEntity =
        PaymentMethodUtil.paymentMethodDtoToPaymentMethodEntity(paymentMethodDto);
    paymentMethodEntity.status = PaymentMethodStatus.ACTIVE.name();
    //        CommandEntitysEvent commandEntitysEvent = new CommandEntitysEvent( this,
    // paymentMethodEntity);
    //        applicationEventPublisher.publishEvent( commandEntitysEvent );
    commandHandler(this, paymentMethodEntity);
  }

	public PaymentMethodDto newPaymentMethod2(PaymentMethodDto paymentMethodDto) throws Exception {
		PaymentMethodEntity paymentMethodEntity =
			PaymentMethodUtil.paymentMethodDtoToPaymentMethodEntity(paymentMethodDto);
		paymentMethodEntity.status = PaymentMethodStatus.ACTIVE.name();
		commandHandler(this, paymentMethodEntity);
		return PaymentMethodUtil.paymentEntityToPaymentMethodDto( paymentMethodEntity );
	}

  public List<PaymentMethodDto> getPaymentMethods() throws Exception {
    List<PaymentMethodEntity> paymentMethodEntities =
        iQueryPaymentMethodRepository.queryPaymentMethod();
    List<PaymentMethodDto> paymentMethodDtos = new ArrayList<>();
    for (PaymentMethodEntity paymentMethodEntity : paymentMethodEntities) {
      paymentMethodDtos.add(PaymentMethodUtil.paymentEntityToPaymentMethodDto(paymentMethodEntity));
    }
    return paymentMethodDtos;
  }
}
