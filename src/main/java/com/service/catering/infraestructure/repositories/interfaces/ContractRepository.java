package com.service.catering.infraestructure.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.service.catering.domain.model.ContractEntity;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, String> {

  @Query("SELECT u FROM Contract u WHERE u.customerId = ?1")
  public List<ContractEntity> findByCustomerId(String customerId);
}
