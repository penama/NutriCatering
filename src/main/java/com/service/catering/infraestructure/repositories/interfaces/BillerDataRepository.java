package com.service.catering.infraestructure.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.service.catering.domain.model.BillerDataEntity;

@Repository
public interface BillerDataRepository extends JpaRepository<BillerDataEntity, String> {

  @Query("SELECT u FROM BillerData u WHERE u.customerId = ?1")
  public List<BillerDataEntity> findByCustomerId(String customerId);
}
