package com.service.catering.infraestructure.repositories.interfaces;

import com.service.catering.domain.model.InvoiceEntity;
import com.service.catering.domain.model.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, String> {

    @Query("SELECT u FROM Invoices u WHERE u.paymentId = ?1")
    public List<InvoiceEntity> findByPaymentId(String paymentId);

}
