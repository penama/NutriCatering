package com.service.catering.application.util;

import com.service.catering.application.model.contract.CateringPlan;
import com.service.catering.application.model.contract.ContractDto;
import com.service.catering.application.model.contract.ContractStatus;
import com.service.catering.application.model.contract.Customer;
import com.service.catering.application.utils.ContractUtil;
import com.service.catering.domain.model.ContractEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

public class ContractUtilTest {

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testContractDtoToContractEntity(){
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
        contractEntityMock.description = "Test";

        ContractEntity contractEntityResult = ContractUtil.contractDtoToContractEntity( contractDto );

        Assertions.assertNotNull( contractEntityResult );
        Assertions.assertEquals( contractEntityResult.id, contractDto.id );
    }

    @Test
    public void testContractEntityToContractDto(){
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
        contractEntityMock.description = "Test";

        ContractUtil contractUtil = new ContractUtil();
        ContractDto contractDtoResult = ContractUtil.contractEntityToContractDto( contractEntityMock );

        Assertions.assertNotNull( contractDtoResult );
        Assertions.assertEquals( contractDtoResult.id, contractEntityMock.id );
    }

}
