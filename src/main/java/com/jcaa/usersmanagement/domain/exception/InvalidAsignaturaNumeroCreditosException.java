package com.jcaa.usersmanagement.domain.exception;

public final class InvalidAsignaturaNumeroCreditosException extends DomainException {

  private static final String MESSAGE_NOT_POSITIVE = "El número de créditos debe ser un entero positivo. Valor recibido: %d";

  private InvalidAsignaturaNumeroCreditosException(final String message) {
    super(message);
  }

  public static InvalidAsignaturaNumeroCreditosException becauseValueIsNotPositive(final int value) {
    return new InvalidAsignaturaNumeroCreditosException(String.format(MESSAGE_NOT_POSITIVE, value));
  }
}

