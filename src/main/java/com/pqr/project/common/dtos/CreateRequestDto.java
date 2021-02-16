package com.pqr.project.common.dtos;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static com.pqr.project.business.domain.constants.PqrConstants.*;

/**
 * @author Sergio Murillo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRequestDto {

    @NotEmpty(message = NOT_EMPTY_ERROR)
    @NotNull(message = NOT_NULL_ERROR)
    private String title;

    @NotEmpty(message = NOT_EMPTY_ERROR)
    @NotNull(message = NOT_NULL_ERROR)
    private String description;

    private String answer;

    @NotEmpty(message = NOT_EMPTY_ERROR)
    @NotNull(message = NOT_NULL_ERROR)
    private String clientId;

    @NotEmpty(message = NOT_EMPTY_ERROR)
    @NotNull(message = NOT_NULL_ERROR)
    @Pattern(regexp = "CREATED|IN_PROCESS|CLOSED", message = VALUES_ERROR)
    private String state;

    @NotEmpty(message = NOT_EMPTY_ERROR)
    @NotNull(message = NOT_NULL_ERROR)
    @Pattern(regexp = "P|Q", message = VALUES_ERROR)
    private String type;
}
