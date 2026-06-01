package edu.udec.ConcesionarioDeAuto.domain.exceptions;

public final class ClienteExcepcion extends DomainExceptionsCEA {

    private static final String MESSAGE_CLIENTE_INVALIDO ="El cliente contiene información inválida.";
    private static final String MESSAGE_CLIENTE_NO_ENCONTRADO = "El cliente no fue encontrado.";


    private ClienteExcepcion(final String message) {
        super(message);
    }

    public static ClienteExcepcion becauseClienteIsInvalid() {
        return new ClienteExcepcion(MESSAGE_CLIENTE_INVALIDO);
    }

    public static ClienteExcepcion becauseClienteNotFound() {
        return new ClienteExcepcion(MESSAGE_CLIENTE_NO_ENCONTRADO);
    }



}
