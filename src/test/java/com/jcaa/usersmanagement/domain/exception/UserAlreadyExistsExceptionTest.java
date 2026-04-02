package com.jcaa.usersmanagement.domain.exception;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests para UserAlreadyExistsException.
 *
 * <p>La excepción no tiene lógica de dominio propia; su valor está en el mensaje que produce: el
 * email duplicado debe aparecer explícitamente para que la capa superior pueda construir una
 * respuesta de error útil. Estos tests existirán hasta que la capa de aplicación sea desarrollada y
 * la ejerza como efecto secundario de sus propios tests.
 */
@DisplayName("UserAlreadyExistsException")
class UserAlreadyExistsExceptionTest {

  @Test
  @DisplayName("becauseEmailAlreadyExists() debe incluir el email en el mensaje de error")
  void shouldIncludeEmailInMessage() {
    // Arrange
    final String email = "existing@example.com";

    // Act
    final String message = UserAlreadyExistsException.becauseEmailAlreadyExists(email).getMessage();

    // Assert
    assertTrue(message.contains(email), "el mensaje debe identificar el email duplicado");
  }
}
