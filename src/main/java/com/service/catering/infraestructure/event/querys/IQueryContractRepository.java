package com.service.catering.infraestructure.event.querys;

import com.service.catering.domain.model.ContractEntity;

import java.util.List;

public interface IQueryContractRepository {

    public List<ContractEntity> queryContracts();

    public List<ContractEntity> queryContractsCustomerId( String customerId );

    public ContractEntity queryContractId( String contractId );

}
