package com.service.catering.infraestructure.repositories.interfaces;

import com.service.catering.domain.model.PaymentMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethodEntity, String> {
}
