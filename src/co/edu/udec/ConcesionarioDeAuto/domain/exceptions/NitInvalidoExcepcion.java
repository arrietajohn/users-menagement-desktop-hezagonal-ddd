package edu.udec.ConcesionarioDeAuto.domain.exceptions;

public class NitInvalidoExcepcion extends RuntimeException {

    private static final String MESSAGE_NIT_INVALIDO = "El NIT ingresado no esta registrado.";
    private static final String MESSAGE_NIT_NULO = "Debes llenar el campo";


    private NitInvalidoExcepcion(final String message) {
        super(message);
    }

    public static NitInvalidoExcepcion nitInvalido() {
        return new NitInvalidoExcepcion(MESSAGE_NIT_INVALIDO);
    }

    public static NitInvalidoExcepcion nitNulo() {
        return new NitInvalidoExcepcion(MESSAGE_NIT_NULO);
    }

}
