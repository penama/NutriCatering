package com.service.catering.application.util;

import com.service.catering.application.model.billerdata.BillerDataDto;
import com.service.catering.application.model.billerdata.Customer;
import com.service.catering.application.model.contract.CateringPlan;
import com.service.catering.application.model.contract.ContractDto;
import com.service.catering.application.model.contract.ContractStatus;
import com.service.catering.application.utils.BillerDataUtil;
import com.service.catering.application.utils.ContractUtil;
import com.service.catering.domain.model.BillerDataEntity;
import com.service.catering.domain.model.ContractEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class BillerDataUtilTest {

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testBillerDataDtoTobillerDataEntity(){
        // Datos de prueba
        Customer customer = new Customer();
        customer.id = UUID.randomUUID().toString();

        BillerDataEntity billerDataEntity = new BillerDataEntity();
        billerDataEntity.nit = "35235";
        billerDataEntity.email = "test@test.com";
        billerDataEntity.socialReazon = "prueba";
        billerDataEntity.customerId = UUID.randomUUID().toString();


        BillerDataDto billerDataDto = new BillerDataDto();
        billerDataDto.id = UUID.randomUUID().toString();
        billerDataDto.createdDate = "19/02/2025";
        billerDataDto.nit = "35235";
        billerDataDto.email = "test@test.com";
        billerDataDto.socialReazon = "prueba";
        billerDataDto.customer = customer;

        BillerDataEntity billerDataEntityResult = BillerDataUtil.BillerDataDtoTobillerDataEntity( billerDataDto );

        Assertions.assertNotNull( billerDataEntityResult );
        Assertions.assertEquals( billerDataEntityResult.id, billerDataDto.id );
    }

    @Test
    public void testContractEntityToContractDto(){
        // Datos de prueba
        Customer customer = new Customer();
        customer.id = UUID.randomUUID().toString();

        BillerDataEntity billerDataEntity = new BillerDataEntity();
        billerDataEntity.nit = "35235";
        billerDataEntity.email = "test@test.com";
        billerDataEntity.socialReazon = "prueba";
        billerDataEntity.customerId = UUID.randomUUID().toString();


        BillerDataDto billerDataDto = new BillerDataDto();
        billerDataDto.id = UUID.randomUUID().toString();
        billerDataDto.createdDate = "19/02/2025";
        billerDataDto.nit = "35235";
        billerDataDto.email = "test@test.com";
        billerDataDto.socialReazon = "prueba";
        billerDataDto.customer = customer;

        BillerDataUtil billerDataUtil = new BillerDataUtil();
        BillerDataDto  billerDataDtoResult = BillerDataUtil.billerDataEntityToBillerDataDto( billerDataEntity );

        Assertions.assertNotNull( billerDataDtoResult );
        Assertions.assertEquals( billerDataDtoResult.id, billerDataEntity.id );
    }

}
