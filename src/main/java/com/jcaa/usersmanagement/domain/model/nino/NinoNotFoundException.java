package com.jcaa.usersmanagement.domain.model.nino;

public class NinoNotFoundException extends RuntimeException {

    public NinoNotFoundException(Long id) {
        super("No se encontró al niño con ID: " + id);
    }

    public NinoNotFoundException(String matricula) {
        super("No se encontró al niño con matrícula: " + matricula);
    }

    public NinoNotFoundException() {
        super("No se encontró al niño");
    }
}
