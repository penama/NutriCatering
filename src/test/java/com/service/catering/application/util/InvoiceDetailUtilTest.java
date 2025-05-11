package com.service.catering.application.util;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.service.catering.application.model.invoice.InvoiceDetail;
import com.service.catering.application.utils.InvoiceDetailUtil;
import com.service.catering.domain.model.InvoiceDetailEntity;

public class InvoiceDetailUtilTest {

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testInvoiceDetailToInvoiceDetailEntity() {
    InvoiceDetailEntity invoiceDetailEntity = new InvoiceDetailEntity();
    invoiceDetailEntity.setId(UUID.randomUUID().toString());
    invoiceDetailEntity.setInvoiceId(UUID.randomUUID().toString());
    invoiceDetailEntity.setCreatedDate("22/02/2025");

    InvoiceDetail invoiceDetail = new InvoiceDetail();
    invoiceDetail.id = UUID.randomUUID().toString();

    InvoiceDetailUtil invoiceDetailUtil = new InvoiceDetailUtil();
    InvoiceDetailEntity invoiceDetailEntityResult =
        InvoiceDetailUtil.InvoiceDetailToInvoiceDetailEntity(invoiceDetail);

    Assertions.assertNotNull(invoiceDetailEntityResult);
    Assertions.assertEquals(invoiceDetailEntityResult.getId(), invoiceDetail.id);
  }

  @Test
  public void testInvoiceDetailEntityToInvoiceDetail() {
    InvoiceDetailEntity invoiceDetailEntity = new InvoiceDetailEntity();
    invoiceDetailEntity.setId(UUID.randomUUID().toString());
    invoiceDetailEntity.setInvoiceId(UUID.randomUUID().toString());
    invoiceDetailEntity.setCreatedDate("22/02/2025");

    InvoiceDetail invoiceDetail = new InvoiceDetail();
    invoiceDetail.id = UUID.randomUUID().toString();

    InvoiceDetail invoiceDetailResult =
        InvoiceDetailUtil.InvoiceDetailEntityToInvoiceDetail(invoiceDetailEntity);

    Assertions.assertNotNull(invoiceDetailResult);
    Assertions.assertEquals(invoiceDetailResult.id, invoiceDetailEntity.getId());
  }
}
