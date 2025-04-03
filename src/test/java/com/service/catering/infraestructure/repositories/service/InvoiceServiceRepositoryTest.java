package com.service.catering.infraestructure.repositories.service;

import com.service.catering.domain.model.InvoiceEntity;
import com.service.catering.infraestructure.repositories.interfaces.InvoiceRepository;
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

public class InvoiceServiceRepositoryTest {

    @Mock
    private InvoiceRepository invoiceRepository;
    @InjectMocks
    private InvoiceServiceRepository invoiceServiceRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test(){
        InvoiceEntity invoiceEntityMock = new InvoiceEntity();
        invoiceEntityMock.setContractId(UUID.randomUUID().toString());
        invoiceEntityMock.setId(UUID.randomUUID().toString());
        invoiceEntityMock.setPaymentId(UUID.randomUUID().toString());
        invoiceEntityMock.setCreatedDate("17/02/2025");
        invoiceEntityMock.setStatus("ACTIVO");
        invoiceEntityMock.setPeriod("022025");
        invoiceEntityMock.setTotal(100f);

        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setContractId(UUID.randomUUID().toString());
        invoiceEntity.setStatus("ACTIVO");
        invoiceEntity.setPeriod("022025");
        invoiceEntity.setTotal(100f);

        List<InvoiceEntity> listInvoiceMock = new ArrayList<>();
        listInvoiceMock.add( invoiceEntityMock );

        Mockito.when( invoiceRepository.save(Mockito.any( InvoiceEntity.class ) ) ).thenReturn( invoiceEntityMock );
        Mockito.when( invoiceRepository.findAll() ).thenReturn( listInvoiceMock );
        Mockito.when( invoiceRepository.findByPaymentId( Mockito.any( String.class ) ) ).thenReturn( listInvoiceMock );
        Mockito.when( invoiceRepository.findById( Mockito.any( String.class ) ) ).thenReturn(Optional.of( invoiceEntityMock ) );

        // guardar orden
        try {
            invoiceServiceRepository.newInvoice( invoiceEntity );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // listar
        List<InvoiceEntity> listResult = null;
        try {
            listResult = invoiceServiceRepository.queryInvoices();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assertions.assertNotNull( listResult, "listado no es nulo" );
        Assertions.assertEquals( invoiceEntityMock, listResult.getFirst(), "Objeto igual" );

        // listar por payment
        List<InvoiceEntity> listResult2 = null;
        try {
            listResult2 = invoiceServiceRepository.queryInvoiceByPayment(invoiceEntityMock.getPaymentId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Assertions.assertNotNull( listResult2, "listado no es nulo" );
        Assertions.assertEquals( invoiceEntityMock, listResult2.getFirst(), "Objeto igual" );

        //update.
        invoiceServiceRepository.generateInvoice( invoiceEntity );
    }
}
