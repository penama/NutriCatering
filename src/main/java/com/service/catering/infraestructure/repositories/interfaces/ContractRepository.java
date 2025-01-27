package com.service.catering.infraestructure.repositories.interfaces;

import com.service.catering.domain.model.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<ContractEntity, String> {

    @Query("SELECT u FROM Contract u WHERE u.customerId = ?1")
    public List<ContractEntity> findByCustomerId(String customerId);

}
