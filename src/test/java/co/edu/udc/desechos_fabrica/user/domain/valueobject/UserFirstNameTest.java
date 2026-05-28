package co.edu.udc.desechos_fabrica.user.domain.valueobject;

import static org.junit.jupiter.api.Assertions.*;

import co.edu.udc.desechos_fabrica.user.domain.exception.InvalidUserNameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserFirstNameTest {

  // --- Happy Path Tests ---

  @ParameterizedTest
  @ValueSource(strings = {"Miguel", "   Miguel   ", "Miguel \t"})
  @DisplayName("Valida que el nombre tenga al menos 3 caracteres")
  void shouldValidateUserNameMinimumLength(final String userFirstName) {
    // Arrange
    final String correctUserFirstName = "Miguel";
    // Act
    final UserFirstName userFirstNameVo = new UserFirstName(userFirstName);
    // Assert
    assertEquals(correctUserFirstName, userFirstNameVo.value());
  }

  // -- Flujo con excepciones y ramas de validación ---

  @Test
  @DisplayName("Valida que el nombre no sea nulo")
  void shouldValidateUserFirstNameIsNotNull() {
    assertThrows(NullPointerException.class, () -> new UserFirstName(null));
  }

  @ParameterizedTest
  @ValueSource(
      strings = {"", "  ", "\t", "\n", "\r", "\f", "\b", "Jo", "Ty  ", "", "   Cy ", "Ed\t"})
  @DisplayName("Valida que el nombre no sea vacio y tenga un tamaño minimo")
  void shouldValidateUserFirstNameIsNotEmptyAndMinimumLength(final String userFirstName) {
    assertThrows(InvalidUserNameException.class, () -> new UserFirstName(userFirstName));
  }
}
