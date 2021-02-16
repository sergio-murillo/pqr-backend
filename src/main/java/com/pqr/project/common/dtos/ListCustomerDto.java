package com.pqr.project.common.dtos;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * Clase encargada de representar el DTO para la respuesta a una lista de clientes
 * @author Sergio Murillo
 */
@Data
@Builder
public class ListCustomerDto {

    private List<CustomerDto> response;
    private Integer countResults;
}
