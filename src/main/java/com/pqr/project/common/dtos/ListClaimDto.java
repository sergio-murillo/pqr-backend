package com.pqr.project.common.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author Sergio Murillo
 */
@Data
@Builder
public class ListClaimDto {

    private List<ClaimDto> response;
    private Integer countResults;
}
