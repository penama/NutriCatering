package com.service.catering.application.util;

import com.service.catering.application.model.contract.CateringPlan;
import com.service.catering.application.model.contract.ContractDto;
import com.service.catering.application.model.contract.ContractStatus;
import com.service.catering.application.model.contract.Customer;
import com.service.catering.application.model.invoice.InvoiceDetail;
import com.service.catering.application.utils.ContractUtil;
import com.service.catering.application.utils.InvoiceDetailUtil;
import com.service.catering.domain.model.ContractEntity;
import com.service.catering.domain.model.InvoiceDetailEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

public class InvoiceDetailUtilTest {

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInvoiceDetailToInvoiceDetailEntity(){
        InvoiceDetailEntity invoiceDetailEntity = new InvoiceDetailEntity();
        invoiceDetailEntity.id = UUID.randomUUID().toString();
        invoiceDetailEntity.invoiceId = UUID.randomUUID().toString();
        invoiceDetailEntity.createdDate = "22/02/2025";

        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.id = UUID.randomUUID().toString();

        InvoiceDetailUtil invoiceDetailUtil = new InvoiceDetailUtil();
        InvoiceDetailEntity invoiceDetailEntityResult = InvoiceDetailUtil.InvoiceDetailToInvoiceDetailEntity( invoiceDetail );

        Assertions.assertNotNull( invoiceDetailEntityResult );
        Assertions.assertEquals( invoiceDetailEntityResult.id, invoiceDetail.id );
    }

    @Test
    public void testInvoiceDetailEntityToInvoiceDetail(){
        InvoiceDetailEntity invoiceDetailEntity = new InvoiceDetailEntity();
        invoiceDetailEntity.id = UUID.randomUUID().toString();
        invoiceDetailEntity.invoiceId = UUID.randomUUID().toString();
        invoiceDetailEntity.createdDate = "22/02/2025";

        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.id = UUID.randomUUID().toString();

        InvoiceDetail invoiceDetailResult = InvoiceDetailUtil.InvoiceDetailEntityToInvoiceDetail( invoiceDetailEntity );

        Assertions.assertNotNull( invoiceDetailResult );
        Assertions.assertEquals( invoiceDetailResult.id, invoiceDetailEntity.id );
    }
}
