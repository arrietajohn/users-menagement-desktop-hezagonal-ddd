package com.jcaa.usersmanagement.domain.exception;

public final class InvalidAsignaturaNombreException extends DomainException {

  private static final String MESSAGE_EMPTY = "El nombre de la asignatura no puede estar vacío.";
  private static final String MESSAGE_TOO_SHORT = "El nombre de la asignatura debe tener al menos %d caracteres.";

  private InvalidAsignaturaNombreException(final String message) {
    super(message);
  }

  public static InvalidAsignaturaNombreException becauseValueIsEmpty() {
    return new InvalidAsignaturaNombreException(MESSAGE_EMPTY);
  }

  public static InvalidAsignaturaNombreException becauseLengthIsTooShort(final int min) {
    return new InvalidAsignaturaNombreException(String.format(MESSAGE_TOO_SHORT, min));
  }
}

