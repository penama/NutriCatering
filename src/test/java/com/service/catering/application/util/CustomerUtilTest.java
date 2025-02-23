package com.service.catering.application.util;

import com.service.catering.application.model.contract.CateringPlan;
import com.service.catering.application.model.contract.ContractDto;
import com.service.catering.application.model.contract.ContractStatus;
import com.service.catering.application.model.contract.Customer;
import com.service.catering.application.model.customer.CustomerDto;
import com.service.catering.application.model.customer.CustomerStatus;
import com.service.catering.application.utils.ContractUtil;
import com.service.catering.application.utils.CustomerUtil;
import com.service.catering.domain.model.ContractEntity;
import com.service.catering.domain.model.CustomerEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

public class CustomerUtilTest {

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCustomerDtoToCustomerEntity(){
        CustomerDto customerDto = new CustomerDto();
        customerDto.name = "Changos";
        customerDto.id = UUID.randomUUID().toString();
        customerDto.status = CustomerStatus.ACTIVE.name();

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.id = UUID.randomUUID().toString();
        customerEntity.name = "Changos";
        customerEntity.status = CustomerStatus.ACTIVE.name();

        CustomerUtil contractUtil = new CustomerUtil();
        CustomerEntity customerEntityResult = CustomerUtil.customerDtoToCustomerEntity( customerDto );

        Assertions.assertNotNull( customerEntityResult );
        Assertions.assertEquals( customerEntityResult.id, customerDto.id );
    }

    @Test
    public void testCustomerEntityToCustomerDto() throws  Exception{
        CustomerDto customerDto = new CustomerDto();
        customerDto.name = "Changos";
        customerDto.id = UUID.randomUUID().toString();
        customerDto.status = CustomerStatus.ACTIVE.name();

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.id = UUID.randomUUID().toString();
        customerEntity.name = "Changos";
        customerEntity.status = CustomerStatus.ACTIVE.name();
        customerEntity.address = "por ahi";
        customerEntity.birtDate = "20/02/2020";
        customerEntity.phone = "42345234";

        CustomerDto customerDtoRestul = CustomerUtil.customerEntityToCustomerDto( customerEntity );

        Assertions.assertNotNull( customerDtoRestul );
        Assertions.assertEquals( customerDtoRestul.id, customerEntity.id );
    }

}
