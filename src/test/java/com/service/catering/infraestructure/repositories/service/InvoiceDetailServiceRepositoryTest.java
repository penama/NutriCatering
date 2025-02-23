package com.service.catering.infraestructure.repositories.service;

import com.service.catering.domain.model.InvoiceDetailEntity;
import com.service.catering.domain.model.InvoiceEntity;
import com.service.catering.infraestructure.repositories.interfaces.InvoiceDetailRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InvoiceDetailServiceRepositoryTest {

    @Mock
    private InvoiceDetailRepository invoiceDetailRepository;

    @InjectMocks
    private InvoiceDetailServiceRepository invoiceDetailServiceRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test(){
        InvoiceDetailEntity invoiceDetailEntityMock = new InvoiceDetailEntity();
        invoiceDetailEntityMock.invoiceId = UUID.randomUUID().toString();
        invoiceDetailEntityMock.amount = 100f;
        invoiceDetailEntityMock.id = UUID.randomUUID().toString();
        invoiceDetailEntityMock.createdDate = "17/02/2025";
        invoiceDetailEntityMock.unitPrice = 100f;
        invoiceDetailEntityMock.description = "-";

        InvoiceDetailEntity invoiceDetailEntity = new InvoiceDetailEntity();
        invoiceDetailEntity.amount = 100f;
        invoiceDetailEntity.createdDate = "17/02/2025";
        invoiceDetailEntity.unitPrice = 100f;
        invoiceDetailEntity.description = "-";

        List<InvoiceDetailEntity> listInvoiceDetailMock = new ArrayList<>();
        listInvoiceDetailMock.add( invoiceDetailEntityMock );

        Mockito.when( invoiceDetailRepository.save(Mockito.any( InvoiceDetailEntity.class ) ) ).thenReturn( invoiceDetailEntityMock );
        Mockito.when( invoiceDetailRepository.findAll() ).thenReturn( listInvoiceDetailMock );
        Mockito.when( invoiceDetailRepository.findByInvoiceId( Mockito.any( String.class ) ) ).thenReturn( listInvoiceDetailMock );
        Mockito.when( invoiceDetailRepository.findById( Mockito.any( String.class ) ) ).thenReturn(Optional.of( invoiceDetailEntityMock ) );

        // guardar orden
        try {
            invoiceDetailServiceRepository.newInvoice( invoiceDetailEntity );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // listar
        List<InvoiceDetailEntity> listResult = null;
        try {
            listResult = invoiceDetailServiceRepository.queryInvoiceDetails();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assertions.assertNotNull( listResult, "listado no es nulo" );
        Assertions.assertEquals( invoiceDetailEntityMock, listResult.getFirst(), "Objeto igual" );

        // listar por payment
        List<InvoiceDetailEntity> listResult2 = null;
        try {
            listResult2 = invoiceDetailServiceRepository.queryInvoiceDetailByInvoice( invoiceDetailEntityMock.invoiceId );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assertions.assertNotNull( listResult2, "listado no es nulo" );
        Assertions.assertEquals( invoiceDetailEntityMock, listResult2.getFirst(), "Objeto igual" );

        //update.
        try {
            invoiceDetailServiceRepository.generateInvoiceDetail( invoiceDetailEntity );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
