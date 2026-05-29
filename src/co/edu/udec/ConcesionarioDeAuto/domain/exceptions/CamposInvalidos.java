package edu.udec.ConcesionarioDeAuto.domain.exceptions;

public class CamposInvalidos extends RuntimeException {
    public CamposInvalidos(String message) {
        super(message);
    }
}
