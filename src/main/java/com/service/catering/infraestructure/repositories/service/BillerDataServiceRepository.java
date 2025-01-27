package com.service.catering.infraestructure.repositories.service;

import com.service.catering.application.service.interfaces.IBillerDataServiceUpdateData;
import com.service.catering.domain.model.BillerDataEntity;
import com.service.catering.domain.model.ContractEntity;
import com.service.catering.infraestructure.event.querys.IQueryBillerDataRepository;
import com.service.catering.infraestructure.event.querys.IQueryContractRepository;
import com.service.catering.infraestructure.event.update.IUpdateBillerDataRepository;
import com.service.catering.infraestructure.repositories.interfaces.BillerDataRepository;
import com.service.catering.infraestructure.repositories.interfaces.ContractRepository;
import com.service.catering.infraestructure.utils.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BillerDataServiceRepository implements IQueryBillerDataRepository, IUpdateBillerDataRepository {

    @Autowired
    public BillerDataRepository repository;

    public void newBillerData(BillerDataEntity billerDataEntity ) throws Exception{
        billerDataEntity.setId(UUID.randomUUID().toString());
        billerDataEntity.setCreatedDate(DateFormat.toDate());
        repository.save(billerDataEntity);
    }

    @Override
    public List<BillerDataEntity> queryBillersData() {
        return repository.findAll();
    }

    @Override
    public List<BillerDataEntity> queryBillerDataCustomerId(String customerId) {
        return repository.findByCustomerId( customerId );
    }

    @Override
    public void updateBillersData(BillerDataEntity billerDataEntity) {
        System.out.println( "nit "+ billerDataEntity.getNit() + " email: "+ billerDataEntity.getEmail() );
        billerDataEntity.setCreatedDate(DateFormat.toDate());
        repository.save( billerDataEntity );
    }
}
