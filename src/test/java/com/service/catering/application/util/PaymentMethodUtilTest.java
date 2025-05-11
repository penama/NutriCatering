package com.service.catering.application.util;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.service.catering.application.model.paymentmethod.PaymentMethodDto;
import com.service.catering.application.model.paymentmethod.PaymentMethodType;
import com.service.catering.application.utils.PaymentMethodUtil;
import com.service.catering.domain.model.PaymentMethodEntity;

public class PaymentMethodUtilTest {

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testPaymentMethodDtoToPaymentMethodEntity() {
    PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
    paymentMethodEntity.setId(UUID.randomUUID().toString());
    paymentMethodEntity.setName("Efectivo");
    paymentMethodEntity.setCreatedDate("22/02/2020");
    paymentMethodEntity.setLabel("Efectivo");
    paymentMethodEntity.setType(PaymentMethodType.EFECTIVO.name());

    PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
    paymentMethodDto.id = UUID.randomUUID().toString();
    paymentMethodDto.name = "Efectivo";
    paymentMethodDto.createdDate = "22/02/2020";
    paymentMethodDto.label = "Efectivo";
    paymentMethodDto.type = PaymentMethodType.EFECTIVO.name();

    PaymentMethodUtil paymentMethodUtil = new PaymentMethodUtil();
    PaymentMethodEntity paymentMethodEntityResult =
        PaymentMethodUtil.paymentMethodDtoToPaymentMethodEntity(paymentMethodDto);

    Assertions.assertNotNull(paymentMethodEntityResult);
    Assertions.assertEquals(paymentMethodEntityResult.getId(), paymentMethodDto.id);
  }

  @Test
  public void testPaymentEntityToPaymentMethodDto() {
    PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
    paymentMethodEntity.setId(UUID.randomUUID().toString());
    paymentMethodEntity.setName("Efectivo");
    paymentMethodEntity.setCreatedDate("22/02/2020");
    paymentMethodEntity.setLabel("Efectivo");
    paymentMethodEntity.setType(PaymentMethodType.EFECTIVO.name());
    paymentMethodEntity.setDescripcion("wtet");

    PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
    paymentMethodDto.id = UUID.randomUUID().toString();
    paymentMethodDto.name = "Efectivo";
    paymentMethodDto.createdDate = "22/02/2020";
    paymentMethodDto.label = "Efectivo";
    paymentMethodDto.type = PaymentMethodType.EFECTIVO.name();
    paymentMethodDto.description = "wtet";

    PaymentMethodUtil paymentMethodUtil = new PaymentMethodUtil();
    PaymentMethodDto paymentMethodDtoResult =
        PaymentMethodUtil.paymentEntityToPaymentMethodDto(paymentMethodEntity);

    Assertions.assertNotNull(paymentMethodDtoResult);
    Assertions.assertEquals(paymentMethodDtoResult.id, paymentMethodEntity.getId());
  }
}
