package co.edu.udc.desechos_fabrica.user.domain.valueobject;

import co.edu.udc.desechos_fabrica.user.domain.exception.InvalidUserNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserLastNameTest {

  // --- Happy Path Tests ---

  @ParameterizedTest
  @ValueSource(strings = {"Jaller", "   Jaller   ", "Jaller \t"})
  @DisplayName("Valida que el apellido tenga al menos 3 caracteres")
  void shouldValidateUserLastNameMinimumLength(final String userLastName) {
    // Arrange
    final String correctUserLastName = "Jaller";
    // Act
    final UserLastName userLastNameVo = new UserLastName(userLastName);
    // Assert
    assertEquals(correctUserLastName, userLastNameVo.value());
  }

  // -- Flujo con excepciones y ramas de validación ---

  @Test
  @DisplayName("Valida que el apellido no sea nulo")
  void shouldValidateUserLastNameIsNotNull() {
    assertThrows(NullPointerException.class, () -> new UserLastName(null));
  }

  @ParameterizedTest
  @ValueSource(
      strings = {"", "  ", "\t", "\n", "\r", "\f", "\b", "Jo", "Ty  ", "", "   Cy ", "Ed\t"})
  @DisplayName("Valida que el apellido no sea vacio y tenga un tamaño minimo")
  void shouldValidateUserLastNameIsNotEmptyAndMinimumLength(final String userLastName) {
    assertThrows(InvalidUserNameException.class, () -> new UserLastName(userLastName));
  }
}
