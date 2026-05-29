package edu.udec.ConcesionarioDeAuto.domain.exceptions;

public class NitInvalido extends RuntimeException {
    public NitInvalido(String message) {
        super(message);
    }
}
