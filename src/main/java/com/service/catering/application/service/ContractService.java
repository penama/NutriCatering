package com.service.catering.application.service;


import com.service.catering.application.model.contract.ContractDto;
import com.service.catering.application.model.contract.ContractStatus;
import com.service.catering.application.model.order.Contract;
import com.service.catering.application.model.order.OrderDto;
import com.service.catering.application.service.interfaces.IOrderServiceCreateByContract;
import com.service.catering.application.utils.ContractUtil;
import com.service.catering.domain.model.ContractEntity;
import com.service.catering.infraestructure.event.querys.IQueryContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContractService extends BaseService {

    @Autowired
    private IQueryContractRepository iQueryContractRepository;

    @Autowired
    private IOrderServiceCreateByContract iOrderServiceCreateByContract;

    @Transactional( propagation = Propagation.REQUIRED)
    public void newContract(ContractDto  contractDto) throws Exception {
        ContractEntity contractEntity = ContractUtil.contractDtoToContractEntity( contractDto );
        contractEntity.setStatus(ContractStatus.ACTIVE.name());
        commandHandler( this, contractEntity );
        //creando las ordenes.
        iOrderServiceCreateByContract.generateOrdersForContract( contractEntity.getId() );
    }

    public List<ContractDto> getContracts() throws Exception {
        List<ContractEntity> contractEntities = iQueryContractRepository.queryContracts();
        List<ContractDto> contractDtos = new ArrayList<>();
        for (ContractEntity contractEntity : contractEntities) {
            contractDtos.add( ContractUtil.contractEntityToContractDto(contractEntity) );
        }
        return contractDtos;
    }

    public List<ContractDto> getContractsByCustomerId( String customerId ) throws Exception {
        List<ContractEntity> contractEntities = iQueryContractRepository.queryContractsCustomerId( customerId );
        List<ContractDto> contractDtos = new ArrayList<>();
        for (ContractEntity contractEntity : contractEntities) {
            contractDtos.add( ContractUtil.contractEntityToContractDto(contractEntity) );
        }
        return contractDtos;
    }

    public ContractDto getContract( String contractId ) throws Exception {
        ContractEntity contractEntity = iQueryContractRepository.queryContractId( contractId );
        if ( contractEntity == null )
            throw  new Exception( contractId + " Contract Not Found" );
        return  ContractUtil.contractEntityToContractDto(contractEntity);
    }

}
