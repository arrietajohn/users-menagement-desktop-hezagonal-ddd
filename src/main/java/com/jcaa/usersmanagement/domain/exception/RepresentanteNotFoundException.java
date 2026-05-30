package com.jcaa.usersmanagement.domain.exception;

public class RepresentanteNotFoundException extends DomainException {
    public RepresentanteNotFoundException(String id) {
        super("No se encontro un representante con el ID: " + id);
    }
}