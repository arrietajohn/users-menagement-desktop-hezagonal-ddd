package edu.udec.ConcesionarioDeAuto.domain.exceptions;

public final class StockInvalidoExcepcion extends DomainExceptionsCEA {

    private static final String MESSAGE_STOCK_INVALIDO = "El stock debe ser mayor o igual a cero.";
    private static final String MESSAGE_STOCK_NULO = "El stock no puede ser nulo.";

    private StockInvalidoExcepcion(final String message) {
        super(message);
    }

    public static StockInvalidoExcepcion stockInvalido() {
        return new StockInvalidoExcepcion(MESSAGE_STOCK_INVALIDO);
    }

    public static StockInvalidoExcepcion stockNulo() {
        return new StockInvalidoExcepcion(MESSAGE_STOCK_NULO);
    }
}