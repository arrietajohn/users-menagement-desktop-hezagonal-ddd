package com.jcaa.usersmanagement.domain.valueobject;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.jcaa.usersmanagement.domain.exception.InvalidUserPasswordException;
import java.util.Objects;

public final class UserPassword {

  private static final int MINIMUM_LENGTH = 8;
  private static final int BCRYPT_COST = 12;

  private final String value;

  private UserPassword(final String value) {
    this.value = value;
  }

  /**
   * Crea un UserPassword desde texto plano: valida y aplica hash BCrypt. Usar cuando el usuario
   * crea o cambia su contraseña.
   */
  public static UserPassword fromPlainText(final String plainText) {
    final String normalizedValue =
        Objects.requireNonNull(plainText, "Password cannot be null").trim();
    validateNotEmpty(normalizedValue);
    validateMinimumLength(normalizedValue);
    final String hash =
        BCrypt.withDefaults().hashToString(BCRYPT_COST, normalizedValue.toCharArray());
    return new UserPassword(hash);
  }

  /**
   * Crea un UserPassword desde un hash ya almacenado en base de datos. No re-valida ni re-hashea.
   */
  public static UserPassword fromHash(final String hash) {
    Objects.requireNonNull(hash, "Password hash cannot be null");
    return new UserPassword(hash);
  }


  /** Verifica un texto plano contra el hash BCrypt almacenado. */
  public boolean verifyPlain(final String plainText) {
    final String normalizedPlain =
        Objects.requireNonNull(plainText, "Plain password cannot be null").trim();
    final BCrypt.Result result = BCrypt.verifyer().verify(normalizedPlain.toCharArray(), value);
    return result.verified;
  }

  public String value() {
    return value;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof UserPassword userPassword)) return false; // NOSONAR: rama instanceof no testeable sin warnings
    return Objects.equals(value, userPassword.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
  private static void validateNotEmpty(final String normalizedValue) {
    if (normalizedValue.isEmpty()) {
      throw InvalidUserPasswordException.becauseValueIsEmpty();
    }
  }

  private static void validateMinimumLength(final String normalizedValue) {
    if (normalizedValue.length() < MINIMUM_LENGTH) {
      throw InvalidUserPasswordException.becauseLengthIsTooShort(MINIMUM_LENGTH);
    }
  }

}
