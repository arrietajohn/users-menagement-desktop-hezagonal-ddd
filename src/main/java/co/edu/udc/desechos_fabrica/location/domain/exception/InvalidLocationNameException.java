package co.edu.udc.desechos_fabrica.location.domain.exception;

public class InvalidLocationNameException extends RuntimeException {

    private static final String MESSAGE = "The location name must not be empty";
    private static final String MESSAGE_TOO_SHORT = "The location name must have at least %d characters.";

    private InvalidLocationNameException(final String message) {
        super(message);
    }

    public static InvalidLocationNameException becauseValueIsEmpty() {
        return new InvalidLocationNameException(MESSAGE);
    }

    public static InvalidLocationNameException becauseLengthIsTooShort(final int minimumLength) {
        return new InvalidLocationNameException(String.format(MESSAGE_TOO_SHORT, minimumLength));
    }
}
