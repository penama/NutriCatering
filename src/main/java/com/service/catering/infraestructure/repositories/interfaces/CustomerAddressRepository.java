package com.service.catering.infraestructure.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.service.catering.domain.model.CustomerAddressEntity;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddressEntity, String> {

  @Query("SELECT u FROM CustomerAddress u WHERE u.customerId = ?1")
  public CustomerAddressEntity findByCustomerId(String customerId);
}
