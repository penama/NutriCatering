package com.service.catering.infraestructure.repositories.interfaces;

import com.service.catering.domain.model.ContractEntity;
import com.service.catering.domain.model.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {

    @Query("SELECT u FROM Payment u WHERE u.orderId = ?1")
    public List<PaymentEntity> findByOrderId(String orderId);

}
