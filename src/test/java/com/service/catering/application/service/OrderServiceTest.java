package com.service.catering.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.context.ApplicationEventPublisher;

import com.service.catering.application.model.contract.ContractStatus;
import com.service.catering.application.model.order.Contract;
import com.service.catering.application.model.order.OrderDto;
import com.service.catering.application.model.order.OrderStatus;
import com.service.catering.application.model.paymentmethod.PaymentMethodStatus;
import com.service.catering.application.utils.OrderUtil;
import com.service.catering.domain.model.ContractEntity;
import com.service.catering.domain.model.OrderEntity;
import com.service.catering.domain.model.PaymentEntity;
import com.service.catering.domain.model.PaymentMethodEntity;
import com.service.catering.infraestructure.event.command.CommandEntitysEvent;
import com.service.catering.infraestructure.event.querys.IQueryContractRepository;
import com.service.catering.infraestructure.event.querys.IQueryOrderRepository;
import com.service.catering.infraestructure.event.querys.IQueryPaymentRepository;

public class OrderServiceTest {

  @Mock
  private ApplicationEventPublisher applicationEventPublisher; // Simula el publicador de eventos

  @Mock private IQueryOrderRepository iQueryOrderRepository;
  @Mock private IQueryPaymentRepository iQueryPaymentRepository;
  @Mock private IQueryContractRepository iQueryContractRepository;

  @InjectMocks private OrderService orderService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testNewOrder() throws Exception {
    Contract contract = new Contract();
    contract.id = UUID.randomUUID().toString();

    OrderDto orderDto = new OrderDto();
    orderDto.id = UUID.randomUUID().toString();
    orderDto.contract = contract;
    orderDto.amount = 100;
    orderDto.status = OrderStatus.PAID.name();
    orderDto.description = "Catering";
    orderDto.creationDate = "22/02/2025";

    OrderEntity orderEntityMock = new OrderEntity();
    orderEntityMock.setId(UUID.randomUUID().toString());
    orderEntityMock.setContractId(UUID.randomUUID().toString());
    orderEntityMock.setAmount(100);
    orderEntityMock.setStatus(OrderStatus.PAID.name());
    orderEntityMock.setDescription("Catering");
    orderEntityMock.setCreatedDate("22/02/2025");

    // Simulación de métodos estáticos con mockStatic()
    try (MockedStatic<OrderUtil> mockedStatic = mockStatic(OrderUtil.class)) {
      mockedStatic
          .when(() -> OrderUtil.orderDtoToOrderEntity(orderDto))
          .thenReturn(orderEntityMock);
      //            doNothing().when(iOrderServiceCreateByContract).generateOrdersForContract(
      // Mockito.any( String.class ));
      // Llamar al método a probar
      try {
        orderService.newOrder(orderDto);
      } catch (Exception e) {
        e.printStackTrace();
      }
      // Verificar que el método estático se llamó correctamente
      mockedStatic.verify(() -> OrderUtil.orderDtoToOrderEntity(orderDto), times(1));
      // Verificar que se creó un evento con los datos correctos
      verify(applicationEventPublisher, times(1)).publishEvent(any(CommandEntitysEvent.class));
      // Verificar que generateOrdersForContract() fue llamado
      //            verify(iOrderServiceCreateByContract,
      // times(1)).generateOrdersForContract(contractEntityMock.id);
    }
  }

  @Test
  public void testGetOrders() {
    Contract contract = new Contract();
    contract.id = UUID.randomUUID().toString();

    OrderDto orderDto = new OrderDto();
    orderDto.id = UUID.randomUUID().toString();
    orderDto.contract = contract;
    orderDto.amount = 100;
    orderDto.status = OrderStatus.PAID.name();
    orderDto.description = "Catering";
    orderDto.creationDate = "22/02/2025";

    OrderEntity orderEntityMock = new OrderEntity();
    orderEntityMock.setId(UUID.randomUUID().toString());
    orderEntityMock.setContractId(contract.id);
    orderEntityMock.setAmount(100);
    orderEntityMock.setStatus(OrderStatus.PAID.name());
    orderEntityMock.setDescription("Catering");
    orderEntityMock.setCreatedDate("22/02/2025");

    List<OrderEntity> expectedDtoList = Arrays.asList(orderEntityMock);

    // Simulación del repositorio
    when(iQueryOrderRepository.queryOrders()).thenReturn(expectedDtoList);
    // Simulación de métodos estáticos con mockStatic()
    try (MockedStatic<OrderUtil> mockedStatic = mockStatic(OrderUtil.class)) {
      mockedStatic
          .when(() -> OrderUtil.orderDtoToOrderEntity(orderDto))
          .thenReturn(orderEntityMock);

      // Llamar al método a probar
      List<OrderDto> result = null;
      try {
        result = orderService.getOrders();
      } catch (Exception e) {
        e.printStackTrace();
      }

      // Verificaciones
      assertNotNull(result);
      assertEquals(1, result.size());
      //            assertEquals(expectedDtoList, result); // Comparar listas

      // Verificar interacciones con los mocks
      verify(iQueryOrderRepository, times(1)).queryOrders();
      //            mockedStatic.verify(() -> OrderUtil.orderDtoToOrderEntity(orderDto), times(1));
      //            mockedStatic.verify(() ->
      // BillerDataUtil.billerDataEntityToBillerDataDto(contractEntityMock), times(1));
    }
  }

