package com.service.catering.infraestructure.event.querys;

import java.util.List;

import com.service.catering.domain.model.BillerDataEntity;

public interface IQueryBillerDataRepository {

  public List<BillerDataEntity> queryBillersData();

  public List<BillerDataEntity> queryBillerDataCustomerId(String customerId);
}
