package com.service.catering.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.context.ApplicationEventPublisher;

import com.service.catering.application.model.contract.CateringPlan;
import com.service.catering.application.model.contract.ContractDto;
import com.service.catering.application.model.contract.ContractStatus;
import com.service.catering.application.model.contract.Customer;
import com.service.catering.application.service.interfaces.IOrderServiceCreateByContract;
import com.service.catering.application.utils.ContractUtil;
import com.service.catering.domain.model.ContractEntity;
import com.service.catering.infraestructure.event.command.CommandEntitysEvent;
import com.service.catering.infraestructure.event.querys.IQueryContractRepository;

// @ExtendWith(MockitoExtension.class)
public class ContractServiceTest {

  @Mock
  private ApplicationEventPublisher applicationEventPublisher; // Simula el publicador de eventos

  @Mock private IQueryContractRepository iQueryContractRepository;

  @Mock private IOrderServiceCreateByContract iOrderServiceCreateByContract;

  @InjectMocks private ContractService contractService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testNewBillerData() throws Exception {
    Customer customer = new Customer();
    customer.id = UUID.randomUUID().toString();

    CateringPlan cateringPlan = new CateringPlan();
    cateringPlan.id = UUID.randomUUID().toString();

    ContractDto contractDto = new ContractDto();
    contractDto.id = UUID.randomUUID().toString();
    contractDto.customer = customer;
    contractDto.status = ContractStatus.ACTIVE.name();
    contractDto.cateringPlan = cateringPlan;
    contractDto.creationDate = "21/02/2025";
    contractDto.quotas = 4;
    contractDto.totalAmount = 100;
    contractDto.description = "Test";

    ContractEntity contractEntityMock = new ContractEntity();
    contractEntityMock.id = UUID.randomUUID().toString();
    contractEntityMock.customerId = UUID.randomUUID().toString();
    contractEntityMock.status = ContractStatus.ACTIVE.name();
    contractEntityMock.cateringPlanId = UUID.randomUUID().toString();
    contractEntityMock.createdDate = "21/02/2025";
    contractEntityMock.quotas = 4;
    //        contractEntityMock.totalAmount = 100;
    contractEntityMock.description = "Test";
    // Simulación de métodos estáticos con mockStatic()
    try (MockedStatic<ContractUtil> mockedStatic = mockStatic(ContractUtil.class)) {
      mockedStatic
          .when(() -> ContractUtil.contractDtoToContractEntity(contractDto))
          .thenReturn(contractEntityMock);
      doNothing()
          .when(iOrderServiceCreateByContract)
          .generateOrdersForContract(Mockito.any(String.class));
      // Llamar al método a probar
      try {
        contractService.newContract(contractDto);
      } catch (Exception e) {
        e.printStackTrace();
      }
      // Verificar que el método estático se llamó correctamente
      mockedStatic.verify(() -> ContractUtil.contractDtoToContractEntity(contractDto), times(1));
      // Verificar que se creó un evento con los datos correctos
      verify(applicationEventPublisher, times(1)).publishEvent(any(CommandEntitysEvent.class));
      // Verificar que generateOrdersForContract() fue llamado
      verify(iOrderServiceCreateByContract, times(1))
          .generateOrdersForContract(contractEntityMock.id);
    }

    //        //
    //        try (MockedStatic<ContractUtil> mockedStatic = mockStatic(ContractUtil.class)) {
    //            mockedStatic.when(() ->
    // ContractUtil.contractDtoToContractEntity(contractDto)).thenReturn(contractEntityMock);
    //            // Simular que generateOrdersForContract() lanza una excepción
    //            doThrow(new RuntimeException("Error al generar
    // órdenes")).when(iOrderServiceCreateByContract).generateOrdersForContract(contractEntityMock.id);
    //            // Llamar al método a probar
    //            try {
    //                contractService.newContract(contractDto);
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //            // Verificar que el método estático se llamó correctamente
    //            mockedStatic.verify(() -> ContractUtil.contractDtoToContractEntity(contractDto),
    // times(1));
    //            // Verificar que se creó un evento con los datos correctos
    //            verify(applicationEventPublisher,
    // times(1)).publishEvent(any(CommandEntitysEvent.class));
    //            // Verificar que generateOrdersForContract() fue llamado
    //            verify(iOrderServiceCreateByContract,
    // times(1)).generateOrdersForContract(contractEntityMock.id);
    //        }

  }

