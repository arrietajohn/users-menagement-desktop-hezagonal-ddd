package edu.udec.ConcesionarioDeAuto.domain.exceptions;

public class NitInvalidoExcepcion extends RuntimeException {

    private static final String MESSAGE_NIT_INVALIDO = "El NIT ingresado es inválido.";
    private static final String MESSAGE_NIT_NULO = "El NIT no puede ser nulo.";


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
