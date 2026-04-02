package com.jcaa.usersmanagement.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests para EmailDestinationModel.
 *
 * <p>Verifica el camino feliz (todos los getters) y los cuatro puntos de validación del
 * constructor: cada campo tiene su propia llamada a {@code validateNotBlank}, por lo que se
 * necesita un test por campo para alcanzar esa instrucción y ejercer las dos ramas (null y blank).
 */
@DisplayName("EmailDestinationModel")
class EmailDestinationModelTest {

  // ── Arrange
  private static final String EMAIL = "dest@example.com";
  private static final String NAME = "Recipient Name";
  private static final String SUBJECT = "Welcome";
  private static final String BODY = "Hello, welcome to the platform.";

  // ── Happy path

  @Test
  @DisplayName("Constructor debe preservar los cuatro campos cuando los datos son válidos")
  void shouldCreateModelWithAllValidFields() {
    // Arrange + Act
    final EmailDestinationModel model = new EmailDestinationModel(EMAIL, NAME, SUBJECT, BODY);

    // Assert
    assertAll(
        "campos de EmailDestinationModel",
        () -> assertEquals(EMAIL, model.getDestinationEmail(), "destinationEmail"),
        () -> assertEquals(NAME, model.getDestinationName(), "destinationName"),
        () -> assertEquals(SUBJECT, model.getSubject(), "subject"),
        () -> assertEquals(BODY, model.getBody(), "body"));
  }

  // ── Validaciones de campo
  // Cada test llega a un punto de validación distinto en el constructor:
  // campo 1 (destinationEmail), campo 2 (destinationName), etc.

  @Test
  @DisplayName("Constructor debe lanzar NullPointerException cuando destinationEmail es null")
  void shouldThrowNpeWhenDestinationEmailIsNull() {
    // Arrange — campo 1 inválido: falla en la primera llamada a validateNotBlank
    // Act & Assert
    assertThrows(
        NullPointerException.class, () -> new EmailDestinationModel(null, NAME, SUBJECT, BODY));
  }

  @Test
  @DisplayName(
      "Constructor debe lanzar IllegalArgumentException cuando destinationName está en blanco")
  void shouldThrowIaeWhenDestinationNameIsBlank() {
    // Arrange — campo 1 válido, campo 2 en blanco: falla en la segunda llamada
    // Act & Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new EmailDestinationModel(EMAIL, "   ", SUBJECT, BODY));
  }

  @Test
  @DisplayName("Constructor debe lanzar NullPointerException cuando subject es null")
  void shouldThrowNpeWhenSubjectIsNull() {
    // Arrange — campos 1 y 2 válidos, campo 3 null: falla en la tercera llamada
    // Act & Assert
    assertThrows(
        NullPointerException.class, () -> new EmailDestinationModel(EMAIL, NAME, null, BODY));
  }

  @Test
  @DisplayName("Constructor debe lanzar IllegalArgumentException cuando body está vacío")
  void shouldThrowIaeWhenBodyIsEmpty() {
    // Arrange — campos 1, 2 y 3 válidos, campo 4 vacío: falla en la cuarta llamada
    // Act & Assert
    assertThrows(
        IllegalArgumentException.class, () -> new EmailDestinationModel(EMAIL, NAME, SUBJECT, ""));
  }
}
