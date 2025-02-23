package com.service.catering.application.service;

import com.service.catering.application.model.invoice.InvoiceDetail;
import com.service.catering.application.utils.InvoiceDetailUtil;
import com.service.catering.application.utils.InvoiceUtil;
import com.service.catering.domain.model.InvoiceDetailEntity;
import com.service.catering.infraestructure.event.querys.IQueryInvoiceDetailRepository;
import com.service.catering.infraestructure.event.update.IGenerateInvoiceDetailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mockStatic;

public class InvoiceDetailServiceTest {

    @Mock
    private IQueryInvoiceDetailRepository iQueryInvoiceDetailRepository;
    @Mock
    private IGenerateInvoiceDetailRepository iGenerateInvoiceDetailRepository;
    @InjectMocks
    private InvoiceDetailService invoiceDetailService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetInvoiceDetails() throws Exception{

        InvoiceDetailEntity invoiceDetailEntity = new InvoiceDetailEntity();
        invoiceDetailEntity.id = UUID.randomUUID().toString();
        invoiceDetailEntity.invoiceId = UUID.randomUUID().toString();
        invoiceDetailEntity.createdDate = "22/02/2025";

        List<InvoiceDetailEntity> invoiceDetailEntityList = Arrays.asList( invoiceDetailEntity);

        Mockito.when( iQueryInvoiceDetailRepository.queryInvoiceDetailByInvoice( Mockito.anyString() ) ).thenReturn( invoiceDetailEntityList );

        List<InvoiceDetailEntity> list = invoiceDetailService.getInvoiceDetails( invoiceDetailEntity.invoiceId );

        Assertions.assertNotNull( list );
        Assertions.assertEquals( 1, list.size() );

    }

    @Test
    public void testGenerateInvoiceDetail() throws  Exception{

        InvoiceDetailEntity invoiceDetailEntity = new InvoiceDetailEntity();
        invoiceDetailEntity.id = UUID.randomUUID().toString();
        invoiceDetailEntity.invoiceId = UUID.randomUUID().toString();
        invoiceDetailEntity.createdDate = "22/02/2025";

        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.id = UUID.randomUUID().toString();

        Mockito.doNothing().when( iGenerateInvoiceDetailRepository ).generateInvoiceDetail( invoiceDetailEntity );
        try (MockedStatic<InvoiceDetailUtil> mockedStatic = mockStatic(InvoiceDetailUtil.class)) {
            mockedStatic.when(() -> InvoiceDetailUtil.InvoiceDetailToInvoiceDetailEntity( Mockito.any(InvoiceDetail.class) )).thenReturn(invoiceDetailEntity);

            invoiceDetailService.generateInvoiceDetail( invoiceDetail );

        }
    }

}