  @Test
  public void testGetContracts() {
    Customer customer = new Customer();
    customer.id = UUID.randomUUID().toString();

    CateringPlan cateringPlan = new CateringPlan();
    cateringPlan.id = UUID.randomUUID().toString();

    ContractDto contractDto = new ContractDto();
    contractDto.id = UUID.randomUUID().toString();
    contractDto.customer = customer;
    contractDto.status = ContractStatus.ACTIVE.name();
    contractDto.cateringPlan = cateringPlan;
    contractDto.creationDate = "21/02/2025";
    contractDto.quotas = 4;
    contractDto.totalAmount = 100;
    contractDto.description = "Test";

    ContractEntity contractEntityMock = new ContractEntity();
    contractEntityMock.id = UUID.randomUUID().toString();
    contractEntityMock.customerId = UUID.randomUUID().toString();
    contractEntityMock.status = ContractStatus.ACTIVE.name();
    contractEntityMock.cateringPlanId = UUID.randomUUID().toString();
    contractEntityMock.createdDate = "21/02/2025";
    contractEntityMock.quotas = 4;
    //        contractEntityMock.totalAmount = 100;
    contractEntityMock.description = "Test";

    List<ContractEntity> expectedDtoList = Arrays.asList(contractEntityMock);

    // Simulación del repositorio
    when(iQueryContractRepository.queryContracts()).thenReturn(expectedDtoList);
    // Simulación de métodos estáticos con mockStatic()
    try (MockedStatic<ContractUtil> mockedStatic = mockStatic(ContractUtil.class)) {
      mockedStatic
          .when(() -> ContractUtil.contractEntityToContractDto(contractEntityMock))
          .thenReturn(contractDto);

      // Llamar al método a probar
      List<ContractDto> result = null;
      try {
        result = contractService.getContracts();
      } catch (Exception e) {
        e.printStackTrace();
      }

      // Verificaciones
      assertNotNull(result);
      assertEquals(1, result.size());
      //            assertEquals(expectedDtoList, result); // Comparar listas

      // Verificar interacciones con los mocks
      verify(iQueryContractRepository, times(1)).queryContracts();
      mockedStatic.verify(
          () -> ContractUtil.contractEntityToContractDto(contractEntityMock), times(1));
      //            mockedStatic.verify(() ->
      // BillerDataUtil.billerDataEntityToBillerDataDto(contractEntityMock), times(1));
    }
  }

