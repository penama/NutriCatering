package com.service.catering.infraestructure.repositories.service;

import com.service.catering.domain.model.BillerDataEntity;
import com.service.catering.infraestructure.repositories.interfaces.BillerDataRepository;
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

public class BillerDataServiceRepositoryTest {
    @Mock
    private BillerDataRepository billerDataRepository;
    @InjectMocks
    private BillerDataServiceRepository billerDataServiceRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks( this );
    }

    @Test
    public void test(){
        BillerDataEntity billerDataEntityMock = new BillerDataEntity();
        billerDataEntityMock.id = UUID.randomUUID().toString();
        billerDataEntityMock.createdDate = "17/02/2025";
        billerDataEntityMock.customerId = UUID.randomUUID().toString();
        billerDataEntityMock.nit = "2352345";
        billerDataEntityMock.email = "test@test.com";
        billerDataEntityMock.socialReazon = "Juan de los Palores.";

        BillerDataEntity billerDataEntity = new BillerDataEntity();
        billerDataEntity.customerId = UUID.randomUUID().toString();
        billerDataEntity.nit = "2352345";
        billerDataEntity.email = "test@test.com";
        billerDataEntity.socialReazon = "Juan de los Palores.";


        List<BillerDataEntity> listBillerDataMock = new ArrayList<>();
        listBillerDataMock.add( billerDataEntityMock );

        Mockito.when( billerDataRepository.save(Mockito.any( BillerDataEntity.class ) ) ).thenReturn( billerDataEntityMock );
        Mockito.when( billerDataRepository.findAll() ).thenReturn( listBillerDataMock );
        Mockito.when( billerDataRepository.findByCustomerId( Mockito.any( String.class ) ) ).thenReturn( listBillerDataMock );
        Mockito.when( billerDataRepository.findById( Mockito.any( String.class ) ) ).thenReturn(Optional.of( billerDataEntityMock ) );

        // guardar orden
        try {
            billerDataServiceRepository.newBillerData( billerDataEntity );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // listar
        List<BillerDataEntity> listResult = billerDataServiceRepository.queryBillersData();
        Assertions.assertNotNull( listResult, "listado no es nulo" );
        Assertions.assertEquals( billerDataEntityMock, listResult.getFirst(), "Objeto igual" );

        // listar por contrato
        List<BillerDataEntity> listResult2 = billerDataServiceRepository.queryBillerDataCustomerId( billerDataEntityMock.customerId );
        Assertions.assertNotNull( listResult2, "listado no es nulo" );
        Assertions.assertEquals( billerDataEntityMock, listResult2.getFirst(), "Objeto igual" );

        //update.
        billerDataServiceRepository.updateBillersData( billerDataEntity );
    }

}
