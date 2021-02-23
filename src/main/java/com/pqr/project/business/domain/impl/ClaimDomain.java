package com.pqr.project.business.domain.impl;

import com.pqr.project.business.domain.IClaimDomain;
import com.pqr.project.business.domain.constants.PqrConstants;
import com.pqr.project.business.domain.exceptions.InternalServerException;
import com.pqr.project.business.domain.exceptions.ResourceNotFoundException;
import com.pqr.project.business.domain.utils.ResponsesFactory;
import com.pqr.project.common.dtos.CreateClaimDto;
import com.pqr.project.persistence.models.Claim;
import com.pqr.project.persistence.models.Customer;
import com.pqr.project.persistence.models.QClaim;
import com.pqr.project.persistence.models.Request;
import com.pqr.project.persistence.repositories.ClaimRepository;
import com.pqr.project.persistence.repositories.CustomerRepository;
import com.pqr.project.persistence.repositories.RequestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Sergio Murillo
 */
@Service
@Transactional
public class ClaimDomain implements IClaimDomain {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Metodo encargado de crear un reclamo
     */
    public Claim createClaim(final CreateClaimDto dto) {
        try {
            final Optional<Request> requestFound = requestRepository.findById(dto.getRequestId());
            if (requestFound.isPresent()) {
                final Request request = requestFound.get();
                final Long daysDifference = Duration.between(request.getCreationDate(), LocalDateTime.now()).toDays();
                if (daysDifference > PqrConstants.MAX_DAYS || !request.getAnswer().isEmpty()) {
                    final Claim claim = modelMapper.map(dto, Claim.class);
                    claim.setCustomer(request.getCustomer());
                    claim.setCreationDate(LocalDateTime.now());
                    claim.setRequest(request);
                    claim.setId(UUID.randomUUID().toString());
                    return claimRepository.save(claim);
                }

                throw new InternalServerException(ResponsesFactory.getInternalServerErrorResponse(PqrConstants.RESTRICTION));
            } else {
                throw new ResourceNotFoundException(ResponsesFactory.getResourceNotFoundResponse(PqrConstants.REQUEST));
            }

        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(ResponsesFactory.getInternalServerErrorResponse(e.getMessage()));
        }
    }

    /**
     * Metodo encargado de listar todos los reclamos
     */
    public List<Claim> findAllClaims() {
        try {
            return claimRepository.findAll();
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(ResponsesFactory.getInternalServerErrorResponse(e.getMessage()));
        }
    }

    /**
     * Metodo encargado de filtrar por estado y ID todos los reclamos
     */
    public List<Claim> findClaimsByStateOrIdOrCustomer(final String filter) {
        try {
            final QClaim claim = QClaim.claim;

            final List<Customer> customerFound = customerRepository.findByIdentificationNumberLikeOrderByIdAsc(filter);

            final Iterable<Claim> claimsIterable = claimRepository
                    .findAll(claim.id.contains(filter).or(claim.state.contains(filter)).or(claim.customer.in(customerFound)));

            final List<Claim> claims = StreamSupport
                    .stream(claimsIterable.spliterator(), false)
                    .collect(Collectors.toList());

            return claims;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(ResponsesFactory.getInternalServerErrorResponse(e.getMessage()));
        }
    }

    /**
     * Metodo encargado de buscar un reclamo
     */
    public Claim findOne(final String id) {
        try {
            final Optional<Claim> request = claimRepository.findById(id);
            if (request.isPresent()) {
                return request.get();
            } else {
                throw new ResourceNotFoundException(ResponsesFactory.getResourceNotFoundResponse(PqrConstants.CLAIM));
            }
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(ResponsesFactory.getInternalServerErrorResponse(e.getMessage()));
        }
    }
}
