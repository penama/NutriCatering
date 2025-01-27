package com.service.catering.application.model.paymentmethod;

import lombok.Getter;

@Getter
public class PaymentMethodDto {

    private String id;
    private String name;
    private String label;
    private String description;
    private String type;
    private String status;
    private String createdDate;

    public void setId( String id ){
//        if ( id == null || id.trim().isEmpty() )
//            throw new RuntimeException( "Field ID is null o Empty..." );
        this.id = id;
    }

    public void setName( String name ){
        if ( name == null || name.trim().isEmpty() )
            throw new RuntimeException( "Field name is null o Empty..." );
        this.name = name;
    }

    public void setLabel( String label ){
        if ( label == null || label.isEmpty() )
            throw new RuntimeException( "Field label is null o Empty..." );
        this.label = label;
    }

    public void setDescription( String description ){
        if ( description == null || description.isEmpty() )
            throw new RuntimeException( "Field description is null o Empty..." );
        this.description = description;
    }

    public void setType( String type ){
        if ( type == null || type.isEmpty() )
            throw new RuntimeException( "Field type is null o Empty..." );
        this.type = type;
    }

    public void setStatus( String status ){
//        if ( status == null || status.isEmpty() )
//            throw new RuntimeException( "Field status is null o Empty..." );
        this.status = status;
    }

    public void setCreatedDate( String createdDate ){
        if ( createdDate == null || createdDate.isEmpty() )
            throw new RuntimeException( "Field createdDate is null o Empty..." );
        this.createdDate = createdDate;
    }

}
