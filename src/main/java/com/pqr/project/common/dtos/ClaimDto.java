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
public class ClaimDto implements Serializable {

    @Id
    private String id;

    private String title;

    private String description;

    @JsonProperty("creation_date")
    private LocalDateTime creationDate;

    @JsonProperty("response_date")
    private LocalDateTime responseDate;

    private CustomerDto customer;

    private RequestDto request;

    private String answer;

    private String state;
}
