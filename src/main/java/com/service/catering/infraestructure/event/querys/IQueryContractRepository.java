package com.service.catering.infraestructure.event.querys;

import java.util.List;

import com.service.catering.domain.model.ContractEntity;

public interface IQueryContractRepository {

  public List<ContractEntity> queryContracts();

  public List<ContractEntity> queryContractsCustomerId(String customerId);

  public ContractEntity queryContractId(String contractId);
}