  @Test
  public void testGetOrders_Parameter() {
    Contract contract = new Contract();
    contract.id = UUID.randomUUID().toString();

    OrderDto orderDto = new OrderDto();
    orderDto.id = UUID.randomUUID().toString();
    orderDto.contract = contract;
    orderDto.amount = 100;
    orderDto.status = OrderStatus.PAID.name();
    orderDto.description = "Catering";
    orderDto.creationDate = "22/02/2025";

    OrderEntity orderEntityMock = new OrderEntity();
    orderEntityMock.setId(UUID.randomUUID().toString());
    orderEntityMock.setContractId(contract.id);
    orderEntityMock.setAmount(100);
    orderEntityMock.setStatus(OrderStatus.PAID.name());
    orderEntityMock.setDescription("Catering");
    orderEntityMock.setCreatedDate("22/02/2025");

    List<OrderEntity> expectedDtoList = Arrays.asList(orderEntityMock);

    // Simulación del repositorio
    when(iQueryOrderRepository.queryOrders(Mockito.any(String.class))).thenReturn(expectedDtoList);
    // Simulación de métodos estáticos con mockStatic()
    try (MockedStatic<OrderUtil> mockedStatic = mockStatic(OrderUtil.class)) {
      mockedStatic
          .when(() -> OrderUtil.orderDtoToOrderEntity(orderDto))
          .thenReturn(orderEntityMock);

      // Llamar al método a probar
      List<OrderDto> result = null;
      try {
        result = orderService.getOrders(contract.id);
      } catch (Exception e) {
        e.printStackTrace();
      }

      // Verificaciones
      assertNotNull(result);
      assertEquals(1, result.size());
      //            assertEquals(expectedDtoList, result); // Comparar listas

      // Verificar interacciones con los mocks
      verify(iQueryOrderRepository, times(1)).queryOrders(contract.id);
      //            mockedStatic.verify(() -> OrderUtil.orderDtoToOrderEntity(orderDto), times(1));
      //            mockedStatic.verify(() ->
      // BillerDataUtil.billerDataEntityToBillerDataDto(contractEntityMock), times(1));
    }
  }

