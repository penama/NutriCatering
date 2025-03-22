package com.service.catering.infraestructure.repositories.service;

import com.service.catering.application.model.paymentmethod.PaymentMethodStatus;
import com.service.catering.domain.model.PaymentMethodEntity;
import com.service.catering.infraestructure.repositories.interfaces.PaymentMethodRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaymentMethodServiceRepositoryTest {

    @Mock
    private PaymentMethodRepository paymentMethodRepository;
    @InjectMocks
    private PaymentMethodServiceRepository paymentMethodServiceRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks( this );
    }

    @Test
    public void test(){
        String name = "QR";

        PaymentMethodEntity paymentMethodEntityMock = new PaymentMethodEntity();
        paymentMethodEntityMock.setId(UUID.randomUUID().toString());
        paymentMethodEntityMock.setName(name);
        paymentMethodEntityMock.setStatus(PaymentMethodStatus.ACTIVE.name());
        paymentMethodEntityMock.setCreatedDate("16/02/2025");

        PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
        paymentMethodEntity.setName(name);

        List<PaymentMethodEntity> listEntityMock = new ArrayList<>();
        listEntityMock.add( paymentMethodEntityMock );

        Mockito.when( paymentMethodRepository.save( Mockito.any( PaymentMethodEntity.class ) ) ).thenReturn( paymentMethodEntityMock );
        Mockito.when( paymentMethodRepository.findAll() ).thenReturn( listEntityMock );

        try {
            paymentMethodServiceRepository.newPaymentMethod( paymentMethodEntity );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // listando
        List<PaymentMethodEntity> listResult = paymentMethodServiceRepository.queryPaymentMethod();

        Assertions.assertNotNull( listResult, "listado not null" );
        Assertions.assertEquals( paymentMethodEntityMock, listResult.getFirst() , "objeto de la lista es igual al mock" );
    }


}
