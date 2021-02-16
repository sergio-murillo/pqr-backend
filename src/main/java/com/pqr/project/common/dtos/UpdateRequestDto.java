package com.pqr.project.common.dtos;

import lombok.Builder;
import lombok.Data;

/**
 * @author Sergio Murillo
 */
@Data
@Builder
public class UpdateRequestDto {

    private String id;

    private String title;

    private String description;

    private String answer;

    private String type;
}
