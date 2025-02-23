package com.service.catering.infraestructure.repositories.service;

import com.service.catering.application.model.payment.PaymentStatus;
import com.service.catering.application.model.paymentmethod.PaymentMethodStatus;
import com.service.catering.domain.model.PaymentEntity;
import com.service.catering.domain.model.PaymentMethodEntity;
import com.service.catering.infraestructure.repositories.interfaces.PaymentMethodRepository;
import com.service.catering.infraestructure.repositories.interfaces.PaymentRepository;
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

public class PaymentServiceRepositoryTest {
    @Mock
    private PaymentRepository paymentRepository;
    @Mock
    private PaymentMethodRepository paymentMethodRepository;
    @InjectMocks
    private PaymentServiceRepository paymentServiceRepository;
    @InjectMocks
    private PaymentMethodServiceRepository paymentMethodServiceRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test(){
        PaymentMethodEntity paymentMethodEntityMock = new PaymentMethodEntity();
        paymentMethodEntityMock.id = UUID.randomUUID().toString();
        paymentMethodEntityMock.createdDate = "18/02/2025";
        paymentMethodEntityMock.status = PaymentMethodStatus.ACTIVE.name();

        PaymentEntity paymentEntityMock = new PaymentEntity();
        paymentEntityMock.id = UUID.randomUUID().toString();
        paymentEntityMock.createdDate = "19/02/2025";
        paymentEntityMock.orderId = UUID.randomUUID().toString();
        paymentEntityMock.amount = 100f;
        paymentEntityMock.status = PaymentStatus.PAID.name();
        paymentEntityMock.currency = "BOB";

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.orderId = UUID.randomUUID().toString();
        paymentEntity.amount = 100f;
        paymentEntity.status = PaymentStatus.PAID.name();
        paymentEntity.currency = "BOB";
        paymentEntity.paymentMethodEntity = paymentMethodEntityMock;

        List<PaymentEntity> listEntityMock = new ArrayList<>();
        listEntityMock.add( paymentEntityMock );

        Mockito.when( paymentRepository.save( Mockito.any( PaymentEntity.class ) ) ).thenReturn( paymentEntityMock );
        Mockito.when( paymentRepository.findAll() ).thenReturn( listEntityMock );
        Mockito.when( paymentMethodRepository.findById( Mockito.any( String.class ) ) ).thenReturn( Optional.of( paymentMethodEntityMock ) );
        try {
            paymentServiceRepository.newPayment( paymentEntity );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // listando
        List<PaymentEntity> listResult = paymentServiceRepository.queryPayment();

        Assertions.assertNotNull( listResult, "listado not null" );
        Assertions.assertEquals( paymentEntityMock, listResult.getFirst() , "objeto de la lista es igual al mock" );

        List<PaymentEntity> listResult2 = paymentServiceRepository.queryPaymentsByOrderId( paymentEntity.orderId );
        Assertions.assertNotNull( listResult2, "listado not null" );
        Assertions.assertEquals( paymentEntityMock, listResult.getFirst() , "objeto de la lista es igual al mock" );

        PaymentMethodEntity paymentMethodEntityNull = null;
        Mockito.when( paymentMethodRepository.findById( Mockito.any( String.class ) ) ).thenReturn( Optional.ofNullable(  paymentMethodEntityNull ) );
        try {
            paymentServiceRepository.newPayment( paymentEntity );
        } catch (Exception e) {
            Assertions.assertEquals( "PaymentMethod not exist",  e.getMessage(), "Excepción correcta" );
        }
    }
}
