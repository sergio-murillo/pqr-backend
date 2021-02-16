package com.pqr.project.business.domain.exceptions;

/**
 * Clase encargada de controlar las excepciones
 * donde no se encuentra un recurso
 * @author Sergio Murillo
 */
public class ResourceNotFoundException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
