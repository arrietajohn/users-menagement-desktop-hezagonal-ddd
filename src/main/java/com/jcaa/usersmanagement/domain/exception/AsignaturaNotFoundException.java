package com.jcaa.usersmanagement.domain.exception;

public final class AsignaturaNotFoundException extends DomainException {

    public AsignaturaNotFoundException(String message) {
        super(message);
    }

    public static AsignaturaNotFoundException becauseIdWasNotFound(String id) {
        return new AsignaturaNotFoundException("No se encontró una asignatura con el ID: " + id);
    }
}