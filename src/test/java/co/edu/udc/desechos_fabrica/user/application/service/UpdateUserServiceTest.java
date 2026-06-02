package co.edu.udc.desechos_fabrica.user.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import co.edu.udc.desechos_fabrica.enterprise.domain.valueobject.EnterpriseNit;
import co.edu.udc.desechos_fabrica.user.application.port.out.GetUserByEmailPort;
import co.edu.udc.desechos_fabrica.user.application.port.out.UpdateUserPort;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.UpdateUserCommand;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserStatus;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserAlreadyExistsException;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserNotFoundException;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import co.edu.udc.desechos_fabrica.user.domain.service.UserRoleManager;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserEmail;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserFirstName;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserLastName;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserPassword;
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
  @Mock private GetUserByEmailPort getUserByEmailPort;
  @Mock private EmailNotificationService emailNotificationService;
  @Mock private UserRoleManager userRoleManager;

  private UpdateUserService service;

  private static final String ACTOR_EMAIL = "admin@ecoresiduos.com";
  private static final String EMAIL = "john@example.com";
  private static final String HASH = "$2a$12$abcdefghijklmnopqrstuO";

  private UserModel existingUser;
  private UserModel actorUser;

  @BeforeEach
  void setUp() {
    try (final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
      service =
          new UpdateUserService(
              updateUserPort,
              getUserByEmailPort,
              emailNotificationService,
              validatorFactory.getValidator(),
              userRoleManager);
    }

    existingUser =
        new UserModel(
            new UserFirstName("John"),
            new UserLastName("Arrieta"),
            new UserEmail(EMAIL),
            new EnterpriseNit("123456789"),
            UserPassword.fromHash(HASH),
            UserRole.MEMBER,
            UserStatus.ACTIVE);

    actorUser =
        new UserModel(
            new UserFirstName("Admin"),
            new UserLastName("User"),
            new UserEmail(ACTOR_EMAIL),
            new EnterpriseNit("999999999"),
            UserPassword.fromHash(HASH),
            UserRole.ADMIN,
            UserStatus.ACTIVE);
  }

  // ── flujo feliz

  @Test
  @DisplayName("execute() actualiza el usuario y envía notificación cuando los datos son válidos")
  void shouldUpdateUserAndNotifyWhenDataIsValid() {
    // Arrange
    final String newEmail = "new.john@example.com";
    final UpdateUserCommand command =
        new UpdateUserCommand(ACTOR_EMAIL, EMAIL,"John", "Updated", newEmail, null, UserRole.ADMIN.name(), UserStatus.ACTIVE.name(), "123456789");

    when(getUserByEmailPort.getByEmail(new UserEmail(ACTOR_EMAIL))).thenReturn(Optional.of(actorUser));
    when(getUserByEmailPort.getByEmail(new UserEmail(EMAIL))).thenReturn(Optional.of(existingUser));
    when(getUserByEmailPort.getByEmail(new UserEmail(newEmail))).thenReturn(Optional.empty()); // El nuevo email no debe existir
    when(updateUserPort.update(any(), any())).thenAnswer(invocation -> invocation.getArgument(1));

    // Act
    final UserModel result = service.execute(command);

    // Assert
    assertNotNull(result);
    assertEquals(newEmail, result.getEmail().value());
    verify(updateUserPort).update(eq(new UserEmail(EMAIL)), any(UserModel.class));
    verify(emailNotificationService).notifyUserUpdated(any(UserModel.class));
  }

  // ── usuario no encontrado

  @Test
  @DisplayName("execute() lanza UserNotFoundException cuando el email no existe")
  void shouldThrowWhenUserNotFound() {
    // Arrange
    final String nonExistentEmail = "no-existe@example.com";
    final UpdateUserCommand command =
        new UpdateUserCommand(ACTOR_EMAIL, nonExistentEmail,"firstName", "lastName", "new@example.com", null, UserRole.ADMIN.name(), UserStatus.ACTIVE.name(), "");

    when(getUserByEmailPort.getByEmail(new UserEmail(ACTOR_EMAIL))).thenReturn(Optional.of(actorUser));
    when(getUserByEmailPort.getByEmail(new UserEmail(nonExistentEmail))).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(UserNotFoundException.class, () -> service.execute(command));
    verify(updateUserPort, never()).update(any(), any());
  }

  // ── email tomado por otro usuario

  @Test
  @DisplayName("execute() lanza UserAlreadyExistsException cuando el email pertenece a otro usuario")
  void shouldThrowWhenEmailBelongsToAnotherUser() {
    // Arrange
    final String newEmail = "other@example.com";
    final UpdateUserCommand command =
        new UpdateUserCommand(ACTOR_EMAIL, EMAIL, "John", "Arrieta", newEmail, null, UserRole.ADMIN.name(), UserStatus.ACTIVE.name(), "123456789");

    final UserModel otherUser =
        new UserModel(
            new UserFirstName("Other"),
            new UserLastName("User"),
            new UserEmail(newEmail),
            new EnterpriseNit("987654321"),
            UserPassword.fromHash(HASH),
            UserRole.MEMBER,
            UserStatus.ACTIVE);

    when(getUserByEmailPort.getByEmail(new UserEmail(ACTOR_EMAIL))).thenReturn(Optional.of(actorUser));
    when(getUserByEmailPort.getByEmail(new UserEmail(EMAIL))).thenReturn(Optional.of(existingUser));
    when(getUserByEmailPort.getByEmail(new UserEmail(newEmail))).thenReturn(Optional.of(otherUser));

    // Act & Assert
    assertThrows(UserAlreadyExistsException.class, () -> service.execute(command));
    verify(updateUserPort, never()).update(any(), any());
  }

  @Test
  @DisplayName("execute() permite mantener el mismo email del propio usuario")
  void shouldAllowKeepingOwnEmail() {
    // Arrange
    final UpdateUserCommand command =
        new UpdateUserCommand(ACTOR_EMAIL, EMAIL, "John", "Updated", EMAIL, null, UserRole.ADMIN.name(), UserStatus.ACTIVE.name(), "123456789");

    when(getUserByEmailPort.getByEmail(new UserEmail(ACTOR_EMAIL))).thenReturn(Optional.of(actorUser));
    when(getUserByEmailPort.getByEmail(new UserEmail(EMAIL))).thenReturn(Optional.of(existingUser));
    when(updateUserPort.update(any(UserEmail.class), any(UserModel.class))).thenAnswer(invocation -> invocation.getArgument(1));

    // Act & Assert
    assertDoesNotThrow(() -> service.execute(command));
    verify(updateUserPort).update(eq(new UserEmail(EMAIL)), any(UserModel.class));
  }

  @Test
  @DisplayName("execute() lanza ConstraintViolationException cuando el command tiene campos inválidos")
  void shouldThrowWhenCommandIsInvalid() {
    // Arrange
    final UpdateUserCommand command =
        new UpdateUserCommand(ACTOR_EMAIL,"", "", "Jo", "no-es-email", null, UserRole.ADMIN.name(), UserStatus.ACTIVE.name(),"12345");

    // Act & Assert
    assertThrows(ConstraintViolationException.class, () -> service.execute(command));
    verifyNoInteractions(updateUserPort, getUserByEmailPort, emailNotificationService);
  }
}
