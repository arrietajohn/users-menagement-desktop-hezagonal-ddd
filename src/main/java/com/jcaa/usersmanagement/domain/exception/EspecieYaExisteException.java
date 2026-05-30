package com.jcaa.usersmanagement.domain.exception;


public class EspecieYaExisteException extends RuntimeException {

    public EspecieYaExisteException(String nombreCientifico) {
        super("Ya existe una especie registrada con el nombre científico: " + nombreCientifico);
    }
}
