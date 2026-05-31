package com.jcaa.usersmanagement.domain.exception.trabajogrado;

public class TrabajoGradoAlreadyExistsException extends RuntimeException {
    public TrabajoGradoAlreadyExistsException(Integer numeroOrden) {
        super("Ya existe un trabajo de grado con número de orden " + numeroOrden);
    }
}