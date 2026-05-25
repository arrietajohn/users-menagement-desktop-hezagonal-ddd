package com.jcaa.usersmanagement.domain.exception;

public final class InvalidProgramaIdException extends DomainException {

  private static final String MESSAGE_EMPTY = "El ID del programa no puede ser nulo o negativo.";

  private InvalidProgramaIdException(final String message) {
    super(message);
  }

  public static InvalidProgramaIdException becauseValueIsInvalid() {
    return new InvalidProgramaIdException(MESSAGE_EMPTY);
  }
}
