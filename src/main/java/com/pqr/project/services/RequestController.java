package com.pqr.project.services;

import com.pqr.project.business.domain.IRequestDomain;
import com.pqr.project.common.dtos.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Sergio Murillo
 */
@RestController
@RequestMapping("/api/request")
public class RequestController {

  @Autowired
  private IRequestDomain requestDomain;

  @Autowired
  private ModelMapper modelMapper;

  @PostMapping(value = "/create")
  public RequestDto create(@Valid @RequestBody final CreateRequestDto dto) {
    return modelMapper.map(requestDomain.createRequest(dto), RequestDto.class);
  }

  @GetMapping("/find/{id}")
  public RequestDto find(@PathVariable final String id) {
    return modelMapper.map(requestDomain.findOne(id), RequestDto.class);
  }

  @GetMapping(value  = {"/find"})
  public ListRequestDto find() {
    List<RequestDto> dtos = requestDomain.findAllRequests()
            .stream()
            .map(request -> modelMapper.map(request, RequestDto.class))
            .collect(Collectors.toList());

    return ListRequestDto.builder().response(dtos).countResults(dtos.size()).build();
  }

  @GetMapping(value  = {"/filter/{filter}"})
  public ListRequestDto findWithFilter(@PathVariable final String filter) {
    List<RequestDto> dtos = requestDomain.findRequestsByStateOrIdOrCustomer(filter)
            .stream()
            .map(request -> modelMapper.map(request, RequestDto.class))
            .collect(Collectors.toList());

    return ListRequestDto.builder().response(dtos).countResults(dtos.size()).build();
  }
}
