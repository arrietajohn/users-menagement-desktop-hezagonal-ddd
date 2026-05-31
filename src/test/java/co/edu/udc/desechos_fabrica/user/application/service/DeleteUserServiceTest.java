package co.edu.udc.desechos_fabrica.user.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import co.edu.udc.desechos_fabrica.user.application.port.out.DeleteUserPort;
import co.edu.udc.desechos_fabrica.user.application.port.out.GetUserByEmailPort;
import co.edu.udc.desechos_fabrica.user.application.service.dto.command.DeleteUserCommand;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserStatus;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserNotFoundException;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
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
 * Tests para DeleteUserService.
 *
 * <p>Cubre: flujo feliz (delete invocado), usuario no encontrado y validación del command.
 */
@DisplayName("DeleteUserService")
@ExtendWith(MockitoExtension.class)
class DeleteUserServiceTest {

  @Mock private DeleteUserPort deleteUserPort;
  @Mock private GetUserByEmailPort getUserByEmailPort;

  private DeleteUserService service;

  @BeforeEach
  void setUp() {
    try (final ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
      service =
          new DeleteUserService(deleteUserPort, getUserByEmailPort, validatorFactory.getValidator());
    }
  }

  // ── flujo feliz

  @Test
  @DisplayName("execute() invoca deleteUserPort cuando el usuario existe")
  void shouldDeleteWhenUserExists() {
    // Arrange
    final String validEmail = "user-to-delete@example.com";
    final DeleteUserCommand command = new DeleteUserCommand(validEmail);

    final UserModel existing =
        new UserModel(
            new UserFirstName("John"),
            new UserLastName("Arrieta"),
            new UserEmail(validEmail),
            UserPassword.fromHash("$2a$12$abcdefghijklmnopqrstuO"),
            UserRole.REVIEWER,
            UserStatus.ACTIVE);

    when(getUserByEmailPort.getByEmail(any(UserEmail.class))).thenReturn(Optional.of(existing));

    // Act
    service.execute(command);

    // Assert
    verify(deleteUserPort).delete(new UserEmail(validEmail));
  }

  // ── usuario no encontrado

  @Test
  @DisplayName("execute() lanza UserNotFoundException cuando el id no existe")
  void shouldThrowWhenUserNotFound() {
    // Arrange
    final String nonExistentEmail = "non-existent-user@example.com";
    final DeleteUserCommand command = new DeleteUserCommand(nonExistentEmail);

    when(getUserByEmailPort.getByEmail(any(UserEmail.class))).thenReturn(Optional.empty());

    // Act & Assert
    assertThrows(UserNotFoundException.class, () -> service.execute(command));
    verify(deleteUserPort, never()).delete(any());
  }

  // ── validación del command

  @Test
  @DisplayName("execute() lanza ConstraintViolationException cuando el id está en blanco")
  void shouldThrowWhenCommandIsInvalid() {
    // Arrange
    final DeleteUserCommand command = new DeleteUserCommand("  ");

    // Act & Assert
    assertThrows(ConstraintViolationException.class, () -> service.execute(command));
    verifyNoInteractions(deleteUserPort, getUserByEmailPort);
  }
}
