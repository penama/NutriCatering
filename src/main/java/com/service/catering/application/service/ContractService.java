package com.service.catering.application.service;

import java.util.ArrayList;
import java.util.List;

import com.service.catering.application.service.events.ContractCreatedProducerService;
import com.service.catering.infraestructure.repositories.service.ContractServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.service.catering.application.model.contract.ContractDto;
import com.service.catering.application.model.contract.ContractStatus;
import com.service.catering.application.service.interfaces.IOrderServiceCreateByContract;
import com.service.catering.application.utils.ContractUtil;
import com.service.catering.domain.model.ContractEntity;
import com.service.catering.infraestructure.event.querys.IQueryContractRepository;

@Service
public class ContractService extends BaseService {

  @Autowired private IQueryContractRepository iQueryContractRepository;

  @Autowired private IOrderServiceCreateByContract iOrderServiceCreateByContract;

  @Autowired private ContractCreatedProducerService contractCreatedProducerService;

  @Transactional(propagation = Propagation.REQUIRED)
  public void newContract(ContractDto contractDto) throws Exception {
    // probandos.
    ContractEntity contractEntity = ContractUtil.contractDtoToContractEntity(contractDto);
    contractEntity.setStatus(ContractStatus.ACTIVE.name());
    commandHandler(this, contractEntity);
    // creando las ordenes.
    iOrderServiceCreateByContract.generateOrdersForContract(contractEntity.getId());
	// enviar evento de contrato creado.
	  contractCreatedProducerService.contractCreatedProducer( contractEntity );
  }

  public List<ContractDto> getContracts() throws Exception {
    List<ContractEntity> contractEntities = iQueryContractRepository.queryContracts();
    List<ContractDto> contractDtos = new ArrayList<>();
    for (ContractEntity contractEntity : contractEntities) {
      contractDtos.add(ContractUtil.contractEntityToContractDto(contractEntity));
    }
    return contractDtos;
  }

  public List<ContractDto> getContractsByCustomerId(String customerId) throws Exception {
    List<ContractEntity> contractEntities =
        iQueryContractRepository.queryContractsCustomerId(customerId);
    List<ContractDto> contractDtos = new ArrayList<>();
    for (ContractEntity contractEntity : contractEntities) {
      contractDtos.add(ContractUtil.contractEntityToContractDto(contractEntity));
    }
    return contractDtos;
  }

  public ContractDto getContract(String contractId) throws Exception {
    ContractEntity contractEntity = iQueryContractRepository.queryContractId(contractId);
    if (contractEntity == null) {
      throw new Exception(contractId + " Contract Not Found..");
    }
    return ContractUtil.contractEntityToContractDto(contractEntity);
  }

//	public void newContractEventNutritionalPlan(ContractDto contractDto) throws Exception {
//		// probandos.
//		ContractEntity contractEntity = ContractUtil.contractDtoToContractEntity(contractDto);
//		contractEntity.setStatus(ContractStatus.ACTIVE.name());
//		contractServiceRepository.newContract( contractEntity );
//		// creando las ordenes.
//		iOrderServiceCreateByContract.generateOrdersForContract(contractEntity.getId());
//		// enviar evento de contrato creado.
//	}

}
