package com.service.catering.infraestructure.repositories.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.service.catering.domain.model.ProducerEntity;

@Repository
public interface ProducerRepository extends JpaRepository<ProducerEntity, String> {

  @Query("SELECT u FROM ProducerEvent u WHERE u.status in ?1")
  public List<ProducerEntity> findByStatusPendiente(List<String> status);
}
