package com.jcaa.usersmanagement.domain.exception;

public final class InvalidAsignaturaSemestreException extends DomainException {

  private static final String MESSAGE_OUT_OF_RANGE = "El semestre debe estar entre 1 y 12. Valor recibido: %d";

  private InvalidAsignaturaSemestreException(final String message) {
    super(message);
  }

  public static InvalidAsignaturaSemestreException becauseValueIsOutOfRange(final int value) {
    return new InvalidAsignaturaSemestreException(String.format(MESSAGE_OUT_OF_RANGE, value));
  }
}

