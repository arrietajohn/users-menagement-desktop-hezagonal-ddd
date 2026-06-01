package edu.udec.ConcesionarioDeAuto.domain.exceptions;

public final class DescuentoInvalidoExcepcion extends DomainExceptionsCEA {

    private static final String MESSAGE_DESCUENTO_INVALIDO = "El descuento debe estar entre 0 y 100.";
    private static final String MESSAGE_DESCUENTO_NULO = "El descuento no puede ser nulo.";

    private DescuentoInvalidoExcepcion(final String message) {
        super(message);
    }

    public static DescuentoInvalidoExcepcion descuentoInvalido() {
        return new DescuentoInvalidoExcepcion(MESSAGE_DESCUENTO_INVALIDO);
    }

    public static DescuentoInvalidoExcepcion descuentoNulo() {
        return new DescuentoInvalidoExcepcion(MESSAGE_DESCUENTO_NULO);
    }
}