package edu.udec.ConcesionarioDeAuto.domain.exceptions;

public final class PlacaInvalidaExcepcion extends DomainExceptionsCEA {

    private static final String MESSAGE_PLACA_INVALIDA = "La placa del vehículo es inválida.";
    private static final String MESSAGE_PLACA_NULA = "La placa del vehículo no puede ser nula.";
    private static final String MESSAGE_PLACA_EXISTENTE = "La placa del vehículo ya existe.";

    private PlacaInvalidaExcepcion(final String message) {
        super(message);
    }

    public static PlacaInvalidaExcepcion becausePlacaIsInvalid() {
        return new PlacaInvalidaExcepcion(MESSAGE_PLACA_INVALIDA);
    }

    public static PlacaInvalidaExcepcion becausePlacaIsNull() {
        return new PlacaInvalidaExcepcion(MESSAGE_PLACA_NULA);
    }
    public static PlacaInvalidaExcepcion becausePlacaIsExist() {
        return new PlacaInvalidaExcepcion(MESSAGE_PLACA_EXISTENTE);
    }
}
