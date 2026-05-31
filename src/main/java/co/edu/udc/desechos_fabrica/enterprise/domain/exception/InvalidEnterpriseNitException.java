package co.edu.udc.desechos_fabrica.enterprise.domain.exception;

import co.edu.udc.desechos_fabrica.user.domain.exception.DomainException;

public final class InvalidEnterpriseNitException extends DomainException{

  private static final String MESSAGE_EMPTY = "The enterprise nit must not be empty.";
  private static final String MESSAGE_CHARACTER_LENGTH = "The enterprise nit must have at least %d and most %d characters.";

  private InvalidEnterpriseNitException(final String message) {
    super(message);
  }

  public static InvalidEnterpriseNitException becauseValueIsEmpty() {
    return new InvalidEnterpriseNitException(MESSAGE_EMPTY);
  }

  public static InvalidEnterpriseNitException becauseLengthIsInvalid(final int minimumLength, final int maximumLength) {
    return new InvalidEnterpriseNitException(String.format(MESSAGE_CHARACTER_LENGTH, minimumLength, maximumLength));
  }

}
