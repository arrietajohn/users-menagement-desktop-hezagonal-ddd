package com.jcaa.usersmanagement.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.application.port.out.GetUserByEmailPort;
import com.jcaa.usersmanagement.application.service.dto.command.LoginCommand;
import com.jcaa.usersmanagement.domain.enums.UserRole;
import com.jcaa.usersmanagement.domain.enums.UserStatus;
import com.jcaa.usersmanagement.domain.exception.InvalidCredentialsException;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.UserEmail;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import com.jcaa.usersmanagement.domain.valueobject.UserName;
import com.jcaa.usersmanagement.domain.valueobject.UserPassword;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests para LoginService.
 *
 * <p>Cubre: login exitoso, email no encontrado, contraseña incorrecta, usuario inactivo/bloqueado y
 * validación del command.
 */
@DisplayName("LoginService")
@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

  @Mock private GetUserByEmailPort getUserByEmailPort;

  private LoginService service;

  private static final String EMAIL = "john@example.com";
  private static final String PASSWORD = "SecurePass1";

  @BeforeEach
  void setUp() {
    try (final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
      service = new LoginService(getUserByEmailPort, validatorFactory.getValidator());
    }
  }

  // ── flujo feliz

  @Test
  @DisplayName("execute() retorna el usuario cuando las credenciales son correctas y está activo")
  void shouldReturnUserWhenCredentialsAreValidAndUserIsActive() {
    // Arrange
    final LoginCommand command = new LoginCommand(EMAIL, PASSWORD);

    final UserModel activeUser =
        new UserModel(
            new UserId("u-001"),
            new UserName("John Arrieta"),
            new UserEmail(EMAIL),
            UserPassword.fromPlainText(PASSWORD),
            UserRole.ADMIN,
            UserStatus.ACTIVE);

    when(getUserByEmailPort.getByEmail(any())).thenReturn(Optional.of(activeUser));

    // Act
    final UserModel result = service.execute(command);

    // Assert
    assertSame(activeUser, result, "debe retornar el usuario autenticado");
  }

  // ── email no registrado

  @Test
  @DisplayName("execute() lanza InvalidCredentialsException cuando el email no existe")
  void shouldThrowWhenEmailNotFound() {
    // Arrange
    final LoginCommand command = new LoginCommand(EMAIL, PASSWORD);

    when(getUserByEmailPort.getByEmail(any())).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(InvalidCredentialsException.class, () -> service.execute(command));
  }

  // ── contraseña incorrecta

  @Test
  @DisplayName("execute() lanza InvalidCredentialsException cuando la contraseña no coincide")
  void shouldThrowWhenPasswordIsWrong() {
    // Arrange
    final LoginCommand command = new LoginCommand(EMAIL, "WrongPass99");

    final UserModel user =
        new UserModel(
            new UserId("u-001"),
            new UserName("John Arrieta"),
            new UserEmail(EMAIL),
            UserPassword.fromPlainText(PASSWORD),
            UserRole.MEMBER,
            UserStatus.ACTIVE);

    when(getUserByEmailPort.getByEmail(any())).thenReturn(Optional.of(user));

    // Act & Assert
    assertThrows(InvalidCredentialsException.class, () -> service.execute(command));
  }

  // ── usuario no activo (PENDING, INACTIVE, BLOCKED)

  @Test
  @DisplayName("execute() lanza InvalidCredentialsException cuando el usuario no está ACTIVE")
  void shouldThrowWhenUserIsNotActive() {
    // Arrange
    final LoginCommand command = new LoginCommand(EMAIL, PASSWORD);

    final UserModel pendingUser =
        new UserModel(
            new UserId("u-001"),
            new UserName("John Arrieta"),
            new UserEmail(EMAIL),
            UserPassword.fromPlainText(PASSWORD),
            UserRole.MEMBER,
            UserStatus.PENDING);

    when(getUserByEmailPort.getByEmail(any())).thenReturn(Optional.of(pendingUser));

    // Act & Assert
    assertThrows(InvalidCredentialsException.class, () -> service.execute(command));
  }

  // ── validación del command

  @Test
  @DisplayName(
      "execute() lanza ConstraintViolationException cuando el command tiene campos inválidos")
  void shouldThrowWhenCommandIsInvalid() {
    // Arrange — email inválido y password muy corto
    final LoginCommand command = new LoginCommand("no-es-email", "short");

    // Act & Assert
    assertThrows(ConstraintViolationException.class, () -> service.execute(command));
    verifyNoInteractions(getUserByEmailPort);
  }
}
