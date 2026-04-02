package com.jcaa.usersmanagement.domain.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests para InvalidCredentialsException.
 *
 * <p>Un solo test ejerce AMBAS factories y verifica la regla de negocio real: cada escenario de
 * autenticación fallida debe comunicar una razón distinta. No se necesitan dos tests separados
 * porque la lógica valiosa es la diferencia entre mensajes.
 */
@DisplayName("InvalidCredentialsException")
class InvalidCredentialsExceptionTest {

  @Test
  @DisplayName("Las dos factories deben producir mensajes distintos y no vacíos")
  void shouldProduceDistinctNonBlankMessagesForEachAuthFailureScenario() {
    // Act
    final String invalidCredsMsg =
        InvalidCredentialsException.becauseCredentialsAreInvalid().getMessage();
    final String inactiveUserMsg =
        InvalidCredentialsException.becauseUserIsNotActive().getMessage();

    // Assert
    assertAll(
        "mensajes de autenticación fallida",
        () ->
            assertFalse(
                invalidCredsMsg.isBlank(), "mensaje de credenciales inválidas no debe estar vacío"),
        () ->
            assertFalse(
                inactiveUserMsg.isBlank(), "mensaje de usuario inactivo no debe estar vacío"),
        () ->
            assertNotEquals(
                invalidCredsMsg,
                inactiveUserMsg,
                "cada escenario debe tener su propio mensaje para identificar la causa"));
  }
}
