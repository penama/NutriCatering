package com.service.catering.infraestructure.repositories.interfaces;

import com.service.catering.domain.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {

    @Query("SELECT u FROM Ordenes u WHERE u.contractId = ?1")
    public List<OrderEntity> findByContractId(String contractId);



}
