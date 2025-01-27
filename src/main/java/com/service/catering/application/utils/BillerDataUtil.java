package com.service.catering.application.utils;


import com.service.catering.application.model.billerdata.BillerDataDto;
import com.service.catering.application.model.billerdata.Customer;
import com.service.catering.domain.model.BillerDataEntity;

public class BillerDataUtil {

    public static BillerDataEntity BillerDataDtoTobillerDataEntity(BillerDataDto billerDataDto){
        BillerDataEntity billerDataEntity = new BillerDataEntity();
        billerDataEntity.setId( billerDataDto.getId() );
        billerDataEntity.setSocialReazon(billerDataDto.getSocialReazon());
        billerDataEntity.setNit( billerDataDto.getNit()  );
        billerDataEntity.setCustomerId( billerDataDto.getCustomer().getId() );
        billerDataEntity.setEmail( billerDataDto.getEmail() );
        billerDataEntity.setCreatedDate( billerDataDto.getCreatedDate() );
        return billerDataEntity;
    }

    public static BillerDataDto billerDataEntityToBillerDataDto(BillerDataEntity billerDataEntity ){
        BillerDataDto billerDataDto = new BillerDataDto();
        billerDataDto.setId( billerDataEntity.getId() );
        billerDataDto.setSocialReazon(billerDataEntity.getSocialReazon());
        billerDataDto.setNit( billerDataEntity.getNit() );
        billerDataDto.setEmail(  billerDataEntity.getEmail() );
        Customer customer = new Customer();
        customer.setId( billerDataEntity.getCustomerId() );
        billerDataDto.setCustomer( customer );
        billerDataDto.setCreatedDate( billerDataEntity.getCreatedDate() );
        return billerDataDto;
    }

}
