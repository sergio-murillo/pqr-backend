package com.pqr.project.persistence.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QClaim is a Querydsl query type for Claim
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QClaim extends EntityPathBase<Claim> {

    private static final long serialVersionUID = -1532101690L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QClaim claim = new QClaim("claim");

    public final StringPath answer = createString("answer");

    public final DateTimePath<java.time.LocalDateTime> creationDate = createDateTime("creationDate", java.time.LocalDateTime.class);

    public final QCustomer customer;

    public final StringPath description = createString("description");

    public final StringPath id = createString("id");

    public final QRequest request;

    public final DateTimePath<java.time.LocalDateTime> responseDate = createDateTime("responseDate", java.time.LocalDateTime.class);

    public final StringPath state = createString("state");

    public final StringPath title = createString("title");

    public QClaim(String variable) {
        this(Claim.class, forVariable(variable), INITS);
    }

    public QClaim(Path<? extends Claim> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QClaim(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QClaim(PathMetadata metadata, PathInits inits) {
        this(Claim.class, metadata, inits);
    }

    public QClaim(Class<? extends Claim> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer")) : null;
        this.request = inits.isInitialized("request") ? new QRequest(forProperty("request"), inits.get("request")) : null;
    }

}

