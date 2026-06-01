package edu.udec.ConcesionarioDeAuto.domain.exceptions;

public final class CilindrajeInvalidoExcepcion extends DomainExceptionsCEA {

    private static final String MESSAGE_CILINDRAJE_INVALIDO = "El cilindraje debe contener únicamente números.";
    private static final String MESSAGE_CILINDRAJE_NULO = "El cilindraje no puede ser nulo.";

    private CilindrajeInvalidoExcepcion(final String message) {
        super(message);
    }

    public static CilindrajeInvalidoExcepcion cilindrajeInvalido() {
        return new CilindrajeInvalidoExcepcion(MESSAGE_CILINDRAJE_INVALIDO);
    }

    public static CilindrajeInvalidoExcepcion cilindrajeNulo() {
        return new CilindrajeInvalidoExcepcion(MESSAGE_CILINDRAJE_NULO);
    }
}