package com.pqr.project.persistence.models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRequest is a Querydsl query type for Request
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRequest extends EntityPathBase<Request> {

    private static final long serialVersionUID = 1066437529L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRequest request = new QRequest("request");

    public final StringPath answer = createString("answer");

    public final StringPath type = createString("type");

    public final DateTimePath<java.time.LocalDateTime> creationDate = createDateTime("creationDate", java.time.LocalDateTime.class);

    public final QCustomer customer;

    public final StringPath description = createString("description");

    public final StringPath id = createString("id");

    public final DateTimePath<java.time.LocalDateTime> responseDate = createDateTime("responseDate", java.time.LocalDateTime.class);

    public final StringPath state = createString("state");

    public final StringPath title = createString("title");

    public QRequest(String variable) {
        this(Request.class, forVariable(variable), INITS);
    }

    public QRequest(Path<? extends Request> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRequest(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRequest(PathMetadata metadata, PathInits inits) {
        this(Request.class, metadata, inits);
    }

    public QRequest(Class<? extends Request> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new QCustomer(forProperty("customer")) : null;
    }

}

