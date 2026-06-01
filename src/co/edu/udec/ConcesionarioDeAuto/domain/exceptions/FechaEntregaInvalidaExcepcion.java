package edu.udec.ConcesionarioDeAuto.domain.exceptions;


public final class FechaEntregaInvalidaExcepcion extends DomainExceptionsCEA {

    private static final String MESSAGE_FECHA_INVALIDA =
            "La fecha de entrega no es válida.";

    private static final String MESSAGE_FECHA_NULA =
            "La fecha de entrega no puede ser nula.";

    private FechaEntregaInvalidaExcepcion(final String message) {
        super(message);
    }

    public static FechaEntregaInvalidaExcepcion fechaEntregaInvalida() {
        return new FechaEntregaInvalidaExcepcion(MESSAGE_FECHA_INVALIDA);
    }

    public static FechaEntregaInvalidaExcepcion fechaEntregaNula() {
        return new FechaEntregaInvalidaExcepcion(MESSAGE_FECHA_NULA);
    }
}