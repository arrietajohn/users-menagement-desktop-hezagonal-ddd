package com.jcaa.usersmanagement.domain.exception.trabajogrado;

public class TrabajoGradoNotFoundException extends RuntimeException {
    public TrabajoGradoNotFoundException(Integer numeroOrden) {
        super("Trabajo de grado con número de orden " + numeroOrden + " no encontrado");
    }
}
