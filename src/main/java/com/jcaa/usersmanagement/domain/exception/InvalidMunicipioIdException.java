package com.jcaa.usersmanagement.domain.exception;

public final class InvalidMunicipioIdException extends DomainException {

  private static final String MESSAGE_EMPTY = "The municipio id must not be empty.";
  private static final String MESSAGE_TOO_LONG = "The municipio id must have at most %d characters.";

  private InvalidMunicipioIdException(final String message) {
    super(message);
  }

  public static InvalidMunicipioIdException becauseValueIsEmpty() {
    return new InvalidMunicipioIdException(MESSAGE_EMPTY);
  }

  public static InvalidMunicipioIdException becauseValueIsTooLong(final int maximumLength) {
    return new InvalidMunicipioIdException(String.format(MESSAGE_TOO_LONG, maximumLength));
  }
}
