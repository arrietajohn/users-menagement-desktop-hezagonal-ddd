package co.edu.udc.desechos_fabrica.user.domain.exception;

import co.edu.udc.desechos_fabrica.shared.DomainException;

public final class UserNotFoundException extends DomainException {

  private static final String MESSAGE_BY_EMAIL = "The user with email '%s' was not found.";

  private UserNotFoundException(final String message) {
    super(message);
  }

  public static UserNotFoundException becauseEmailWasNotFound(final String userEmail) {
    return new UserNotFoundException(String.format(MESSAGE_BY_EMAIL, userEmail));
  }
}
