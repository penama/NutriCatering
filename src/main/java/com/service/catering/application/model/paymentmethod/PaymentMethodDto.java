package com.service.catering.application.model.paymentmethod;

import lombok.Getter;

@Getter
public class PaymentMethodDto {

  public String id;
  public String name;
  public String label;
  public String description;
  public String type;
  public String status;
  public String createdDate;

  public void setId(String id) {
    //        if ( id == null || id.trim().isEmpty() )
    //            throw new RuntimeException( "Field ID is null o Empty..." );
    this.id = id;
  }

  public void setName(String name) {
    if (name == null || name.trim().isEmpty())
      throw new RuntimeException("Field fullName is null o Empty...");
    this.name = name;
  }

  public void setLabel(String label) {
    if (label == null || label.isEmpty())
      throw new RuntimeException("Field label is null o Empty...");
    this.label = label;
  }

  public void setDescription(String description) {
    if (description == null || description.isEmpty())
      throw new RuntimeException("Field description is null o Empty...");
    this.description = description;
  }

  public void setType(String type) {
    if (type == null || type.isEmpty()) throw new RuntimeException("Field type is null o Empty...");
    this.type = type;
  }

  public void setStatus(String status) {
    //        if ( status == null || status.isEmpty() )
    //            throw new RuntimeException( "Field status is null o Empty..." );
    this.status = status;
  }

  public void setCreatedDate(String createdDate) {
    if (createdDate == null || createdDate.isEmpty())
      throw new RuntimeException("Field createdAt is null o Empty...");
    this.createdDate = createdDate;
  }
}
