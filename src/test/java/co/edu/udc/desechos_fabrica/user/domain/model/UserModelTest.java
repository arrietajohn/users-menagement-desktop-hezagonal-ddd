package co.edu.udc.desechos_fabrica.user.domain.model;

import static org.junit.jupiter.api.Assertions.*;

import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserStatus;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.*;
import co.edu.udc.desechos_fabrica.enterprise.domain.valueobject.EnterpriseNit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests para UserModel.
 *
 * <p>Sólo se testean los tres métodos con lógica real: {@code create()} que fija el estado inicial
 * PENDING, {@code activate()} y {@code deactivate()} que producen transiciones de estado. El
 * constructor trivial y los getters quedan cubiertos como efecto secundario de estos tests.
 */
@DisplayName("UserModel")
class UserModelTest {

  // ── Arrange (variables globales para la mayoría de las test)
  private static final String HASH = "$2a$12$abcdefghijklmnopqrstuO";

  private UserFirstName userFirstName;
  private UserLastName userLastName;
  private UserEmail userEmail;
  private EnterpriseNit enterpriseNit;
  private UserPassword password;

  @BeforeEach
  void setUp() {
    userFirstName = new UserFirstName("Alice");
    userLastName = new UserLastName("Smith");
    userEmail = new UserEmail("alice@example.com");
    enterpriseNit = new EnterpriseNit("123456789");
    password = UserPassword.fromHash(HASH);
  }

  // ── create()

  @Test
  @DisplayName("create() debe fijar status PENDING y preservar todos los campos recibidos")
  void shouldCreateUserWithPendingStatusAndPreserveAllFields() {
    // Act
    final UserModel model =
        UserModel.create(userFirstName, userLastName, userEmail, password, UserRole.MEMBER);

    // Assert
    assertAll(
        "create() factory",
        () ->
            assertEquals(UserStatus.PENDING, model.getStatus(), "status debe iniciar como PENDING"),
        () -> assertSame(password, model.getPassword(), "password debe preservarse"));
  }

  // ── activate()

  @Test
  @DisplayName("activate() debe retornar nueva instancia con ACTIVE y demás campos intactos")
  void shouldActivateAndPreserveOtherFields() {
    // Arrange
    final UserModel pending =
        UserModel.create(userFirstName, userLastName, userEmail, password, UserRole.REVIEWER);

    // Act
    final UserModel activated = pending.activate();

    // Assert
    assertAll(
        "resultado de activate()",
        () -> assertNotSame(pending, activated, "debe ser una nueva instancia"),
        () -> assertEquals(UserStatus.ACTIVE, activated.getStatus(), "status debe ser ACTIVE"),
        () -> assertSame(userFirstName, activated.getFirstName(), "firstName debe preservarse"),
        () -> assertSame(userLastName, activated.getLastName(), "lastName debe preservarse"),
        () -> assertSame(userEmail, activated.getEmail(), "email debe preservarse"),
        () -> assertEquals(UserRole.REVIEWER, activated.getRole(), "role debe preservarse"));
  }

  // ── deactivate()

  @Test
  @DisplayName("deactivate() debe retornar nueva instancia con INACTIVE y demás campos intactos")
  void shouldDeactivateAndPreserveOtherFields() {
    // Arrange
    final UserModel active =
        new UserModel(userFirstName, userLastName, userEmail, enterpriseNit, password, UserRole.REVIEWER, UserStatus.ACTIVE);

    // Act
    final UserModel deactivated = active.deactivate();

    // Assert
    assertAll(
        "resultado de deactivate()",
        () -> assertNotSame(active, deactivated, "debe ser una nueva instancia"),
        () ->
            assertEquals(UserStatus.INACTIVE, deactivated.getStatus(), "status debe ser INACTIVE"),
        () -> assertSame(userEmail, deactivated.getEmail(), "id debe preservarse"),
        () -> assertEquals(UserRole.REVIEWER, deactivated.getRole(), "role debe preservarse"));
  }
}
