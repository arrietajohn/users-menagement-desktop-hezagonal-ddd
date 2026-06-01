package edu.udec.ConcesionarioDeAuto.domain.exceptions;

public final class KilometrajeInvalidoExcepcion extends DomainExceptionsCEA {

    private static final String MESSAGE_KILOMETRAJE_INVALIDO = "El kilometraje debe ser mayor o igual a cero.";
    private static final String MESSAGE_KILOMETRAJE_NULO = "Debes introducir el valor del kilometraje";

    private KilometrajeInvalidoExcepcion(final String message) {
        super(message);
    }

    public static KilometrajeInvalidoExcepcion kilometrajeInvalido() {
        return new KilometrajeInvalidoExcepcion(MESSAGE_KILOMETRAJE_INVALIDO);
    }

    public static KilometrajeInvalidoExcepcion kilometrajeNulo() {
        return new KilometrajeInvalidoExcepcion(MESSAGE_KILOMETRAJE_NULO);
    }
}