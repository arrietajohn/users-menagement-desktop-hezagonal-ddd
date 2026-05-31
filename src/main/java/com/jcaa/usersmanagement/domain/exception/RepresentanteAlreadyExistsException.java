package com.jcaa.usersmanagement.domain.exception;

public class RepresentanteAlreadyExistsException extends DomainException {
    public RepresentanteAlreadyExistsException(String id) {
        super("El representante con el ID " + id + " ya existe.");
    }
}