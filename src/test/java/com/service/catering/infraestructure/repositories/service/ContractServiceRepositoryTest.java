package com.service.catering.infraestructure.repositories.service;

import com.service.catering.application.model.contract.ContractStatus;
import com.service.catering.domain.model.ContractEntity;
import com.service.catering.infraestructure.repositories.interfaces.ContractRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContractServiceRepositoryTest {

    @Mock
    private ContractRepository contractRepository;
    @InjectMocks
    private ContractServiceRepository contractServiceRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks( this );
    }

    @Test
    public void test(){
        ContractEntity contractEntityMock = new ContractEntity();
        contractEntityMock.customerId = UUID.randomUUID().toString();
        contractEntityMock.id = UUID.randomUUID().toString();
        contractEntityMock.createdDate = "16/02/2025";
        contractEntityMock.status = ContractStatus.ACTIVE.name();
        contractEntityMock.amount = 10;
        contractEntityMock.cateringPlanId = UUID.randomUUID().toString();
        contractEntityMock.description = "descripción del contrato.";
        contractEntityMock.quotas = 2;

        ContractEntity contractEntity = new ContractEntity();
        contractEntity.createdDate = "16/02/2025";
        contractEntity.status = ContractStatus.ACTIVE.name();
        contractEntity.amount = 10;
        contractEntity.description = "descripción del contrato.";
        contractEntity.quotas = 2;

        List<ContractEntity> listCustomer = new ArrayList<>();
        listCustomer.add( contractEntityMock );

        Mockito.when( contractRepository.save( Mockito.any( ContractEntity.class ) ) ).thenReturn( contractEntityMock );
        Mockito.when( contractRepository.findAll() ).thenReturn( listCustomer );
        Mockito.when( contractRepository.findById( Mockito.any( String.class ) ) ).thenReturn( Optional.of( contractEntityMock ) );
        Mockito.when( contractRepository.findByCustomerId( Mockito.any( String.class ) ) ).thenReturn( listCustomer );
        try {
            contractServiceRepository.newContract( contractEntity );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // listado de clientes registrados.
        List<ContractEntity> listEntityResult = contractServiceRepository.queryContracts();

        assertNotNull( listEntityResult, "Listado no es nulo" );
        assertEquals( contractEntityMock, listEntityResult.getFirst() , "Datos del Mock son los mismos");

        // busqueda por id.
        ContractEntity contractEntity1Result = contractServiceRepository.queryContractId( contractEntityMock.id );

        assertNotNull( contractEntity1Result, "Contract no es nulo..." );
        assertEquals( contractEntityMock, contractEntity1Result, "objetos iguales..." );

        // busqueda por Customer id.
        List<ContractEntity> listContractResult = contractServiceRepository.queryContractsCustomerId( contractEntityMock.customerId );

        assertNotNull( listContractResult, "Listado no es nulo..." );
        assertEquals( contractEntityMock, listContractResult.getFirst(), "objetos iguales..." );

    }

}
