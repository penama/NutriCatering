package com.service.catering.application.service;

import com.service.catering.application.model.billerdata.BillerDataDto;
import com.service.catering.application.service.interfaces.IBillerDataServiceUpdateData;
import com.service.catering.application.utils.BillerDataUtil;
import com.service.catering.domain.model.BillerDataEntity;
import com.service.catering.infraestructure.event.querys.IQueryBillerDataRepository;
import com.service.catering.infraestructure.event.update.IUpdateBillerDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BillerDataService extends BaseService implements  IBillerDataServiceUpdateData {

    @Autowired
    private IQueryBillerDataRepository iQueryBillerDataRepository;

    @Autowired
    private IUpdateBillerDataRepository iUpdateBillerDataRepository;

    public void newBillerData(BillerDataDto billerDataDto) throws Exception {
        BillerDataEntity billerDataEntity = BillerDataUtil.BillerDataDtoTobillerDataEntity( billerDataDto );
        commandHandler( this, billerDataEntity );
    }

    public List<BillerDataDto> getBillersData() throws Exception {
        List<BillerDataEntity> billerDataEntities = iQueryBillerDataRepository.queryBillersData();
        List<BillerDataDto> billerDataDtos = new ArrayList<>();
        for (BillerDataEntity billerDataEntity : billerDataEntities) {
            billerDataDtos.add( BillerDataUtil.billerDataEntityToBillerDataDto(billerDataEntity) );
        }
        return billerDataDtos;
    }

    public List<BillerDataDto> getBillerDataByCustomerId( String customerId ) throws Exception {
        List<BillerDataEntity> billerDataEntities = iQueryBillerDataRepository.queryBillerDataCustomerId( customerId );
        List<BillerDataDto> billerDataDtos = new ArrayList<>();
        for (BillerDataEntity billerDataEntity : billerDataEntities) {
            billerDataDtos.add( BillerDataUtil.billerDataEntityToBillerDataDto( billerDataEntity) );
        }
        return billerDataDtos;
    }

    @Override
    @Transactional( propagation = Propagation.MANDATORY)
    public void updateBillerData(String customerId, String socialRazon, String nit, String email) throws Exception {
        List<BillerDataEntity> billerDataEntities = iQueryBillerDataRepository.queryBillerDataCustomerId( customerId );
        BillerDataEntity billerDataEntity = null;
        if ( billerDataEntities == null || billerDataEntities.isEmpty() ){
            billerDataEntity = new BillerDataEntity();
            billerDataEntity.setId( UUID.randomUUID().toString() );
            billerDataEntity.setCustomerId( customerId );
        } else {
            billerDataEntity = billerDataEntities.getFirst();
        }
        System.out.println( "nit "+ nit + " email: "+ email );
        billerDataEntity.setSocialReazon( socialRazon );
        billerDataEntity.setNit( nit );
        billerDataEntity.setEmail( email );
        iUpdateBillerDataRepository.updateBillersData( billerDataEntity );
    }
}
