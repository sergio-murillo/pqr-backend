package com.pqr.project.persistence.repositories;

import com.pqr.project.persistence.models.Request;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Sergio Murillo
 */
@Repository
public interface RequestRepository extends MongoRepository<Request, String>, QuerydslPredicateExecutor<Request> {

    @Query("{'$or':[ { 'customer.id' : ?0 }, { 'id' : ?0 } ] }")
    List<Request> findRequestsByCustomerOrId(String filter);
}