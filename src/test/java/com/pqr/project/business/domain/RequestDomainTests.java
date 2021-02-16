package com.pqr.project.business.domain;

import com.pqr.project.business.domain.exceptions.ResourceNotFoundException;
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
public class RequestDomainTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private IRequestDomain requestDomain;

    private String customerId;
    private String requestId;

    @Before
    public void init() {
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
    }

    @After
    public void end() {
        customerRepository.deleteById(customerId);
        requestRepository.deleteById(requestId);
    }

    @Test
    public void assertThatRequestsCanBeFoundAll() {
        List<Request> requests = requestDomain.findAllRequests();
        assertThat(requests).isNotNull();
    }

    @Test
    public void assertThatRequestCanBeFoundById() {
        Request request = requestDomain.findOne(requestId);
        assertThat(request).isNotNull();
        assertThat(request.getId()).isEqualTo(requestId);
        assertThat(request.getTitle()).contains("Request");
        assertThat(request.getDescription()).contains("Description");
    }

    @Test
    public void assertThatRequestCannotBeFoundById() {
        assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> {
                    requestDomain.findOne("123");
                });
    }

    @Test
    public void assertThatRequestCanFilterByState() {
        List<Request> requestFound = requestDomain.findRequestsByStateOrIdOrCustomer("CREATED");
        assertThat(requestFound.size()).isGreaterThan(0);
        assertThat(requestFound.get(0).getTitle()).contains("Request");
        assertThat(requestFound.get(0).getDescription()).contains("Description");
    }

    @Test
    public void assertThatRequestCannotFilterByState() {
        List<Request> requestFound = requestDomain.findRequestsByStateOrIdOrCustomer("STATE");
        assertThat(requestFound.size()).isEqualTo(0);
    }

    @Test
    public void assertThatRequestCanFilterById() {
        List<Request> requestFound = requestDomain.findRequestsByStateOrIdOrCustomer(requestId);
        assertThat(requestFound.size()).isGreaterThan(0);
        assertThat(requestFound.get(0).getTitle()).contains("Request");
        assertThat(requestFound.get(0).getDescription()).contains("Description");
    }

    @Test
    public void assertThatClaimCanFilterByClientId() {
        List<Request> requestFound = requestDomain.findRequestsByStateOrIdOrCustomer("1120125412");
        assertThat(requestFound.size()).isGreaterThan(0);
        assertThat(requestFound.get(0).getTitle()).contains("Request");
        assertThat(requestFound.get(0).getDescription()).contains("Description");
    }
}
