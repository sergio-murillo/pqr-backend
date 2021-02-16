package com.pqr.project.persistence.repositories;

import com.pqr.project.persistence.models.Claim;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Sergio Murillo
 */
@Repository
public interface ClaimRepository extends MongoRepository<Claim, String>, QuerydslPredicateExecutor<Claim> {

}
