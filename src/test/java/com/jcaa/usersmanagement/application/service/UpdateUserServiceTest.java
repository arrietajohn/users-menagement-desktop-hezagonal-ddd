package com.jcaa.usersmanagement.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.jcaa.usersmanagement.application.port.out.GetUserByEmailPort;
import com.jcaa.usersmanagement.application.port.out.GetUserByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateUserPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateUserCommand;
import com.jcaa.usersmanagement.domain.enums.UserRole;
import com.jcaa.usersmanagement.domain.enums.UserStatus;
import com.jcaa.usersmanagement.domain.exception.UserAlreadyExistsException;
import com.jcaa.usersmanagement.domain.exception.UserNotFoundException;
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
 * Tests para UpdateUserService.
 *
 * <p>Cubre: flujo feliz, usuario no encontrado, email tomado por otro usuario, email del mismo
 * usuario (no debe fallar) y validación del command.
 */
@DisplayName("UpdateUserService")
@ExtendWith(MockitoExtension.class)
class UpdateUserServiceTest {

  @Mock private UpdateUserPort updateUserPort;
  @Mock private GetUserByIdPort getUserByIdPort;
  @Mock private GetUserByEmailPort getUserByEmailPort;
  @Mock private EmailNotificationService emailNotificationService;

  private UpdateUserService service;

  private static final String ID = "u-001";
  private static final String EMAIL = "john@example.com";
  private static final String HASH = "$2a$12$abcdefghijklmnopqrstuO";

  private UserModel existingUser;

  @BeforeEach
  void setUp() {
    try (final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
      service =
          new UpdateUserService(
              updateUserPort,
              getUserByIdPort,
              getUserByEmailPort,
              emailNotificationService,
              validatorFactory.getValidator());
    }

    existingUser =
        new UserModel(
            new UserId(ID),
            new UserName("John Arrieta"),
            new UserEmail(EMAIL),
            UserPassword.fromHash(HASH),
            UserRole.MEMBER,
            UserStatus.ACTIVE);
  }

  // ── flujo feliz

  @Test
  @DisplayName("execute() actualiza el usuario y envía notificación cuando los datos son válidos")
  void shouldUpdateUserAndNotifyWhenDataIsValid() {
    // Arrange
    final UpdateUserCommand command =
        new UpdateUserCommand(ID, "John Updated", EMAIL, null, "ADMIN", "ACTIVE");

    when(getUserByIdPort.getById(any())).thenReturn(Optional.of(existingUser));
    when(getUserByEmailPort.getByEmail(any())).thenReturn(Optional.of(existingUser));
    when(updateUserPort.update(any())).thenReturn(existingUser);

    // Act
    final UserModel result = service.execute(command);

    // Assert
    assertNotNull(result);
    verify(updateUserPort).update(any(UserModel.class));
    verify(emailNotificationService).notifyUserUpdated(existingUser);
  }

  // ── usuario no encontrado

  @Test
  @DisplayName("execute() lanza UserNotFoundException cuando el id no existe")
  void shouldThrowWhenUserNotFound() {
    // Arrange
    final UpdateUserCommand command =
        new UpdateUserCommand("no-existe", "Name", "a@b.com", null, "MEMBER", "ACTIVE");

    when(getUserByIdPort.getById(any())).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(UserNotFoundException.class, () -> service.execute(command));
    verify(updateUserPort, never()).update(any());
  }

  // ── email tomado por otro usuario

  @Test
  @DisplayName(
      "execute() lanza UserAlreadyExistsException cuando el email pertenece a otro usuario")
  void shouldThrowWhenEmailBelongsToAnotherUser() {
    // Arrange
    final UpdateUserCommand command =
        new UpdateUserCommand(ID, "John", "other@example.com", null, "MEMBER", "ACTIVE");

    final UserModel otherUser =
        new UserModel(
            new UserId("u-999"),
            new UserName("Other User"),
            new UserEmail("other@example.com"),
            UserPassword.fromHash(HASH),
            UserRole.MEMBER,
            UserStatus.ACTIVE);

    when(getUserByIdPort.getById(any())).thenReturn(Optional.of(existingUser));
    when(getUserByEmailPort.getByEmail(any())).thenReturn(Optional.of(otherUser));

    // Act & Assert
    assertThrows(UserAlreadyExistsException.class, () -> service.execute(command));
    verify(updateUserPort, never()).update(any());
  }

  // ── email del mismo usuario: no debe lanzar excepción

  @Test
  @DisplayName("execute() permite mantener el mismo email del propio usuario")
  void shouldAllowKeepingOwnEmail() {
    // Arrange
    final UpdateUserCommand command =
        new UpdateUserCommand(ID, "John Updated", EMAIL, null, "ADMIN", "ACTIVE");

    when(getUserByIdPort.getById(any())).thenReturn(Optional.of(existingUser));
    when(getUserByEmailPort.getByEmail(any())).thenReturn(Optional.of(existingUser));
    when(updateUserPort.update(any())).thenReturn(existingUser);

    // Act & Assert
    assertDoesNotThrow(() -> service.execute(command));
    verify(updateUserPort).update(any());
  }

  // ── validación del command

  @Test
  @DisplayName(
      "execute() lanza ConstraintViolationException cuando el command tiene campos inválidos")
  void shouldThrowWhenCommandIsInvalid() {
    // Arrange — id en blanco y email inválido
    final UpdateUserCommand command =
        new UpdateUserCommand("", "Jo", "no-es-email", null, "MEMBER", "ACTIVE");

    // Act & Assert
    assertThrows(ConstraintViolationException.class, () -> service.execute(command));
    verifyNoInteractions(updateUserPort, getUserByIdPort, getUserByEmailPort);
  }
}
