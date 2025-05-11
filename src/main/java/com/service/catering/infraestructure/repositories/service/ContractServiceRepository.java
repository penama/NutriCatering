package com.service.catering.infraestructure.repositories.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.catering.domain.model.ContractEntity;
import com.service.catering.infraestructure.event.querys.IQueryContractRepository;
import com.service.catering.infraestructure.repositories.interfaces.ContractRepository;
import com.service.catering.infraestructure.utils.DateFormat;

@Service
public class ContractServiceRepository implements IQueryContractRepository {

  @Autowired public ContractRepository repository;

  public void newContract(ContractEntity contractEntity) throws Exception {
    contractEntity.id = UUID.randomUUID().toString();
    contractEntity.createdDate = DateFormat.toDate();
    repository.save(contractEntity);
  }

  @Override
  public List<ContractEntity> queryContracts() {
    return repository.findAll();
  }

  @Override
  public List<ContractEntity> queryContractsCustomerId(String customerId) {
    return repository.findByCustomerId(customerId);
  }

  @Override
  public ContractEntity queryContractId(String contractId) {
    return repository.findById(contractId).get();
  }
}
