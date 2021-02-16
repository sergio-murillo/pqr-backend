package com.pqr.project.business.domain.exceptions;

/**
 * Clase encargada de controlar las excepciones con propiedades erroneas
 *
 * @author Sergio Murillo
 */
public class BadRequesException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public BadRequesException(String message) {
        super(message);
    }
}
