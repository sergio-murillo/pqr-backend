package com.pqr.project.common.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Sergio Murillo
 */
@Data
@Builder
public class ListRequestDto {

    private List<RequestDto> response;
    private Integer countResults;
}
