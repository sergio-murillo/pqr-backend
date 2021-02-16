package com.pqr.project.business.domain.impl;

import com.pqr.project.business.domain.IRequestDomain;
import com.pqr.project.business.domain.constants.PqrConstants;
import com.pqr.project.business.domain.exceptions.InternalServerException;
import com.pqr.project.business.domain.exceptions.ResourceNotFoundException;
import com.pqr.project.business.domain.utils.ResponsesFactory;
import com.pqr.project.persistence.models.Customer;
import com.pqr.project.persistence.models.QRequest;
import com.pqr.project.persistence.models.Request;
import com.pqr.project.common.dtos.CreateRequestDto;
import com.pqr.project.persistence.repositories.CustomerRepository;
import com.pqr.project.persistence.repositories.RequestRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class RequestDomain implements IRequestDomain {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Metodo encargado de crear una petición/queja
     */
    public Request createRequest(final CreateRequestDto dto) {
        try {
            Optional<Customer> customerFound = Optional.of(customerRepository.findByIdentificationNumber(dto.getClientId()));
            if (customerFound.isPresent()) {
                Customer customer = customerFound.get();
                Request request = modelMapper.map(dto, Request.class);
                request.setId(UUID.randomUUID().toString());
                request.setCustomer(customer);
                request.setCreationDate(LocalDateTime.now());
                return requestRepository.save(request);
            } else {
                throw new ResourceNotFoundException(ResponsesFactory.getResourceNotFoundResponse(PqrConstants.CUSTOMER));
            }
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(ResponsesFactory.getInternalServerErrorResponse(e.getMessage()));
        }
    }

    /**
     * Metodo encargado de listar todas las peticiones/quejas
     */
    public List<Request> findAllRequests() {
        try {
            return requestRepository.findAll();
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(ResponsesFactory.getInternalServerErrorResponse(e.getMessage()));
        }
    }

    /**
     * Metodo encargado de filtrar por estado o ID todas las peticiones/quejas
     */
    public List<Request> findRequestsByStateOrIdOrCustomer(String filter) {
        try {
            QRequest request = new QRequest("request");

            List<Customer> customerFound = customerRepository.findByIdentificationNumberLikeOrderByIdAsc(filter);

            Iterable<Request> requestsIterable = requestRepository
                    .findAll(request.id.contains(filter).or(request.state.contains(filter).or(request.customer.in(customerFound))));

            List<Request> requests = StreamSupport
                    .stream(requestsIterable.spliterator(), false)
                    .collect(Collectors.toList());

            return requests;
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(ResponsesFactory.getInternalServerErrorResponse(e.getMessage()));
        }
    }

    /**
     * Metodo encargado de buscar una Petición o Queja
     */
    public Request findOne(final String id) {
        try {
            Optional<Request> request = requestRepository.findById(id);
            if (request.isPresent()) {
                return request.get();
            } else {
                throw new ResourceNotFoundException(ResponsesFactory.getResourceNotFoundResponse(PqrConstants.REQUEST));
            }
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerException(ResponsesFactory.getInternalServerErrorResponse(e.getMessage()));
        }
    }
}
