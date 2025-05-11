package com.service.catering.infraestructure.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.service.catering.domain.model.InvoiceEntity;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, String> {

  @Query("SELECT u FROM Invoices u WHERE u.paymentId = ?1")
  public List<InvoiceEntity> findByPaymentId(String paymentId);
}
