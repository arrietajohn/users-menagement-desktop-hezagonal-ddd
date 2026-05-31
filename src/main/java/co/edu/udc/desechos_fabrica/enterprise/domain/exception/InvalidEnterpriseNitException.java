package co.edu.udc.desechos_fabrica.enterprise.domain.exception;

import co.edu.udc.desechos_fabrica.user.domain.exception.DomainException;

public final class InvalidEnterpriseNitException extends DomainException{

  private static final String MESSAGE_EMPTY = "The enterprise nit must not be empty.";
  private static final String MESSAGE_TOO_SHORT = "The enterprise nit must have at least %d characters.";

  private InvalidEnterpriseNitException(final String message) {
    super(message);
  }

  public static InvalidEnterpriseNitException becauseValueIsEmpty() {
    return new InvalidEnterpriseNitException(MESSAGE_EMPTY);
  }

  public static InvalidEnterpriseNitException becauseValueIsTooShort(final int minimumLength) {
    return new InvalidEnterpriseNitException(String.format(MESSAGE_TOO_SHORT, minimumLength));
  }
}
