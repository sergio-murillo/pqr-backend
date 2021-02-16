package com.pqr.project.business.domain.exceptions;

/**
 * Clase encargada de controlar las excepciones del servidor
 *
 * @author Sergio Murillo
 */
public class InternalServerException extends RuntimeException {

    public InternalServerException(String message) {
        super(message);
    }
}