  @Test
  public void getContractsByCustomerId() {
    Customer customer = new Customer();
    customer.id = UUID.randomUUID().toString();

    CateringPlan cateringPlan = new CateringPlan();
    cateringPlan.id = UUID.randomUUID().toString();

    ContractDto contractDto = new ContractDto();
    contractDto.id = UUID.randomUUID().toString();
    contractDto.customer = customer;
    contractDto.status = ContractStatus.ACTIVE.name();
    contractDto.cateringPlan = cateringPlan;
    contractDto.creationDate = "21/02/2025";
    contractDto.quotas = 4;
    contractDto.totalAmount = 100;
    contractDto.description = "Test";

    ContractEntity contractEntityMock = new ContractEntity();
    contractEntityMock.id = UUID.randomUUID().toString();
    contractEntityMock.customerId = UUID.randomUUID().toString();
    contractEntityMock.status = ContractStatus.ACTIVE.name();
    contractEntityMock.cateringPlanId = UUID.randomUUID().toString();
    contractEntityMock.createdDate = "21/02/2025";
    contractEntityMock.quotas = 4;
    //        contractEntityMock.totalAmount = 100;
    contractEntityMock.description = "Test";

    List<ContractEntity> expectedDtoList = Arrays.asList(contractEntityMock);

    // Simulación del repositorio
    when(iQueryContractRepository.queryContractsCustomerId(Mockito.any(String.class)))
        .thenReturn(expectedDtoList);
    // Simulación de métodos estáticos con mockStatic()
    try (MockedStatic<ContractUtil> mockedStatic = mockStatic(ContractUtil.class)) {
      mockedStatic
          .when(() -> ContractUtil.contractEntityToContractDto(contractEntityMock))
          .thenReturn(contractDto);

      // Llamar al método a probar
      List<ContractDto> result = null;
      try {
        result = contractService.getContractsByCustomerId(customer.id);
      } catch (Exception e) {
        e.printStackTrace();
      }
      // Verificaciones
      assertNotNull(result);
      assertEquals(1, result.size());
      //            assertEquals(expectedDtoList, result); // Comparar listas

      // Verificar interacciones con los mocks
      verify(iQueryContractRepository, times(1)).queryContractsCustomerId(customer.id);
      mockedStatic.verify(
          () -> ContractUtil.contractEntityToContractDto(contractEntityMock), times(1));
      //            mockedStatic.verify(() ->
      // BillerDataUtil.billerDataEntityToBillerDataDto(contractEntityMock), times(1));
    }
  }

  @Test
  public void testGetContract() throws Exception {
    Customer customer = new Customer();
    customer.id = UUID.randomUUID().toString();

    CateringPlan cateringPlan = new CateringPlan();
    cateringPlan.id = UUID.randomUUID().toString();

    ContractDto contractDto = new ContractDto();
    contractDto.id = UUID.randomUUID().toString();
    contractDto.customer = customer;
    contractDto.status = ContractStatus.ACTIVE.name();
    contractDto.cateringPlan = cateringPlan;
    contractDto.creationDate = "21/02/2025";
    contractDto.quotas = 4;
    contractDto.totalAmount = 100;
    contractDto.description = "Test";

    ContractEntity contractEntityMock = new ContractEntity();
    contractEntityMock.id = UUID.randomUUID().toString();
    contractEntityMock.customerId = UUID.randomUUID().toString();
    contractEntityMock.status = ContractStatus.ACTIVE.name();
    contractEntityMock.cateringPlanId = UUID.randomUUID().toString();
    contractEntityMock.createdDate = "21/02/2025";
    contractEntityMock.quotas = 4;
    //        contractEntityMock.totalAmount = 100;
    contractEntityMock.description = "Test";

    List<ContractEntity> expectedDtoList = Arrays.asList(contractEntityMock);

    // Simulación del repositorio
    when(iQueryContractRepository.queryContractId(Mockito.any(String.class)))
        .thenReturn(contractEntityMock);
    // Simulación de métodos estáticos con mockStatic()
    try (MockedStatic<ContractUtil> mockedStatic = mockStatic(ContractUtil.class)) {
      mockedStatic
          .when(() -> ContractUtil.contractEntityToContractDto(contractEntityMock))
          .thenReturn(contractDto);

      // Llamar al método a probar
      ContractDto result = null;
      try {
        result = contractService.getContract(contractEntityMock.id);
      } catch (Exception e) {
        e.printStackTrace();
      }
      // Verificaciones
      assertNotNull(result);
      //            assertEquals(1, result.size());
      //            assertEquals(expectedDtoList, result); // Comparar listas
      // Verificar interacciones con los mocks
      verify(iQueryContractRepository, times(1)).queryContractId(contractEntityMock.id);
      mockedStatic.verify(
          () -> ContractUtil.contractEntityToContractDto(contractEntityMock), times(1));
      //            mockedStatic.verify(() ->
      // BillerDataUtil.billerDataEntityToBillerDataDto(contractEntityMock), times(1));
    }

    //        // Simulación del repositorio
    //        ContractEntity contractEntityNUll = null;
    ////
    // when(iQueryContractRepository.queryContractId(Mockito.any(String.class))).thenReturn(
    // contractEntityNUll );
    //        // Simulación de métodos estáticos con mockStatic()
    //        try (MockedStatic<ContractUtil> mockedStatic = mockStatic(ContractUtil.class)) {
    //            mockedStatic.when(() ->
    // ContractUtil.contractEntityToContractDto(contractEntityMock)).thenReturn(contractDto);
    //
    //
    // when(iQueryContractRepository.queryContractId(Mockito.any(String.class))).thenReturn( null );
    //
    //            // Simular que generateOrdersForContract() lanza una excepción
    ////            doThrow(new RuntimeException("Error al generar
    // órdenes")).when(iQueryContractRepository).queryContractId( contractEntityMock.id );
    //
    //            //ar que la excepción se lanza correctamente
    ////            assertThrows(Exception.class, () -> contractService.newContract(contractDto));
    //            ContractDto result = null;
    //            try {
    //                result = contractService.getContract(contractEntityMock.id );
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    ////            // Verificar que el evento se publicó antes del error
    ////            verify(applicationEventPublisher,
    // times(1)).publishEvent(any(CommandEntitysEvent.class));
    //
    //            // Verificar que generateOrdersForContract() fue llamado antes de fallar
    //            verify(iQueryContractRepository, times(1)).queryContractId( contractEntityMock.id
    // );
    //        }

  }

