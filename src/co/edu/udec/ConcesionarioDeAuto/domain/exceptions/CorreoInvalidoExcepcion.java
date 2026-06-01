package edu.udec.ConcesionarioDeAuto.domain.exceptions;

public final class CorreoInvalidoExcepcion extends DomainExceptionsCEA {

    private static final String MESSAGE_CORREO_INVALIDO = "Debe introducir un correo electrónico válido.";
    private static final String MESSAGE_CORREO_NULO = "El correo electrónico no puede ser nulo.";
    private static final String MESSAGE_CORREO_INEXISTENTE = "El correo no existe.";


    private CorreoInvalidoExcepcion(final String message) {
        super(message);
    }

    public static CorreoInvalidoExcepcion correoInvalido() {
        return new CorreoInvalidoExcepcion(MESSAGE_CORREO_INVALIDO);
    }

    public static CorreoInvalidoExcepcion correoNulo() {
        return new CorreoInvalidoExcepcion(MESSAGE_CORREO_NULO);
    }

    public static CorreoInvalidoExcepcion correoInexistente() {
        return new CorreoInvalidoExcepcion(MESSAGE_CORREO_INEXISTENTE);
    }
}