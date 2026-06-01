package edu.udec.ConcesionarioDeAuto.domain.exceptions;

public final class DniInvalidoExcepcion extends RuntimeException {

    private static final String MESSAGE_DNI_INVALIDO = "La identificación DNI es inválida.";
    private static final String MESSAGE_DNI_NULO = "La identificación DNI no puede ser nula.";

    private DniInvalidoExcepcion(final String message) {
        super(message);
    }

    public static DniInvalidoExcepcion dniInvalido() {
        return new DniInvalidoExcepcion(MESSAGE_DNI_INVALIDO);
    }

    public static DniInvalidoExcepcion dniNulo() {
        return new DniInvalidoExcepcion(MESSAGE_DNI_NULO);
    }
}