  @Test
  public void testActualizarStatus_PagoIgualOrder() {
    Contract contract = new Contract();
    contract.id = UUID.randomUUID().toString();

    OrderDto orderDto = new OrderDto();
    orderDto.id = UUID.randomUUID().toString();
    orderDto.contract = contract;
    orderDto.amount = 100;
    orderDto.status = OrderStatus.PAID.name();
    orderDto.description = "Catering";
    orderDto.creationDate = "22/02/2025";

    OrderEntity orderEntityMock = new OrderEntity();
    orderEntityMock.setId(UUID.randomUUID().toString());
    orderEntityMock.setContractId(contract.id);
    orderEntityMock.setAmount(100f);
    orderEntityMock.setStatus(OrderStatus.PAID.name());
    orderEntityMock.setDescription("Catering");
    orderEntityMock.setCreatedDate("22/02/2025");

    PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
    paymentMethodEntity.setId(UUID.randomUUID().toString());
    paymentMethodEntity.setStatus(PaymentMethodStatus.ACTIVE.name());
    paymentMethodEntity.setName("QR");
    paymentMethodEntity.setCreatedDate("22/02/2025");
    paymentMethodEntity.setDescripcion("QR");
    paymentMethodEntity.setLabel("QR");

    PaymentEntity paymentEntity = new PaymentEntity();
    paymentEntity.setPaymentMethodEntity(paymentMethodEntity);
    paymentEntity.setOrderId(UUID.randomUUID().toString());
    paymentEntity.setCurrency("BOB");
    paymentEntity.id = UUID.randomUUID().toString();
    paymentEntity.setAmount(100f);
    paymentEntity.setCreatedDate("22/02/2025");

    List<PaymentEntity> listPaymentEntityMock = Arrays.asList(paymentEntity);
    List<OrderEntity> expectedDtoList = Arrays.asList(orderEntityMock);

    // Simulación del repositorio
    doNothing().when(iQueryOrderRepository).update(Mockito.any(OrderEntity.class));
    when(iQueryPaymentRepository.queryPaymentsByOrderId(Mockito.any(String.class)))
        .thenReturn(listPaymentEntityMock);
    when(iQueryOrderRepository.queryOrder(Mockito.any(String.class))).thenReturn(orderEntityMock);

    try {
      orderService.actualizarStatus(orderDto.id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    //        assertEquals( 1,  );

  }

  @Test
  public void testActualizarStatus_PagoMenorOrder() {
    Contract contract = new Contract();
    contract.id = UUID.randomUUID().toString();

    OrderDto orderDto = new OrderDto();
    orderDto.id = UUID.randomUUID().toString();
    orderDto.contract = contract;
    orderDto.amount = 100f;
    orderDto.status = OrderStatus.PAID.name();
    orderDto.description = "Catering";
    orderDto.creationDate = "22/02/2025";

    OrderEntity orderEntityMock = new OrderEntity();
    orderEntityMock.setId(UUID.randomUUID().toString());
    orderEntityMock.setContractId(contract.id);
    orderEntityMock.setAmount(100f);
    orderEntityMock.setStatus(OrderStatus.PAID.name());
    orderEntityMock.setDescription("Catering");
    orderEntityMock.setCreatedDate("22/02/2025");

    PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
    paymentMethodEntity.setId(UUID.randomUUID().toString());
    paymentMethodEntity.setStatus(PaymentMethodStatus.ACTIVE.name());
    paymentMethodEntity.setName("QR");
    paymentMethodEntity.setCreatedDate("22/02/2025");
    paymentMethodEntity.setDescripcion("QR");
    paymentMethodEntity.setLabel("QR");

    PaymentEntity paymentEntity = new PaymentEntity();
    paymentEntity.setPaymentMethodEntity(paymentMethodEntity);
    paymentEntity.setOrderId(UUID.randomUUID().toString());
    paymentEntity.setCurrency("BOB");
    paymentEntity.id = UUID.randomUUID().toString();
    paymentEntity.setAmount(30f);
    paymentEntity.setCreatedDate("22/02/2025");

    List<PaymentEntity> listPaymentEntityMock = Arrays.asList(paymentEntity);
    List<OrderEntity> expectedDtoList = Arrays.asList(orderEntityMock);

    // Simulación del repositorio
    doNothing().when(iQueryOrderRepository).update(Mockito.any(OrderEntity.class));
    when(iQueryPaymentRepository.queryPaymentsByOrderId(Mockito.any(String.class)))
        .thenReturn(listPaymentEntityMock);
    when(iQueryOrderRepository.queryOrder(Mockito.any(String.class))).thenReturn(orderEntityMock);

    try {
      orderService.actualizarStatus(orderEntityMock.getId());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testGenerateOrdersForContract_Quota1() {
    ContractEntity contractEntityMock = new ContractEntity();
    contractEntityMock.createdDate = "22/02/2025";
    contractEntityMock.status = ContractStatus.ACTIVE.name();
    ;
    contractEntityMock.quotas = 1;
    contractEntityMock.cateringPlanId = UUID.randomUUID().toString();
    contractEntityMock.customerId = UUID.randomUUID().toString();
    contractEntityMock.amount = 60;
    contractEntityMock.id = UUID.randomUUID().toString();

    // Simulación del repositorio
    when(iQueryContractRepository.queryContractId(Mockito.any(String.class)))
        .thenReturn(contractEntityMock);
    try {
      orderService.generateOrdersForContract(contractEntityMock.id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    //        assertEquals( 1,  );

  }

  @Test
  public void testGenerateOrdersForContract_QuotaMayor1() {
    ContractEntity contractEntityMock = new ContractEntity();
    contractEntityMock.createdDate = "22/02/2025";
    contractEntityMock.status = ContractStatus.ACTIVE.name();
    ;
    contractEntityMock.quotas = 6;
    contractEntityMock.cateringPlanId = UUID.randomUUID().toString();
    contractEntityMock.customerId = UUID.randomUUID().toString();
    contractEntityMock.amount = 10;
    contractEntityMock.id = UUID.randomUUID().toString();

    // Simulación del repositorio
    when(iQueryContractRepository.queryContractId(Mockito.any(String.class)))
        .thenReturn(contractEntityMock);

    try {
      orderService.generateOrdersForContract(contractEntityMock.id);
    } catch (Exception e) {
      e.printStackTrace();
    }
    //        assertEquals( 1,  );

  }
}
