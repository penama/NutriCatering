package com.service.catering.application.util;

import com.service.catering.application.model.invoice.InvoiceDetail;
import com.service.catering.application.model.invoice.InvoiceDto;
import com.service.catering.application.utils.InvoiceUtil;
import com.service.catering.domain.model.InvoiceDetailEntity;
import com.service.catering.domain.model.InvoiceEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

public class InvoiceUtilTest {

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInvoiceDtoToInvoiceEntity(){
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.id = UUID.randomUUID().toString();
        invoiceEntity.total = 100;
        invoiceEntity.createdDate = "22/02/2025";

        InvoiceDetailEntity invoiceDetailEntity = new InvoiceDetailEntity();
        invoiceDetailEntity.id = UUID.randomUUID().toString();
        invoiceDetailEntity.invoiceId = invoiceEntity.id;
        invoiceDetailEntity.unitPrice = 10;
        invoiceDetailEntity.amount = 10;
        invoiceDetailEntity.concepts = "algo de algo";

        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.id = UUID.randomUUID().toString();
        invoiceDto.total = 100;
        invoiceDto.createdDate = "22/02/2025";

        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.id = UUID.randomUUID().toString();
        invoiceDetail.invoiceId = invoiceDto.id;
        invoiceDetail.unitPrice = 100;
        invoiceDetail.subtotal = 100;
        invoiceDetail.amount = 1;

        invoiceDto.addInvoiceDetail( invoiceDetail );

        InvoiceUtil invoiceUtil = new InvoiceUtil();
        InvoiceEntity invoiceEntityResult = InvoiceUtil.InvoiceDtoToInvoiceEntity( invoiceDto );

        Assertions.assertNotNull( invoiceEntityResult );
        Assertions.assertEquals( invoiceEntityResult.id, invoiceDto.id );
    }

    @Test
    public void testInvoiceDetailEntityToInvoiceDetail(){
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.id = UUID.randomUUID().toString();
        invoiceEntity.total = 100;
        invoiceEntity.createdDate = "22/02/2025";

        InvoiceDetailEntity invoiceDetailEntity = new InvoiceDetailEntity();
        invoiceDetailEntity.id = UUID.randomUUID().toString();
        invoiceDetailEntity.invoiceId = invoiceEntity.id;
        invoiceDetailEntity.unitPrice = 10;
        invoiceDetailEntity.amount = 10;
        invoiceDetailEntity.concepts = "algo de algo";

        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.id = UUID.randomUUID().toString();
        invoiceDto.total = 100;
        invoiceDto.createdDate = "22/02/2025";

        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.id = UUID.randomUUID().toString();
        invoiceDetail.invoiceId = invoiceDto.id;
        invoiceDetail.unitPrice = 100;
        invoiceDetail.subtotal = 100;
        invoiceDetail.amount = 1;

        invoiceDto.addInvoiceDetail( invoiceDetail );

        InvoiceDto invoiceDtoResult = InvoiceUtil.InvoiceEntityToInvoiceDto( invoiceEntity );

        Assertions.assertNotNull( invoiceDtoResult );
        Assertions.assertEquals( invoiceDtoResult.id, invoiceEntity.id );
    }
}
