package com.jcaa.usersmanagement.domain.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.domain.exception.InvalidUserEmailException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserEmailTest {

  @Test
  @DisplayName("Normaliza email con trim y lowercase")
  void shouldNormalizeEmail() {
    // Arrange
    final String correctEmail = "john.arrieta@gmail.com";
    final String email = "  john.arrieta@gmail.com  ";

    // Act
    final UserEmail userEmail = new UserEmail(email);

    // Assert
    assertEquals(correctEmail, userEmail.value());
  }

  @Test
  @DisplayName("Valida que el email no este vacio")
  void shouldValidateEmailIsNotEmpty() {
    // Arrange
    final String email = "   ";

    // Act & Assert
    assertThrows(InvalidUserEmailException.class, () -> new UserEmail(email));
  }

  @Test
  @DisplayName("Valida que el email tenga un formato correcto")
  void shouldValidateEmailFormat() {
    // Arrange
    final String email = "johnarroeta-arroba-gmail.com";
    // Act y Assert
    assertThrows(InvalidUserEmailException.class, () -> new UserEmail(email));
  }

  @Test
  @DisplayName("Valida que el email creado sea igual al generado en formato string")
  void shouldValidateEmailToString() {
    // Arrange
    final String email = "john.arrieta@gmail.com";
    // Act & Assert
    assertEquals(email, new UserEmail(email).toString());
  }

  @Test
  @DisplayName("Valida que el email no sea nulo")
  void shouldValidateEmailIsNotNull() {
    // Act & Assert
    assertThrows(NullPointerException.class, () -> new UserEmail(null));
  }

  // ------ Test con parameters --------

  @ParameterizedTest
  @ValueSource(
      strings = {
        "john.arrieta@gmail.com",
        "john-arrieta_arreita@gmail.com.co",
        "john1234567arreita@gmail.com"
      })
  @DisplayName("Valida que el email tenga un formato correcto con diferentes casos")
  void shouldValidateEmailFormatWithParameters(String email) {
    // Act & Assert
    assertDoesNotThrow(() -> new UserEmail(email));
  }

  @ParameterizedTest
  @ValueSource(
      strings = {
        "",
        "johnarroetaarroba-gmail.com",
        "john.arrieta@gmail",
        "john.arrieta@.com",
        "john arrieta@com"
      })
  @DisplayName("Valida que el email tenga un formato incorrecto con diferentes casos")
  void shouldValidateEmailFormatWithInvalidParameters(String email) {
    // Act & Assert
    assertThrows(InvalidUserEmailException.class, () -> new UserEmail(email));
  }
}
