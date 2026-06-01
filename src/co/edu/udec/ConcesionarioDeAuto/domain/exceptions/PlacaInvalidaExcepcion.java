package edu.udec.ConcesionarioDeAuto.domain.exceptions;

public final class PlacaInvalidaExcepcion extends DomainExceptionsCEA {

    private static final String MESSAGE_PLACA_INVALIDA = "La placa del vehículo es inválida.";
    private static final String MESSAGE_PLACA_NULA = "La placa del vehículo no puede ser nula.";
    private static final String MESSAGE_PLACA_INEXISTENTE = "La placa del vehículo no existe.";

    private PlacaInvalidaExcepcion(final String message) {
        super(message);
    }

    public static PlacaInvalidaExcepcion becausePlacaIsInvalid() {
        return new PlacaInvalidaExcepcion(MESSAGE_PLACA_INVALIDA);
    }

    public static PlacaInvalidaExcepcion becausePlacaIsNull() {
        return new PlacaInvalidaExcepcion(MESSAGE_PLACA_NULA);
    }
    public static PlacaInvalidaExcepcion becausePlacaIsNotExist() {
        return new PlacaInvalidaExcepcion(MESSAGE_PLACA_INEXISTENTE);
    }
}
