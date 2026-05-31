package co.edu.udc.desechos_fabrica.enterprise.domain.exception;

import co.edu.udc.desechos_fabrica.shared.DomainException;

public final class InvalidEnterpriseNitException extends DomainException{

  private static final String MESSAGE_EMPTY = "The enterprise NIT must not be empty.";
  private static final String MESSAGE_INVALID_FORMAT = "The enterprise NIT must contain only digits between 9 and 12 characters long.";

  private InvalidEnterpriseNitException(final String message) {
    super(message);
  }

  public static InvalidEnterpriseNitException becauseValueIsEmpty() {
    return new InvalidEnterpriseNitException(MESSAGE_EMPTY);
  }

  public static InvalidEnterpriseNitException becauseFormatIsInvalid() {
    return new InvalidEnterpriseNitException(MESSAGE_INVALID_FORMAT);
  }
}
