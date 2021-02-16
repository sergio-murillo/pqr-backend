package com.pqr.project.services;

import com.pqr.project.business.domain.IClaimDomain;
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
@RequestMapping("/api/claim")
public class ClaimController {

    @Autowired
    private IClaimDomain claimDomain;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/create")
    public ClaimDto create(@Valid @RequestBody final CreateClaimDto dto) {
        return modelMapper.map(claimDomain.createClaim(dto), ClaimDto.class);
    }

    @GetMapping("/find/{id}")
    public ClaimDto find(@PathVariable final String id) {
        return modelMapper.map(claimDomain.findOne(id), ClaimDto.class);
    }

    @GetMapping(value  = {"/find"})
    public ListClaimDto find() {
        List<ClaimDto> dtos = claimDomain.findAllClaims()
                .stream()
                .map(request -> modelMapper.map(request, ClaimDto.class))
                .collect(Collectors.toList());

        return ListClaimDto.builder().response(dtos).countResults(dtos.size()).build();
    }

    @GetMapping(value  = {"/filter/{filter}"})
    public ListClaimDto findWithFilter(@PathVariable final String filter) {
        List<ClaimDto> dtos = claimDomain.findClaimsByStateOrIdOrCustomer(filter)
                .stream()
                .map(claim -> modelMapper.map(claim, ClaimDto.class))
                .collect(Collectors.toList());

        return ListClaimDto.builder().response(dtos).countResults(dtos.size()).build();
    }
}
