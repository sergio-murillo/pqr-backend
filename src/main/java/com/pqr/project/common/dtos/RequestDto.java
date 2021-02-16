package com.pqr.project.common.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Sergio Murillo
 */
@Data
public class RequestDto implements Serializable {

    @Id
    private String id;

    private String title;

    private String description;

    @JsonProperty("creation_date")
    private LocalDateTime creationDate;

    @JsonProperty("response_date")
    private LocalDateTime responseDate;

    private CustomerDto customer;

    private String answer;

    private String state;

    private String type;
}
