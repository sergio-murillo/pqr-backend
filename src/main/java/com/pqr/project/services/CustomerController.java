package com.pqr.project.services;

import com.pqr.project.common.dtos.*;
import com.pqr.project.persistence.repositories.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sergio Murillo
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value  = {"/find"})
    public ListCustomerDto find() {
        List<CustomerDto> dtos = customerRepository.findAll()
                .stream()
                .map(request -> modelMapper.map(request, CustomerDto.class))
                .collect(Collectors.toList());

        return ListCustomerDto.builder().response(dtos).countResults(dtos.size()).build();
    }
}
