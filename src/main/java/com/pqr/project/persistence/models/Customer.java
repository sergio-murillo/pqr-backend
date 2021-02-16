package com.pqr.project.persistence.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Sergio Murillo
 */
@Data
@Document(collection = "customers")
public class Customer {

    @Id
    private String id;

    @Indexed(name = "identification_index", unique=true)
    @Field("identification_number")
    private String identificationNumber;

    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    @Field("cellphone_number")
    private String cellphoneNumber;

    private Integer age;
}
