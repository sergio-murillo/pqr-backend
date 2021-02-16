package com.pqr.project.business.domain;

import com.pqr.project.common.dtos.CreateClaimDto;
import com.pqr.project.persistence.models.Claim;

import java.util.List;

/**
 * @author Sergio Murillo
 */
public interface IClaimDomain {

    Claim createClaim(final CreateClaimDto dto);

    List<Claim> findClaimsByStateOrIdOrCustomer(String filter);

    Claim findOne(final String id);

    List<Claim> findAllClaims();
}
