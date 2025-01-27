package com.service.catering.infraestructure.repositories.interfaces;

import com.service.catering.domain.model.InvoiceDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetailEntity, String> {

    @Query("SELECT u FROM InvoiceDetail u WHERE u.invoiceId = ?1")
    public List<InvoiceDetailEntity> findByInvoiceId(String invoiceId);

}
