package com.pqr.project.common.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.pqr.project.business.domain.constants.PqrConstants.NOT_EMPTY_ERROR;
import static com.pqr.project.business.domain.constants.PqrConstants.NOT_NULL_ERROR;
import static com.pqr.project.business.domain.constants.PqrConstants.VALUES_ERROR;

/**
 * @author Sergio Murillo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateClaimDto {

    @NotEmpty(message = NOT_EMPTY_ERROR)
    @NotNull(message = NOT_NULL_ERROR)
    private String title;

    @NotEmpty(message = NOT_EMPTY_ERROR)
    @NotNull(message = NOT_NULL_ERROR)
    private String description;

    private String answer;

    @NotEmpty(message = NOT_EMPTY_ERROR)
    @NotNull(message = NOT_NULL_ERROR)
    private String requestId;

    @NotEmpty(message = NOT_EMPTY_ERROR)
    @NotNull(message = NOT_NULL_ERROR)
    @Pattern(regexp = "CREATED|IN_PROCESS|CLOSED", message = VALUES_ERROR)
    private String state;
}

