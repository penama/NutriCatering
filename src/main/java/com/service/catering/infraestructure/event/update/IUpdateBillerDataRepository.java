package com.service.catering.infraestructure.event.update;

import com.service.catering.domain.model.BillerDataEntity;

import java.util.List;

public interface IUpdateBillerDataRepository {

    public void updateBillersData( BillerDataEntity billerDataEntity );

}
