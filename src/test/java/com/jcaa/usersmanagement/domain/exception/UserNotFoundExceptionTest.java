package com.jcaa.usersmanagement.domain.exception;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests para UserNotFoundException.
 *
 * <p>La excepción no tiene lógica de dominio propia; su valor está en el mensaje que produce: el id
 * del usuario no encontrado debe aparecer explícitamente para que la capa superior pueda construir
 * una respuesta de error útil. Estos tests existirán hasta que la capa de aplicación sea
 * desarrollada y la ejerza como efecto secundario de sus propios tests.
 */
@DisplayName("UserNotFoundException")
class UserNotFoundExceptionTest {

  @Test
  @DisplayName("becauseIdWasNotFound() debe incluir el id del usuario en el mensaje de error")
  void shouldIncludeUserIdInMessage() {
    // Arrange
    final String userId = "user-404";

    // Act
    final String message = UserNotFoundException.becauseIdWasNotFound(userId).getMessage();

    // Assert
    assertTrue(
        message.contains(userId), "el mensaje debe identificar el id del usuario no encontrado");
  }
}
