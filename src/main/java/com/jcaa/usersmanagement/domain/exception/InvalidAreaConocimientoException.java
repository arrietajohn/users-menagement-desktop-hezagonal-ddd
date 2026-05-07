package com.jcaa.usersmanagement.domain.exception;

public final class InvalidAreaConocimientoException extends DomainException {

  private static final String MESSAGE_INVALID = "El área de conocimiento es inválida: %s";

  private InvalidAreaConocimientoException(final String message) {
    super(message);
  }

  public static InvalidAreaConocimientoException becauseValueIsInvalid(final String value) {
    return new InvalidAreaConocimientoException(String.format(MESSAGE_INVALID, value));
  }
}

