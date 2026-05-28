package co.edu.udc.desechos_fabrica.user.application.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import co.edu.udc.desechos_fabrica.user.application.port.out.GetAllUsersPort;
import co.edu.udc.desechos_fabrica.user.application.service.GetAllUsersService;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserStatus;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserEmail;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserId;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserName;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserPassword;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests para GetAllUsersService.
 *
 * <p>Cubre: lista con usuarios y lista vacía.
 */
@DisplayName("GetAllUsersService")
@ExtendWith(MockitoExtension.class)
class GetAllUsersServiceTest {

  @Mock private GetAllUsersPort getAllUsersPort;

  private GetAllUsersService service;

  @BeforeEach
  void setUp() {
    service = new GetAllUsersService(getAllUsersPort);
  }

  // ── lista con usuarios

  @Test
  @DisplayName("execute() retorna la lista de usuarios del puerto")
  void shouldReturnUsersFromPort() {
    // Arrange
    final UserModel user =
        new UserModel(
            new UserId("u-001"),
            new UserName("John Arrieta"),
            new UserEmail("john@example.com"),
            UserPassword.fromHash("$2a$12$abcdefghijklmnopqrstuO"),
            UserRole.ADMIN,
            UserStatus.ACTIVE);

    when(getAllUsersPort.getAll()).thenReturn(List.of(user));

    // Act
    final List<UserModel> result = service.execute();

    // Assert
    assertAll(
        "getAll con un usuario",
        () -> assertEquals(1, result.size(), "debe retornar exactamente un usuario"),
        () -> assertSame(user, result.get(0), "debe ser el mismo objeto del puerto"));
  }

  // ── lista vacía

  @Test
  @DisplayName("execute() retorna lista vacía cuando no hay usuarios")
  void shouldReturnEmptyListWhenNoUsers() {
    // Arrange
    when(getAllUsersPort.getAll()).thenReturn(List.of());

    // Act
    final List<UserModel> result = service.execute();

    // Assert
    assertTrue(result.isEmpty(), "debe retornar lista vacía");
  }
}
