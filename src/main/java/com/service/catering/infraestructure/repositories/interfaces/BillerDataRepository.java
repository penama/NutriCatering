package com.service.catering.infraestructure.repositories.interfaces;

import com.service.catering.domain.model.BillerDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillerDataRepository extends JpaRepository<BillerDataEntity, String> {

    @Query("SELECT u FROM BillerData u WHERE u.customerId = ?1")
    public List<BillerDataEntity> findByCustomerId(String customerId);

}
