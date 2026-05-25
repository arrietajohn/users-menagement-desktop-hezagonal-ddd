package com.jcaa.usersmanagement.domain.exception;

public final class InvalidProgramaGeneroException extends DomainException {

  private static final String MESSAGE_EMPTY = "El genero del programa no puede estar vacio.";

  private InvalidProgramaGeneroException(final String message) {
    super(message);
  }

  public static InvalidProgramaGeneroException becauseValueIsEmpty() {
    return new InvalidProgramaGeneroException(MESSAGE_EMPTY);
  }
}
