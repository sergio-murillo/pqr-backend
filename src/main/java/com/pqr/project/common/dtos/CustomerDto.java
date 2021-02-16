package com.pqr.project.common.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * @author Sergio Murillo
 */
@Data
public class CustomerDto implements Serializable {

    @Id
    private String id;

    @JsonProperty("identification_number")
    private String identificationNumber;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("cellphone_number")
    private String cellphoneNumber;

    private Integer age;
}
