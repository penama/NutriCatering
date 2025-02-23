package com.service.catering.application.service;

import com.service.catering.application.model.customer.CustomerDto;
import com.service.catering.application.model.paymentmethod.PaymentMethodDto;
import com.service.catering.application.utils.CustomerUtil;
import com.service.catering.application.utils.PaymentMethodUtil;
import com.service.catering.domain.model.PaymentMethodEntity;
import com.service.catering.infraestructure.event.command.CommandEntitysEvent;
import com.service.catering.infraestructure.event.querys.IQueryPaymentMethodRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PaymentMethodServiceTest {

    @InjectMocks
    private PaymentMethodService paymentMethodService;
    @Mock
    private IQueryPaymentMethodRepository iQueryPaymentMethodRepository;
    @Mock
    private ApplicationEventPublisher applicationEventPublisher; // Simula el publicador de eventos

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNewPaymentMethod() throws  Exception{
        PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
        paymentMethodEntity.id = UUID.randomUUID().toString();
        paymentMethodEntity.name = "QR";

        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodDto.id = UUID.randomUUID().toString();
        paymentMethodDto.name = "QR";

        try (MockedStatic<PaymentMethodUtil> mockedStatic = mockStatic(PaymentMethodUtil.class)) {
            mockedStatic.when(() -> PaymentMethodUtil.paymentMethodDtoToPaymentMethodEntity(paymentMethodDto)).thenReturn(paymentMethodEntity);
            paymentMethodService.newPaymentMethod( paymentMethodDto );
            verify(applicationEventPublisher, times(1)).publishEvent(any(CommandEntitysEvent.class));
        }
    }

    @Test
    public void testGetPaymentMethods() throws  Exception{
        PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
        paymentMethodEntity.id = UUID.randomUUID().toString();
        paymentMethodEntity.name = "QR";

        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodDto.id = UUID.randomUUID().toString();
        paymentMethodDto.name = "QR";

        List<PaymentMethodEntity> paymentMethodEntityList = Arrays.asList( paymentMethodEntity );
        when(iQueryPaymentMethodRepository.queryPaymentMethod()).thenReturn( paymentMethodEntityList );

        try (MockedStatic<PaymentMethodUtil> mockedStatic = mockStatic(PaymentMethodUtil.class)) {
            mockedStatic.when(() -> PaymentMethodUtil.paymentEntityToPaymentMethodDto( paymentMethodEntity )).thenReturn(paymentMethodDto);
            List<PaymentMethodDto> paymentMethodDtoList = paymentMethodService.getPaymentMethods();
            Assertions.assertNotNull( paymentMethodDtoList );
            Assertions.assertEquals( 1, paymentMethodDtoList.size() );
        }
    }

}
