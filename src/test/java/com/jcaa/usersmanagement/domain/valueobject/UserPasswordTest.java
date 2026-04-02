package com.jcaa.usersmanagement.domain.valueobject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import com.jcaa.usersmanagement.domain.exception.InvalidUserPasswordException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserPasswordTest {

  @ParameterizedTest
  @ValueSource(strings = {"password123", "   password123   "})
  @DisplayName("Valida que el password se normalice con trim y se aplique hash")
  void shouldNormalizeAndHashPassword(final String input) {
    // Act
    final UserPassword result = UserPassword.fromPlainText(input);
    // Assert — verifica que se creó un hash (no el texto plano)
    assertNotNull(result.value());
    assertNotEquals(input.trim(), result.value());
  }

  @ParameterizedTest
  @ValueSource(strings = {"clave", "    clave     "})
  @DisplayName("Valida que el password no tenga menos de 8 caracteres después normalizarlo")
  void shouldFailWhenPasswordIsTooShort(final String password) {
    // Act & Assert
    assertThrows(InvalidUserPasswordException.class, () -> UserPassword.fromPlainText(password));
  }

  @ParameterizedTest
  @ValueSource(strings = {"", "  ", "\r", "\t", "\n", "\f", "\b", "\0"})
  @DisplayName("Valida que el password no sea vacio o solo espacios en blanco")
  void shouldThrowWhenPasswordIsEmptyOrBlank(final String password) {
    // Act & Assert
    assertThrows(InvalidUserPasswordException.class, () -> UserPassword.fromPlainText(password));
  }

  @Test
  @DisplayName("Valida que el password no sea null")
  void shouldThrowWhenPasswordIsNull() {
    // Act & Assert
    assertThrows(NullPointerException.class, () -> UserPassword.fromPlainText(null));
  }

  @Test
  @DisplayName("Valida que el hash del password pueda ser verificado con el texto plano original")
  void shouldVerifyPlainPassword() {
    // Arrange
    final String plainPassword = "mySecurePassword";
    // Act
    final UserPassword userPassword = UserPassword.fromPlainText(plainPassword);
    // Assert
    assertTrue(userPassword.verifyPlain(plainPassword));
  }

  @Test
  @DisplayName("Debería crear un UserPassword desde un hash y ser igual al original")
  void shouldCreateUserPasswordFromExistingHash() {
    // Arrange
    final String rawPassword = "Abcde1234567";
    final UserPassword originalUserPassword = UserPassword.fromPlainText(rawPassword);
    final String generatedHash = originalUserPassword.value();
    // Act
    final UserPassword fromHashUserPassword = UserPassword.fromHash(generatedHash);
    assertEquals(
        originalUserPassword,
        fromHashUserPassword,
        "Los objetos UserPassword deberían ser iguales al usar el mismo hash");
    assertTrue(
        fromHashUserPassword.verifyPlain(rawPassword),
        "El objeto creado desde el hash debería poder verificar el password en texto plano");
  }

  @Test
  @DisplayName("equals: retorna false cuando el argumento no es instancia de UserPassword")
  void shouldReturnFalseWhenOtherIsNotInstanceOfUserPassword() {
    // Arrange & Act
    final UserPassword password = UserPassword.fromPlainText("MiPassword123");
    final Object nonUserPassword = mock(Object.class);
    // Assert
    assertNotEquals(password, nonUserPassword);
  }

  @Test
  @DisplayName("equals: hash distinto retorna false")
  void shouldReturnFalseWhenDifferentHash() {
    // Arrange & Act
    final UserPassword a = UserPassword.fromPlainText("MiPassword123");
    final UserPassword b = UserPassword.fromPlainText("OtroPassword456");
    // Assert
    assertNotEquals(a, b);
  }

  @Test
  @DisplayName("hashCode: consistente para la misma instancia")
  void shouldReturnConsistentHashCode() {
    // Arrange & Act
    UserPassword password = UserPassword.fromPlainText("MiPassword123");
    //  Act
    final int firstHashCode = password.hashCode();
    final int secondHashCode = password.hashCode();
    // Assert
    assertEquals(firstHashCode, secondHashCode);
  }

  @Test
  @DisplayName("hashCode: objetos iguales tienen el mismo hashCode — contrato equals/hashCode")
  void shouldHaveSameHashCodeWhenEqual() {
    // Arrange & Act
    final UserPassword a = UserPassword.fromPlainText("MiPassword123");
    final UserPassword b = UserPassword.fromHash(a.value()); // mismo hash => equals true
    // Assert
    assertEquals(a, b);
    assertEquals(a.hashCode(), b.hashCode());
  }
}
