package com.jcaa.usersmanagement.domain.exception;

public final class InvalidProgramaNameException extends DomainException {

  private static final String MESSAGE_EMPTY = "El nombre del programa no puede estar vacio.";
  private static final String MESSAGE_TOO_SHORT = "El nombre del programa debe tener al menos %d caracteres.";

  private InvalidProgramaNameException(final String message) {
    super(message);
  }

  public static InvalidProgramaNameException becauseValueIsEmpty() {
    return new InvalidProgramaNameException(MESSAGE_EMPTY);
  }

  public static InvalidProgramaNameException becauseLengthIsTooShort(final int length) {
    return new InvalidProgramaNameException(String.format(MESSAGE_TOO_SHORT, length));
  }
}
