package com.pqr.project.business.domain.constants;

/**
 * @author Sergio Murillo
 */
public class PqrConstants {

    private PqrConstants() {
        /*
         * Private constructor. Utility class
         */
    }

    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error [%s]";
    public static final String NOT_FOUND_RESOURCE = "Resource Not Found [%s]";
    public static final String RESTRICTION = "Restriction not met";

    public static final String CUSTOMER = "Customer";
    public static final String REQUEST = "Request";
    public static final String CLAIM = "Claim";

    public static final String NOT_NULL_ERROR = "El campo no puede ser null";
    public static final String NOT_EMPTY_ERROR = "El campo no puede estar vacio";
    public static final String VALUES_ERROR = "El campo no está incluido dentro de los posibles valores";

    public static final String[] SWAGGER_URLS = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };

    public static final String CREATED = "CREATED";

    public static final String UPDATED = "UPDATED";

    public static final String DELETED = "DELETED";
}
