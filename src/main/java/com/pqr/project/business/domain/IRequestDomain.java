package com.pqr.project.business.domain;

import com.pqr.project.common.dtos.CreateRequestDto;
import com.pqr.project.persistence.models.Request;

import java.util.List;

/**
 * @author Sergio Murillo
 */
public interface IRequestDomain {

    Request createRequest(final CreateRequestDto dto);

    List<Request> findAllRequests();

    List<Request> findRequestsByStateOrIdOrCustomer(String filter);

    Request findOne(final String id);
}
