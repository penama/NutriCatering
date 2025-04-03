package com.service.catering.application.service;

import com.service.catering.application.model.invoice.InvoiceDto;
import com.service.catering.application.model.payment.*;
import com.service.catering.application.service.interfaces.IBillerDataServiceUpdateData;
import com.service.catering.application.service.interfaces.IInvoiceServiceGenerate;
import com.service.catering.application.service.interfaces.IOrderServiceUpdateStatus;
import com.service.catering.application.utils.PaymentUtil;
import com.service.catering.domain.model.PaymentEntity;
import com.service.catering.domain.model.PaymentMethodEntity;
import com.service.catering.infraestructure.event.command.CommandEntitysEvent;
import com.service.catering.infraestructure.event.querys.IQueryPaymentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private IOrderServiceUpdateStatus iOrderServiceUpdateStatus;

    @Mock
    private IBillerDataServiceUpdateData iBillerDataServiceUpdateData;

    @Mock
    private IInvoiceServiceGenerate iInvoiceServiceGenerate;
    @Mock
    private IQueryPaymentRepository iQueryPaymentRepository;
    @Mock
    private ApplicationEventPublisher applicationEventPublisher; // Simula el publicador de eventos

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNewPayment() throws Exception{

        PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
        paymentMethodEntity.setId(UUID.randomUUID().toString());
        paymentMethodEntity.setLabel("Qr");
        paymentMethodEntity.setDescripcion("Qr");
        paymentMethodEntity.setName("Qr");
        paymentMethodEntity.setCreatedDate("22/02/2025");

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.id = UUID.randomUUID().toString();
        paymentEntity.setPaymentMethodEntity(paymentMethodEntity);
        paymentEntity.setAmount(10);
        paymentEntity.setCurrency("BOB");
        paymentEntity.setOrderId(UUID.randomUUID().toString());
        paymentEntity.setStatus(PaymentStatus.PAID.name());
        paymentEntity.setCreatedDate("22/02/2025");

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.id = UUID.randomUUID().toString();
        Price price = new Price();
        price.amount = 10;
        price.currency = "BOB";
        BillerData billerData = new BillerData();
        billerData.nit = "1213";
        billerData.email = "test@test";
        billerData.socialReason = "chagnos";
        BillingInvoice billingInvoice = new BillingInvoice();
        billingInvoice.customerId = UUID.randomUUID().toString();
        billingInvoice.contract = UUID.randomUUID().toString();
        billingInvoice.billerData = billerData;
        Order order = new Order();
        order.id = UUID.randomUUID().toString();

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.id = UUID.randomUUID().toString();
        paymentDto.createdDate = "22/02/2025";
        paymentDto.status = PaymentStatus.PAID.name();
        paymentDto.paymentMethod = paymentMethod;
        paymentDto.price = price;
        paymentDto.billingInvoice = billingInvoice;
        paymentDto.order = order;

        doNothing().when(iOrderServiceUpdateStatus).actualizarStatus( Mockito.any( String.class ) );
        doNothing().when(iBillerDataServiceUpdateData).updateBillerData(Mockito.any( String.class ), Mockito.any( String.class ), Mockito.any( String.class ), Mockito.any( String.class ));
        doNothing().when(iInvoiceServiceGenerate).generateInvoice( Mockito.any( InvoiceDto.class ) );
        // Simulación de métodos estáticos con mockStatic()
        try (MockedStatic<PaymentUtil> mockedStatic = mockStatic(PaymentUtil.class)) {
            mockedStatic.when(() -> PaymentUtil.paymentDtoToPaymentEntity(paymentDto)).thenReturn(paymentEntity);

            // Llamar al método a probar
            try {
                paymentService.newPayment( paymentDto );
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Verificar que el método estático se llamó correctamente
            mockedStatic.verify(() -> PaymentUtil.paymentDtoToPaymentEntity(paymentDto), times(1));
            // Verificar que se creó un evento con los datos correctos
            verify(applicationEventPublisher, times(1)).publishEvent(any(CommandEntitysEvent.class));
            // Verificar que generateOrdersForContract() fue llamado
//            verify(iOrderServiceCreateByContract, times(1)).generateOrdersForContract(contractEntityMock.id);
        }

    }

    @Test
    public void testGetPayments(){

        PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
        paymentMethodEntity.setId(UUID.randomUUID().toString());
        paymentMethodEntity.setLabel("Qr");
        paymentMethodEntity.setDescripcion("Qr");
        paymentMethodEntity.setName("Qr");
        paymentMethodEntity.setCreatedDate("22/02/2025");

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.id = UUID.randomUUID().toString();
        paymentEntity.setPaymentMethodEntity(paymentMethodEntity);
        paymentEntity.setAmount(10);
        paymentEntity.setCurrency("BOB");
        paymentEntity.setOrderId(UUID.randomUUID().toString());
        paymentEntity.setStatus(PaymentStatus.PAID.name());
        paymentEntity.setCreatedDate("22/02/2025");

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.id = UUID.randomUUID().toString();
        Price price = new Price();
        price.amount = 10;
        price.currency = "BOB";
        BillerData billerData = new BillerData();
        billerData.nit = "1213";
        billerData.email = "test@test";
        billerData.socialReason = "chagnos";
        BillingInvoice billingInvoice = new BillingInvoice();
        billingInvoice.customerId = UUID.randomUUID().toString();
        billingInvoice.contract = UUID.randomUUID().toString();
        billingInvoice.billerData = billerData;
        Order order = new Order();
        order.id = UUID.randomUUID().toString();

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.id = UUID.randomUUID().toString();
        paymentDto.createdDate = "22/02/2025";
        paymentDto.status = PaymentStatus.PAID.name();
        paymentDto.paymentMethod = paymentMethod;
        paymentDto.price = price;
        paymentDto.billingInvoice = billingInvoice;
        paymentDto.order = order;

        List<PaymentEntity> listPayment = Arrays.asList( paymentEntity );

        when(iQueryPaymentRepository.queryPayment()).thenReturn( listPayment );
        // Simulación de métodos estáticos con mockStatic()
        try (MockedStatic<PaymentUtil> mockedStatic = mockStatic(PaymentUtil.class)) {
            mockedStatic.when(() -> PaymentUtil.paymentDtoToPaymentEntity(paymentDto)).thenReturn(paymentEntity);

            // Llamar al método a probar
            List<PaymentDto> listPaymentDto = null;
            try {
                listPaymentDto = paymentService.getPayments();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Assertions.assertEquals( 1, listPaymentDto.size() );
            Assertions.assertNotNull( listPaymentDto );

            // Verificar que el método estático se llamó correctamente
//            mockedStatic.verify(() -> PaymentUtil.paymentDtoToPaymentEntity(paymentDto), times(1));
            // Verificar que se creó un evento con los datos correctos
//            verify(applicationEventPublisher, times(1)).publishEvent(any(CommandEntitysEvent.class));
            // Verificar que generateOrdersForContract() fue llamado
//            verify(iOrderServiceCreateByContract, times(1)).generateOrdersForContract(contractEntityMock.id);
        }

    }

    @Test
    public void testGetPaymentsByOrder(){

        PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
        paymentMethodEntity.setId(UUID.randomUUID().toString());
        paymentMethodEntity.setLabel("Qr");
        paymentMethodEntity.setDescripcion("Qr");
        paymentMethodEntity.setName("Qr");
        paymentMethodEntity.setCreatedDate("22/02/2025");

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.id = UUID.randomUUID().toString();
        paymentEntity.setPaymentMethodEntity(paymentMethodEntity);
        paymentEntity.setAmount(10);
        paymentEntity.setCurrency("BOB");
        paymentEntity.setOrderId(UUID.randomUUID().toString());
        paymentEntity.setStatus(PaymentStatus.PAID.name());
        paymentEntity.setCreatedDate("22/02/2025");

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.id = UUID.randomUUID().toString();
        Price price = new Price();
        price.amount = 10;
        price.currency = "BOB";
        BillerData billerData = new BillerData();
        billerData.nit = "1213";
        billerData.email = "test@test";
        billerData.socialReason = "chagnos";
        BillingInvoice billingInvoice = new BillingInvoice();
        billingInvoice.customerId = UUID.randomUUID().toString();
        billingInvoice.contract = UUID.randomUUID().toString();
        billingInvoice.billerData = billerData;
        Order order = new Order();
        order.id = UUID.randomUUID().toString();

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.id = UUID.randomUUID().toString();
        paymentDto.createdDate = "22/02/2025";
        paymentDto.status = PaymentStatus.PAID.name();
        paymentDto.paymentMethod = paymentMethod;
        paymentDto.price = price;
        paymentDto.billingInvoice = billingInvoice;
        paymentDto.order = order;

        List<PaymentEntity> listPayment = Arrays.asList( paymentEntity );

        when(iQueryPaymentRepository.queryPaymentsByOrderId( Mockito.any( String.class ) )).thenReturn( listPayment );
        // Simulación de métodos estáticos con mockStatic()
        try (MockedStatic<PaymentUtil> mockedStatic = mockStatic(PaymentUtil.class)) {
            mockedStatic.when(() -> PaymentUtil.paymentDtoToPaymentEntity(paymentDto)).thenReturn(paymentEntity);

            // Llamar al método a probar
            List<PaymentDto> listPaymentDto = null;
            try {
                listPaymentDto = paymentService.getPaymentsByOrderId( order.id );
            } catch (Exception e) {
                e.printStackTrace();
            }
            Assertions.assertEquals( 1, listPaymentDto.size() );
            Assertions.assertNotNull( listPaymentDto );

            // Verificar que el método estático se llamó correctamente
//            mockedStatic.verify(() -> PaymentUtil.paymentDtoToPaymentEntity(paymentDto), times(1));
            // Verificar que se creó un evento con los datos correctos
//            verify(applicationEventPublisher, times(1)).publishEvent(any(CommandEntitysEvent.class));
            // Verificar que generateOrdersForContract() fue llamado
//            verify(iOrderServiceCreateByContract, times(1)).generateOrdersForContract(contractEntityMock.id);
        }

    }


}
