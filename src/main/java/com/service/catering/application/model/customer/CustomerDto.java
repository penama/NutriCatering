package com.service.catering.application.model.customer;

import com.service.catering.application.exceptions.FieldNullException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class CustomerDto {

    private String id;
    private String name;
    private String address;
    private String phone;
    private String birtDate;
    private String createdDate;
    private String status;

    public void setId( String id ) {
        this.id = id;
    }
    public void setName( String name ) throws FieldNullException {
        System.out.println( "nombre " + name );
        if ( name == null || name.isEmpty() ){
            throw new FieldNullException( "El cammpo 'name' no puede ser null" );
        }
        this.name = name;
    }
    public void setAddress( String address ) throws FieldNullException {
        if ( address == null || address.isEmpty() ){
            throw new FieldNullException( "El cammpo 'address' no puede ser null" );
        }
        this.address = address;
    }
    public void setPhone( String phone ) throws FieldNullException {
        if ( phone == null || phone.isEmpty() ){
            throw new FieldNullException( "El cammpo 'phone' no puede ser null" );
        }
        this.phone = phone;
    }
    public void setBirtDate( String birtDate ) throws FieldNullException {
        if ( birtDate == null || birtDate.isEmpty() ){
            throw new FieldNullException( "El cammpo 'birtDate' no puede ser null" );
        }
        this.birtDate = birtDate;
    }
    public void setCreatedDate( String createdDate )  {
        this.createdDate = createdDate;
    }
    public void setStatus( String status ) {
        this.status = status;
    }

}
