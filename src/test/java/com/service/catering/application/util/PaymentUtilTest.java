package com.service.catering.application.util;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.service.catering.application.model.payment.*;
import com.service.catering.application.utils.PaymentUtil;
import com.service.catering.domain.model.PaymentEntity;
import com.service.catering.domain.model.PaymentMethodEntity;

public class PaymentUtilTest {

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testPaymentDtoToPaymentEntity() {
    PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
    paymentMethodEntity.setId(UUID.randomUUID().toString());
    paymentMethodEntity.setLabel("Qr");
    paymentMethodEntity.setDescripcion("Qr");
    paymentMethodEntity.setName("Qr");
    paymentMethodEntity.setCreatedDate("22/02/2025");

    PaymentEntity paymentEntity = new PaymentEntity();
    paymentEntity.id = UUID.randomUUID().toString();
    paymentEntity.setPaymentMethodEntity(paymentMethodEntity);
    paymentEntity.setAmount(10);
    paymentEntity.setCurrency("BOB");
    paymentEntity.setOrderId(UUID.randomUUID().toString());
    paymentEntity.setStatus(PaymentStatus.PAID.name());
    paymentEntity.setCreatedDate("22/02/2025");

    PaymentMethod paymentMethod = new PaymentMethod();
    paymentMethod.id = UUID.randomUUID().toString();
    Price price = new Price();
    price.amount = 10;
    price.currency = "BOB";
    BillerData billerData = new BillerData();
    billerData.nit = "1213";
    billerData.email = "test@test";
    billerData.socialReason = "chagnos";
    BillingInvoice billingInvoice = new BillingInvoice();
    billingInvoice.customerId = UUID.randomUUID().toString();
    billingInvoice.contract = UUID.randomUUID().toString();
    billingInvoice.billerData = billerData;
    Order order = new Order();
    order.id = UUID.randomUUID().toString();

    PaymentDto paymentDto = new PaymentDto();
    paymentDto.id = UUID.randomUUID().toString();
    paymentDto.createdDate = "22/02/2025";
    paymentDto.status = PaymentStatus.PAID.name();
    paymentDto.paymentMethod = paymentMethod;
    paymentDto.price = price;
    paymentDto.billingInvoice = billingInvoice;
    paymentDto.order = order;

    PaymentEntity paymentEntityResult = PaymentUtil.paymentDtoToPaymentEntity(paymentDto);
    Assertions.assertNotNull(paymentEntityResult);
    Assertions.assertEquals(paymentEntityResult.id, paymentDto.id);
  }

  @Test
  public void testPaymentEntityToPaymentDto() {
    PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
    paymentMethodEntity.setId(UUID.randomUUID().toString());
    paymentMethodEntity.setLabel("Qr");
    paymentMethodEntity.setDescripcion("Qr");
    paymentMethodEntity.setName("Qr");
    paymentMethodEntity.setCreatedDate("22/02/2025");

    PaymentEntity paymentEntity = new PaymentEntity();
    paymentEntity.id = UUID.randomUUID().toString();
    paymentEntity.setPaymentMethodEntity(paymentMethodEntity);
    paymentEntity.setAmount(10);
    paymentEntity.setCurrency("BOB");
    paymentEntity.setOrderId(UUID.randomUUID().toString());
    paymentEntity.setStatus(PaymentStatus.PAID.name());
    paymentEntity.setCreatedDate("22/02/2025");

    PaymentMethod paymentMethod = new PaymentMethod();
    paymentMethod.id = UUID.randomUUID().toString();
    Price price = new Price();
    price.amount = 10;
    price.currency = "BOB";
    BillerData billerData = new BillerData();
    billerData.nit = "1213";
    billerData.email = "test@test";
    billerData.socialReason = "chagnos";
    BillingInvoice billingInvoice = new BillingInvoice();
    billingInvoice.customerId = UUID.randomUUID().toString();
    billingInvoice.contract = UUID.randomUUID().toString();
    billingInvoice.billerData = billerData;
    Order order = new Order();
    order.id = UUID.randomUUID().toString();

    PaymentDto paymentDto = new PaymentDto();
    paymentDto.id = UUID.randomUUID().toString();
    paymentDto.createdDate = "22/02/2025";
    paymentDto.status = PaymentStatus.PAID.name();
    paymentDto.paymentMethod = paymentMethod;
    paymentDto.price = price;
    paymentDto.billingInvoice = billingInvoice;
    paymentDto.order = order;

    PaymentUtil paymentUtil = new PaymentUtil();

    PaymentDto paymentDtoResult = PaymentUtil.paymentEntityToPaymentDto(paymentEntity);
    Assertions.assertNotNull(paymentDtoResult);
    Assertions.assertEquals(paymentDtoResult.id, paymentEntity.id);
  }
}
