package co.edu.udc.desechos_fabrica.user.domain.valueobject;

import co.edu.udc.desechos_fabrica.user.domain.exception.InvalidUserNameException;
import java.util.Objects;

public record UserLastName(String value) {

  private static final int MINIMUM_LENGTH = 3;

  public UserLastName {
    final String normalizedValue = Objects.requireNonNull(value, "user last name cannot be null").trim();
    validateNotEmpty(normalizedValue);
    validateMinimumLength(normalizedValue);
    value = normalizedValue;
  }

  private static void validateNotEmpty(final String normalizedValue) {
    if (normalizedValue.isEmpty()) {
      throw InvalidUserNameException.becauseValueIsEmpty();
    }
  }

  private static void validateMinimumLength(final String normalizedValue) {
    if (normalizedValue.length() < MINIMUM_LENGTH) {
      throw InvalidUserNameException.becauseLengthIsTooShort(MINIMUM_LENGTH);
    }
  }

  @Override
  public String toString() {
    return value;
  }
}
