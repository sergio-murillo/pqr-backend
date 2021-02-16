package com.pqr.project.persistence.models;

import com.querydsl.core.annotations.QueryEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * @author Sergio Murillo
 */
@QueryEntity
@Data
@Document(collection = "requests")
@CompoundIndexes({ @CompoundIndex(name = "customer_state", def = "{'customer.id' : 1, 'state': 1}") })
public class Request {

    @Id
    private String id;

    private String title;

    private String description;

    @Field("creation_date")
    private LocalDateTime creationDate;

    @Field("response_date")
    private LocalDateTime responseDate;

    @DBRef
    private Customer customer;

    private String answer;

    private String state;

    private String type;
}
