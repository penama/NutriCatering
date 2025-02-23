package com.service.catering.application.util;

import com.service.catering.application.model.contract.CateringPlan;
import com.service.catering.application.model.contract.ContractDto;
import com.service.catering.application.model.contract.ContractStatus;
import com.service.catering.application.model.contract.Customer;
import com.service.catering.application.model.paymentmethod.PaymentMethodDto;
import com.service.catering.application.model.paymentmethod.PaymentMethodType;
import com.service.catering.application.utils.ContractUtil;
import com.service.catering.application.utils.PaymentMethodUtil;
import com.service.catering.domain.model.ContractEntity;
import com.service.catering.domain.model.PaymentMethodEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

public class PaymentMethodUtilTest {

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPaymentMethodDtoToPaymentMethodEntity(){
        PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
        paymentMethodEntity.id = UUID.randomUUID().toString();
        paymentMethodEntity.name = "Efectivo";
        paymentMethodEntity.createdDate = "22/02/2020";
        paymentMethodEntity.label = "Efectivo";
        paymentMethodEntity.type = PaymentMethodType.EFECTIVO.name();

        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodDto.id = UUID.randomUUID().toString();
        paymentMethodDto.name = "Efectivo";
        paymentMethodDto.createdDate = "22/02/2020";
        paymentMethodDto.label = "Efectivo";
        paymentMethodDto.type = PaymentMethodType.EFECTIVO.name();

        PaymentMethodUtil paymentMethodUtil = new PaymentMethodUtil();
        PaymentMethodEntity paymentMethodEntityResult = PaymentMethodUtil.paymentMethodDtoToPaymentMethodEntity( paymentMethodDto );

        Assertions.assertNotNull( paymentMethodEntityResult );
        Assertions.assertEquals( paymentMethodEntityResult.id, paymentMethodDto.id );
    }

    @Test
    public void testPaymentEntityToPaymentMethodDto(){
        PaymentMethodEntity paymentMethodEntity = new PaymentMethodEntity();
        paymentMethodEntity.id = UUID.randomUUID().toString();
        paymentMethodEntity.name = "Efectivo";
        paymentMethodEntity.createdDate = "22/02/2020";
        paymentMethodEntity.label = "Efectivo";
        paymentMethodEntity.type = PaymentMethodType.EFECTIVO.name();
        paymentMethodEntity.descripcion = "wtet";

        PaymentMethodDto paymentMethodDto = new PaymentMethodDto();
        paymentMethodDto.id = UUID.randomUUID().toString();
        paymentMethodDto.name = "Efectivo";
        paymentMethodDto.createdDate = "22/02/2020";
        paymentMethodDto.label = "Efectivo";
        paymentMethodDto.type = PaymentMethodType.EFECTIVO.name();
        paymentMethodDto.description = "wtet";

        PaymentMethodUtil paymentMethodUtil = new PaymentMethodUtil();
        PaymentMethodDto paymentMethodDtoResult = PaymentMethodUtil.paymentEntityToPaymentMethodDto( paymentMethodEntity );

        Assertions.assertNotNull( paymentMethodDtoResult );
        Assertions.assertEquals( paymentMethodDtoResult.id, paymentMethodEntity.id );
    }

}
