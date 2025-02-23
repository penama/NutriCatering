package com.service.catering.application.service;

import com.service.catering.application.model.billerdata.BillerDataDto;
import com.service.catering.application.model.billerdata.Customer;
import com.service.catering.application.utils.BillerDataUtil;
import com.service.catering.domain.model.BillerDataEntity;
import com.service.catering.infraestructure.event.command.CommandEntitysEvent;
import com.service.catering.infraestructure.event.querys.IQueryBillerDataRepository;
import com.service.catering.infraestructure.event.update.IUpdateBillerDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.context.ApplicationEventPublisher;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class BillerDataServiceTest {

    @Mock
    private IQueryBillerDataRepository iQueryBillerDataRepository; // Simula el repositorio

    @Mock
    private ApplicationEventPublisher applicationEventPublisher; // Simula el publicador de eventos

    @Mock
    private IUpdateBillerDataRepository iUpdateBillerDataRepository;

    @InjectMocks
    private BillerDataService billerDataService; // Se inyecta el mock en el servicio

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testNewBillerData(){
        Customer customer = new Customer();
        customer.id = UUID.randomUUID().toString();

        BillerDataEntity billerDataEntityMock = new BillerDataEntity();
        billerDataEntityMock.id = UUID.randomUUID().toString();
        billerDataEntityMock.createdDate = "19/02/2025";
        billerDataEntityMock.nit = "35235";
        billerDataEntityMock.email = "test@test.com";
        billerDataEntityMock.socialReazon = "prueba";
        billerDataEntityMock.customerId = UUID.randomUUID().toString();

        BillerDataDto billerDataDtoMock = new BillerDataDto();
        billerDataDtoMock.id = UUID.randomUUID().toString();
        billerDataDtoMock.createdDate = "19/02/2025";
        billerDataDtoMock.nit = "35235";
        billerDataDtoMock.email = "test@test.com";
        billerDataDtoMock.socialReazon = "prueba";
        billerDataDtoMock.customer = customer;

        BillerDataDto billerDataDto = new BillerDataDto();
        billerDataDto.nit = "35235";
        billerDataDto.email = "test@test.com";
        billerDataDto.socialReazon = "prueba";
        billerDataDto.customer = customer;
        // Simulación de métodos estáticos con mockStatic()
        try (MockedStatic<BillerDataUtil> mockedStatic = mockStatic(BillerDataUtil.class)) {
            mockedStatic.when(() -> BillerDataUtil.BillerDataDtoTobillerDataEntity(billerDataDto)).thenReturn(billerDataEntityMock);
            // Llamar al método a probar
            try {
                billerDataService.newBillerData(billerDataDto);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Verificar que el método estático se llamó correctamente
            mockedStatic.verify(() -> BillerDataUtil.BillerDataDtoTobillerDataEntity(billerDataDto), times(1));

            // Verificar que se creó un evento con los datos correctos
            verify(applicationEventPublisher, times(1)).publishEvent(any(CommandEntitysEvent.class));
        }
    }

    @Test
    public void testGetBillersData(){
        // Datos de prueba
        Customer customer = new Customer();
        customer.id = UUID.randomUUID().toString();

        BillerDataEntity entity1 = new BillerDataEntity();
        entity1.nit = "35235";
        entity1.email = "test@test.com";
        entity1.socialReazon = "prueba";
        entity1.customerId = UUID.randomUUID().toString();

        BillerDataEntity entity2 = new BillerDataEntity();
        entity2.nit = "35235";
        entity2.email = "test@test.com";
        entity2.socialReazon = "prueba";
        entity2.customerId = UUID.randomUUID().toString();

        List<BillerDataEntity> entityList = Arrays.asList(entity1, entity2);

        BillerDataDto dto1Mock = new BillerDataDto();
        dto1Mock.id = UUID.randomUUID().toString();
        dto1Mock.createdDate = "19/02/2025";
        dto1Mock.nit = "35235";
        dto1Mock.email = "test@test.com";
        dto1Mock.socialReazon = "prueba";
        dto1Mock.customer = customer;

        BillerDataDto dto2Mock = new BillerDataDto();
        dto2Mock.id = UUID.randomUUID().toString();
        dto2Mock.createdDate = "19/02/2025";
        dto2Mock.nit = "35235";
        dto2Mock.email = "test@test.com";
        dto2Mock.socialReazon = "prueba";
        dto2Mock.customer = customer;

        List<BillerDataDto> expectedDtoList = Arrays.asList(dto1Mock, dto2Mock);

        // Simulación del repositorio
        when(iQueryBillerDataRepository.queryBillersData()).thenReturn(entityList);

        // Simulación de métodos estáticos con mockStatic()
        try (MockedStatic<BillerDataUtil> mockedStatic = mockStatic(BillerDataUtil.class)) {
            mockedStatic.when(() -> BillerDataUtil.billerDataEntityToBillerDataDto(entity1)).thenReturn(dto1Mock);
            mockedStatic.when(() -> BillerDataUtil.billerDataEntityToBillerDataDto(entity2)).thenReturn(dto2Mock);

            // Llamar al método a probar
            List<BillerDataDto> result = null;
            try {
                result = billerDataService.getBillersData();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Verificaciones
            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(expectedDtoList, result); // Comparar listas

            // Verificar interacciones con los mocks
            verify(iQueryBillerDataRepository, times(1)).queryBillersData();
            mockedStatic.verify(() -> BillerDataUtil.billerDataEntityToBillerDataDto(entity1), times(1));
            mockedStatic.verify(() -> BillerDataUtil.billerDataEntityToBillerDataDto(entity2), times(1));
        }

    }

    @Test
    public void testGetBillerDataByCustomerId(){
        // Datos de prueba
        Customer customer = new Customer();
        customer.id = UUID.randomUUID().toString();

        BillerDataEntity entity1 = new BillerDataEntity();
        entity1.nit = "35235";
        entity1.email = "test@test.com";
        entity1.socialReazon = "prueba";
        entity1.customerId = UUID.randomUUID().toString();

        BillerDataEntity entity2 = new BillerDataEntity();
        entity2.nit = "35235";
        entity2.email = "test@test.com";
        entity2.socialReazon = "prueba";
        entity2.customerId = UUID.randomUUID().toString();

        List<BillerDataEntity> entityList = Arrays.asList(entity1, entity2);

        BillerDataDto dto1Mock = new BillerDataDto();
        dto1Mock.id = UUID.randomUUID().toString();
        dto1Mock.createdDate = "19/02/2025";
        dto1Mock.nit = "35235";
        dto1Mock.email = "test@test.com";
        dto1Mock.socialReazon = "prueba";
        dto1Mock.customer = customer;

        BillerDataDto dto2Mock = new BillerDataDto();
        dto2Mock.id = UUID.randomUUID().toString();
        dto2Mock.createdDate = "19/02/2025";
        dto2Mock.nit = "35235";
        dto2Mock.email = "test@test.com";
        dto2Mock.socialReazon = "prueba";
        dto2Mock.customer = customer;

        List<BillerDataDto> expectedDtoList = Arrays.asList(dto1Mock, dto2Mock);
        // Simulación del repositorio
        when(iQueryBillerDataRepository.queryBillerDataCustomerId( Mockito.any( String.class ) )).thenReturn(entityList);
        // Simulación de métodos estáticos con mockStatic()
        try (MockedStatic<BillerDataUtil> mockedStatic = mockStatic(BillerDataUtil.class)) {
            mockedStatic.when(() -> BillerDataUtil.billerDataEntityToBillerDataDto(entity1)).thenReturn(dto1Mock);
            mockedStatic.when(() -> BillerDataUtil.billerDataEntityToBillerDataDto(entity2)).thenReturn(dto2Mock);
            // Llamar al método a probar
            List<BillerDataDto> result = null;
            try {
                result = billerDataService.getBillerDataByCustomerId( entity1.customerId );
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Verificaciones
            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals(expectedDtoList, result); // Comparar listas
            // Verificar interacciones con los mocks
            verify(iQueryBillerDataRepository, times(1)).queryBillerDataCustomerId( entity1.customerId );
            mockedStatic.verify(() -> BillerDataUtil.billerDataEntityToBillerDataDto(entity1), times(1));
            mockedStatic.verify(() -> BillerDataUtil.billerDataEntityToBillerDataDto(entity2), times(1));
        }
    }

    @Test
    public void testUpdateBillerData(){
        // Datos de prueba
        Customer customer = new Customer();
        customer.id = UUID.randomUUID().toString();

        BillerDataEntity entity1 = new BillerDataEntity();
        entity1.nit = "35235";
        entity1.email = "test@test.com";
        entity1.socialReazon = "prueba";
        entity1.customerId = UUID.randomUUID().toString();

        BillerDataEntity entity2 = new BillerDataEntity();
        entity2.nit = "35235";
        entity2.email = "test@test.com";
        entity2.socialReazon = "prueba";
        entity2.customerId = UUID.randomUUID().toString();

        List<BillerDataEntity> entityList = Arrays.asList(entity1, entity2);

        BillerDataDto dto1Mock = new BillerDataDto();
        dto1Mock.id = UUID.randomUUID().toString();
        dto1Mock.createdDate = "19/02/2025";
        dto1Mock.nit = "35235";
        dto1Mock.email = "test@test.com";
        dto1Mock.socialReazon = "prueba";
        dto1Mock.customer = customer;

        BillerDataDto dto2Mock = new BillerDataDto();
        dto2Mock.id = UUID.randomUUID().toString();
        dto2Mock.createdDate = "19/02/2025";
        dto2Mock.nit = "35235";
        dto2Mock.email = "test@test.com";
        dto2Mock.socialReazon = "prueba";
        dto2Mock.customer = customer;

        List<BillerDataDto> expectedDtoList = Arrays.asList(dto1Mock, dto2Mock);
        // Simulación del repositorio
        when(iQueryBillerDataRepository.queryBillerDataCustomerId( Mockito.any( String.class ) )).thenReturn(entityList);
//        when(iUpdateBillerDataRepository.updateBillersData( Mockito.any( BillerDataEntity.class ) )).thenReturn( entity2 );

        try {
            billerDataService.updateBillerData( "52345354", "testtest", "12345", "email@email.com" );
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<BillerDataEntity> billerDataEntityListNull = null;
        when(iQueryBillerDataRepository.queryBillerDataCustomerId( Mockito.any( String.class ) )).thenReturn( billerDataEntityListNull );

        try {
            billerDataService.updateBillerData( "52345354", "testtest", "12345", "email@email.com" );
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<BillerDataEntity> billerDataEntityListEmpty = new ArrayList<>();
        when(iQueryBillerDataRepository.queryBillerDataCustomerId( Mockito.any( String.class ) )).thenReturn( billerDataEntityListEmpty );

        try {
            billerDataService.updateBillerData( "52345354", "testtest", "12345", "email@email.com" );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
