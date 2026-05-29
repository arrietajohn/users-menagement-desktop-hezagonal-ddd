package co.edu.udc.desechos_fabrica.user.domain.exception;

import static org.junit.jupiter.api.Assertions.assertTrue;

import co.edu.udc.desechos_fabrica.user.domain.exception.UserNotFoundException;
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
  @DisplayName("becauseEmailWasNotFound() debe incluir el email del usuario en el mensaje de error")
  void shouldIncludeUserEmailInMessage() {
    // Arrange
    final String userEmail = "miguel-jaller@example.com";

    // Act
    final String message = UserNotFoundException.becauseEmailWasNotFound(userEmail).getMessage();

    // Assert
    assertTrue(
        message.contains(userEmail), "el mensaje debe identificar el email del usuario no encontrado");
  }
}
