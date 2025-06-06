package com.service.catering.infraestructure.repositories.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.service.catering.domain.model.CustomerEntity;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

  @Query("update Customer c set c.address = ?2 WHERE c.id = ?1")
  public void updateCustomerAddressId(String customerId, String address);
}
