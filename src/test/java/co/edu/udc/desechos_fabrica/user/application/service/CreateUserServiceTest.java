package co.edu.udc.desechos_fabrica.user.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import co.edu.udc.desechos_fabrica.user.application.port.out.GetUserByEmailPort;
import co.edu.udc.desechos_fabrica.user.application.port.out.SaveUserPort;
import co.edu.udc.desechos_fabrica.user.application.service.CreateUserService;
import co.edu.udc.desechos_fabrica.user.application.service.EmailNotificationService;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.CreateUserCommand;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserStatus;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserAlreadyExistsException;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserEmail;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserId;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserName;
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
 * Tests para CreateUserService.
 *
 * <p>Cubre: validación del command, email duplicado y flujo feliz (save + notificación).
 */
@DisplayName("CreateUserService")
@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {

  @Mock private SaveUserPort saveUserPort;
  @Mock private GetUserByEmailPort getUserByEmailPort;
  @Mock private EmailNotificationService emailNotificationService;

  private CreateUserService service;

  @BeforeEach
  void setUp() {
    try (final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
      service =
          new CreateUserService(
              saveUserPort,
              getUserByEmailPort,
              emailNotificationService,
              validatorFactory.getValidator());
    }
  }

  // ── flujo feliz

  @Test
  @DisplayName("execute() guarda el usuario y envía notificación cuando el email no existe")
  void shouldSaveUserAndNotifyWhenEmailIsNew() {
    // Arrange
    final CreateUserCommand command =
        new CreateUserCommand("u-01", "John Arrieta", "john@example.com", "Pass1234", "ADMIN");

    final UserModel savedUser =
        new UserModel(
            new UserId("u-01"),
            new UserName("John Arrieta"),
            new UserEmail("john@example.com"),
            UserPassword.fromPlainText("Pass1234"),
            UserRole.ADMIN,
            UserStatus.PENDING);

    when(getUserByEmailPort.getByEmail(any())).thenReturn(Optional.empty());
    when(saveUserPort.save(any())).thenReturn(savedUser);

    // Act
    final UserModel result = service.execute(command);

    // Assert
    assertAll(
        "flujo feliz de CreateUserService",
        () -> assertNotNull(result, "resultado no debe ser null"),
        () -> assertEquals("u-01", result.getId().value(), "id del usuario guardado"));

    verify(saveUserPort).save(any(UserModel.class));
    verify(emailNotificationService).notifyUserCreated(savedUser, "Pass1234");
  }

  // ── email duplicado

  @Test
  @DisplayName("execute() lanza UserAlreadyExistsException cuando el email ya está registrado")
  void shouldThrowWhenEmailAlreadyExists() {
    // Arrange
    final CreateUserCommand command =
        new CreateUserCommand("u-02", "Jane Doe", "jane@example.com", "Pass5678", "MEMBER");

    final UserModel existing =
        new UserModel(
            new UserId("u-99"),
            new UserName("Jane Doe"),
            new UserEmail("jane@example.com"),
            UserPassword.fromPlainText("OtraPass1"),
            UserRole.MEMBER,
            UserStatus.ACTIVE);

    when(getUserByEmailPort.getByEmail(any())).thenReturn(Optional.of(existing));

    // Act & Assert
    assertThrows(UserAlreadyExistsException.class, () -> service.execute(command));
    verify(saveUserPort, never()).save(any());
    verify(emailNotificationService, never()).notifyUserCreated(any(), any());
  }

  // ── validación del command

  @Test
  @DisplayName(
      "execute() lanza ConstraintViolationException cuando el command tiene campos inválidos")
  void shouldThrowWhenCommandIsInvalid() {
    // Arrange — id en blanco y email inválido
    final CreateUserCommand command =
        new CreateUserCommand("", "Jo", "not-an-email", "short", "ADMIN");

    // Act & Assert
    assertThrows(ConstraintViolationException.class, () -> service.execute(command));
    verifyNoInteractions(saveUserPort, getUserByEmailPort, emailNotificationService);
  }
}
