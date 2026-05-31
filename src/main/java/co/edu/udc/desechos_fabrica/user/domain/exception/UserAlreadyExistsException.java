package co.edu.udc.desechos_fabrica.user.domain.exception;

import co.edu.udc.desechos_fabrica.shared.DomainException;

public final class UserAlreadyExistsException extends DomainException {

  private static final String MESSAGE_EMAIL_EXISTS = "A user with email '%s' already exists.";

  private UserAlreadyExistsException(final String message) {
    super(message);
  }

  public static UserAlreadyExistsException becauseEmailAlreadyExists(final String email) {
    return new UserAlreadyExistsException(String.format(MESSAGE_EMAIL_EXISTS, email));
  }
}
