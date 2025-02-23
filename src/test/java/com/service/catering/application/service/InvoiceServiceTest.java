package com.service.catering.application.service;

import com.service.catering.application.model.invoice.InvoiceDetail;
import com.service.catering.application.model.invoice.InvoiceDto;
import com.service.catering.application.utils.InvoiceDetailUtil;
import com.service.catering.application.utils.InvoiceUtil;
import com.service.catering.application.utils.OrderUtil;
import com.service.catering.domain.model.InvoiceDetailEntity;
import com.service.catering.domain.model.InvoiceEntity;
import com.service.catering.infraestructure.event.command.CommandEntitysEvent;
import com.service.catering.infraestructure.event.querys.IQueryInvoiceDetailRepository;
import com.service.catering.infraestructure.event.querys.IQueryInvoiceRepository;
import com.service.catering.infraestructure.event.update.IGenerateInvoiceDetailRepository;
import com.service.catering.infraestructure.event.update.IGenerateInvoiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class InvoiceServiceTest {
    @Mock
    private IQueryInvoiceRepository iQueryInvoiceRepository;
    @Mock
    private IQueryInvoiceDetailRepository iQueryInvoiceDetailRepository;
    @Mock
    private IGenerateInvoiceRepository iGenerateInvoiceRepository;
    @Mock
    private IGenerateInvoiceDetailRepository iGenerateInvoiceDetailRepository;

    @InjectMocks
    private InvoiceService invoiceService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetInvoices() throws Exception{

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

        List<InvoiceEntity> listInvoice = Arrays.asList( invoiceEntity );
        List<InvoiceDetailEntity> listInvoiceDetail = Arrays.asList( invoiceDetailEntity );

        Mockito.when(iQueryInvoiceRepository.queryInvoices()).thenReturn( listInvoice );
        Mockito.when(iQueryInvoiceDetailRepository.queryInvoiceDetailByInvoice( Mockito.any( String.class ))).thenReturn( listInvoiceDetail );

        // Simulación de métodos estáticos con mockStatic()
        try (MockedStatic<InvoiceUtil> mockedStatic = mockStatic(InvoiceUtil.class)) {
            mockedStatic.when(() -> InvoiceUtil.InvoiceEntityToInvoiceDto( invoiceEntity )).thenReturn( invoiceDto );

            try (MockedStatic<InvoiceDetailUtil> mockedStaticDetail = mockStatic(InvoiceDetailUtil.class)) {
                mockedStaticDetail.when(() -> InvoiceDetailUtil.InvoiceDetailEntityToInvoiceDetail( invoiceDetailEntity )).thenReturn(invoiceDetail);

                List<InvoiceDto> listInvoiceDto = null;
                // Llamar al método a probar
                try {
                    listInvoiceDto = invoiceService.getInvoices();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Assertions.assertEquals( 1, listInvoiceDto.size() );
                Assertions.assertNotNull( listInvoiceDto );
            }
        }
    }

    @Test
    public void testGenerateInvoice() throws Exception{

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

        List<InvoiceEntity> listInvoice = Arrays.asList( invoiceEntity );
        List<InvoiceDetailEntity> listInvoiceDetail = Arrays.asList( invoiceDetailEntity );

        Mockito.doNothing().when(iGenerateInvoiceRepository).generateInvoice( Mockito.any( InvoiceEntity.class ) );
        Mockito.doNothing().when(iGenerateInvoiceDetailRepository).generateInvoiceDetail( Mockito.any( InvoiceDetailEntity.class ) );

        // Simulación de métodos estáticos con mockStatic()
        try (MockedStatic<InvoiceUtil> mockedStatic = mockStatic(InvoiceUtil.class)) {
            mockedStatic.when(() -> InvoiceUtil.InvoiceDtoToInvoiceEntity( invoiceDto )).thenReturn( invoiceEntity );

            try (MockedStatic<InvoiceDetailUtil> mockedStaticDetail = mockStatic(InvoiceDetailUtil.class)) {
                mockedStaticDetail.when(() -> InvoiceDetailUtil.InvoiceDetailToInvoiceDetailEntity(invoiceDetail)).thenReturn(invoiceDetailEntity);
                try {
                    invoiceService.generateInvoice( invoiceDto );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
