package com.jcaa.usersmanagement.domain.exception;

public final class AsignaturaAlreadyExistsException extends DomainException {

    public AsignaturaAlreadyExistsException(String message) {
        super(message);
    }

    public static AsignaturaAlreadyExistsException becauseNombreAlreadyExists(String nombre) {
        return new AsignaturaAlreadyExistsException("Ya existe una asignatura con el nombre: " + nombre);
    }
}