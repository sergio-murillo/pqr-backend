package com.pqr.project.business.domain;

import com.pqr.project.business.domain.exceptions.ResourceNotFoundException;
import com.pqr.project.persistence.models.Claim;
import com.pqr.project.persistence.models.Customer;
import com.pqr.project.persistence.models.Request;
import com.pqr.project.persistence.repositories.ClaimRepository;
import com.pqr.project.persistence.repositories.CustomerRepository;
import com.pqr.project.persistence.repositories.RequestRepository;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClaimDomainTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private IClaimDomain claimDomain;

    private String claimId;
    private String customerId;
    private String requestId;

    @Before
    public void init() {
        Claim claim = new Claim();
        Request request = new Request();

        Customer customerFound = customerRepository.findByIdentificationNumber("1120125412");

        if (customerFound == null) {
            Customer customerOne = new Customer();
            customerOne.setIdentificationNumber("1120125412");
            customerOne.setFirstName("Pepito");
            customerOne.setLastName("Perez");
            customerOne.setAge(25);
            customerOne.setCellphoneNumber("3123667715");
            customerRepository.save(customerOne);
            customerFound = customerRepository.findByIdentificationNumber("1120125412");
        }
        customerId = customerFound.getId();

        requestId = UUID.randomUUID().toString();
        request.setCustomer(customerFound);
        request.setTitle("Request 1");
        request.setDescription("Description Request 1");
        request.setState("CREATED");
        request.setType("P");
        request.setCreationDate(LocalDateTime.now());
        request.setId(requestId);
        requestRepository.save(request);

        claimId = UUID.randomUUID().toString();
        claim.setCustomer(customerFound);
        claim.setTitle("Claim 1");
        claim.setDescription("Description Claim 1");
        claim.setState("CREATED");
        claim.setCreationDate(LocalDateTime.now());
        claim.setId(claimId);
        claim.setRequest(request);

        claimRepository.save(claim);
    }

    @After
    public void end() {
        claimRepository.deleteById(claimId);
        customerRepository.deleteById(customerId);
        requestRepository.deleteById(requestId);
    }

    @Test
    public void assertThatClaimsCanBeFoundAll() {
        List<Claim> claims = claimDomain.findAllClaims();
        assertThat(claims).isNotNull();
    }

    @Test
    public void assertThatClaimCanBeFoundById() {
        Claim claim = claimDomain.findOne(claimId);
        assertThat(claim).isNotNull();
        assertThat(claim.getId()).isEqualTo(claimId);
        assertThat(claim.getTitle()).contains("Claim");
        assertThat(claim.getDescription()).contains("Description");
    }

    @Test
    public void assertThatClaimCannotBeFoundById() {
        assertThatExceptionOfType(ResourceNotFoundException.class)
        .isThrownBy(() -> {
            claimDomain.findOne("123");
        });
    }

    @Test
    public void assertThatClaimCannotFilterByState() {
        List<Claim> claimsFound = claimDomain.findClaimsByStateOrIdOrCustomer("STATE");
        assertThat(claimsFound.size()).isEqualTo(0);
    }

    @Test
    public void assertThatClaimCanFilterById() {
        List<Claim> claimsFound = claimDomain.findClaimsByStateOrIdOrCustomer(claimId);
        assertThat(claimsFound.size()).isGreaterThan(0);
        assertThat(claimsFound.get(0).getTitle()).contains("Claim");
        assertThat(claimsFound.get(0).getDescription()).contains("Description");
    }

    @Test
    public void assertThatClaimCanFilterByClientId() {
        List<Claim> claimsFound = claimDomain.findClaimsByStateOrIdOrCustomer("1120125412");
        assertThat(claimsFound.size()).isGreaterThan(0);
        assertThat(claimsFound.get(0).getTitle()).contains("Claim");
        assertThat(claimsFound.get(0).getDescription()).contains("Description");
    }
}
