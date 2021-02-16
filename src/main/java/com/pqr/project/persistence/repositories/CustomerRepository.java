package com.pqr.project.persistence.repositories;

import com.pqr.project.persistence.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sergio Murillo
 */
@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByIdentificationNumber(String identificationNumber);

    List<Customer> findByIdentificationNumberLikeOrderByIdAsc(String identificationNumber);
}
