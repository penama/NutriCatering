package com.service.catering.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "ProducerEvent")
@Data
public class ProducerEntity {

  @Id public String id;

  @Column(length = 1000)
  public String body;

  public String status;
  public String processedDate;
  public String createdDate;
}
