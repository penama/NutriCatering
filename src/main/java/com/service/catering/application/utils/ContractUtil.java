package com.service.catering.application.utils;


import com.service.catering.application.model.contract.CateringPlan;
import com.service.catering.application.model.contract.ContractDto;
import com.service.catering.application.model.contract.Customer;
import com.service.catering.domain.model.ContractEntity;

public class ContractUtil {

    public static ContractEntity contractDtoToContractEntity(ContractDto contractDto){
        ContractEntity contractEntity = new ContractEntity();
        contractEntity.setId( contractDto.getId() );
        contractEntity.setDescription(contractDto.getDescription());
        contractEntity.setCateringPlanId( contractDto.getCateringPlan().getId()  );
        contractEntity.setCustomerId( contractDto.getCustomer().getId() );
        contractEntity.setAmount( contractDto.getTotalAmount() );
        contractEntity.setQuotas( contractDto.getQuotas() );
        contractEntity.setStatus( contractDto.getStatus() );
        contractEntity.setCreatedDate( contractDto.getCreationDate() );
        return contractEntity;
    }

    public static ContractDto contractEntityToContractDto(ContractEntity contractEntity ){
        ContractDto contractDto = new ContractDto();
        contractDto.setId( contractEntity.getId() );
        contractDto.setDescription(contractEntity.getDescription());
        CateringPlan cateringPlan = new CateringPlan();
        cateringPlan.setId( contractEntity.getCateringPlanId() );
        contractDto.setCateringPlan( cateringPlan );
        Customer customer = new Customer();
        customer.setId( contractEntity.getCustomerId() );
        contractDto.setCustomer( customer );
        contractDto.setTotalAmount( contractEntity.getAmount() );
        contractDto.setQuotas( contractEntity.getQuotas() );
        contractDto.setStatus( contractEntity.getStatus() );
        contractDto.setCreationDate( contractEntity.getCreatedDate() );
        return contractDto;
    }

}
