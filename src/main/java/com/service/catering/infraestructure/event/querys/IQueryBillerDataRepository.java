package com.service.catering.infraestructure.event.querys;

import com.service.catering.domain.model.BillerDataEntity;

import java.util.List;

public interface IQueryBillerDataRepository {

    public List<BillerDataEntity> queryBillersData();

    public List<BillerDataEntity> queryBillerDataCustomerId(String customerId );

}