  @Test
  public void testGetContractException() {
    Customer customer = new Customer();
    customer.id = UUID.randomUUID().toString();

    CateringPlan cateringPlan = new CateringPlan();
    cateringPlan.id = UUID.randomUUID().toString();

    ContractDto contractDto = new ContractDto();
    contractDto.id = UUID.randomUUID().toString();
    contractDto.customer = customer;
    contractDto.status = ContractStatus.ACTIVE.name();
    contractDto.cateringPlan = cateringPlan;
    contractDto.creationDate = "21/02/2025";
    contractDto.quotas = 4;
    contractDto.totalAmount = 100;
    contractDto.description = "Test";

    ContractEntity contractEntityMock = new ContractEntity();
    contractEntityMock.id = UUID.randomUUID().toString();
    contractEntityMock.customerId = UUID.randomUUID().toString();
    contractEntityMock.status = ContractStatus.ACTIVE.name();
    contractEntityMock.cateringPlanId = UUID.randomUUID().toString();
    contractEntityMock.createdDate = "21/02/2025";
    contractEntityMock.quotas = 4;
    //        contractEntityMock.totalAmount = 100;
    contractEntityMock.description = "Test";

    // Simulación del repositorio
    ContractEntity contractEntityNUll = null;
    //        when(iQueryContractRepository.queryContractId(Mockito.any(String.class))).thenReturn(
    // contractEntityNUll );
    // Simulación de métodos estáticos con mockStatic()
    try (MockedStatic<ContractUtil> mockedStatic = mockStatic(ContractUtil.class)) {
      mockedStatic
          .when(() -> ContractUtil.contractEntityToContractDto(contractEntityMock))
          .thenReturn(contractDto);
      when(iQueryContractRepository.queryContractId(Mockito.any(String.class))).thenReturn(null);

      ContractDto result = null;
      try {
        result = contractService.getContract(contractEntityMock.id);
      } catch (Exception e) {
        e.printStackTrace();
      }
      //            // Verificar que el evento se publicó antes del error
      //            verify(applicationEventPublisher,
      // times(1)).publishEvent(any(CommandEntitysEvent.class));

      // Verificar que generateOrdersForContract() fue llamado antes de fallar
      verify(iQueryContractRepository, times(1)).queryContractId(contractEntityMock.id);
    }
  }
}
