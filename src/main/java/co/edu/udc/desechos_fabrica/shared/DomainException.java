package co.edu.udc.desechos_fabrica.shared;

public abstract class DomainException extends RuntimeException {

  protected DomainException(final String message) {
    super(message);
  }

  protected DomainException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
