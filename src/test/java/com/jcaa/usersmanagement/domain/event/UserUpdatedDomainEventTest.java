package com.jcaa.usersmanagement.domain.event;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.domain.enums.UserRole;
import com.jcaa.usersmanagement.domain.enums.UserStatus;
import com.jcaa.usersmanagement.domain.model.UserModel;
import com.jcaa.usersmanagement.domain.valueobject.UserEmail;
import com.jcaa.usersmanagement.domain.valueobject.UserId;
import com.jcaa.usersmanagement.domain.valueobject.UserName;
import com.jcaa.usersmanagement.domain.valueobject.UserPassword;
import java.time.LocalDateTime;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests para UserUpdatedDomainEvent.
 *
 * <p>Misma estructura que UserCreatedDomainEvent pero con el nombre de evento "user.updated". Se
 * usan datos de fixture distintos (ADMIN / INACTIVE) para garantizar que el payload refleja
 * correctamente los datos del usuario actualizado y no confundirlo con los tests de creación.
 */
@DisplayName("UserUpdatedDomainEvent")
class UserUpdatedDomainEventTest {

  // ── Arranges globales
  private static final String ID = "user-003";
  private static final String NAME = "Jane Doe";
  private static final String EMAIL = "jane.doe@example.com";
  // fromHash() acepta cualquier string no-null: evita el coste de BCrypt en tests
  private static final String HASH = "$2a$12$abcdefghijklmnopqrstuO";

  private UserModel user;

  @BeforeEach
  void setUp() {
    user =
        new UserModel(
            new UserId(ID),
            new UserName(NAME),
            new UserEmail(EMAIL),
            UserPassword.fromHash(HASH),
            UserRole.ADMIN,
            UserStatus.INACTIVE);
  }

  // ── eventName

  @Test
  @DisplayName("eventName() debe retornar la constante 'user.updated'")
  void shouldHaveEventNameUserUpdated() {
    // Arrange — el usuario ya está en el @BeforeEach
    final UserUpdatedDomainEvent event = new UserUpdatedDomainEvent(user);

    // Act
    final String result = event.getEventName();

    // Assert
    assertEquals("user.updated", result);
  }

  // ── occurredOn

  @Test
  @DisplayName("occurredOn() no debe ser nulo y debe quedar acotado al instante de construcción")
  void shouldRecordOccurredOnAtCreationTime() {
    // Arrange
    final LocalDateTime before = LocalDateTime.now();
    final UserUpdatedDomainEvent event = new UserUpdatedDomainEvent(user);
    final LocalDateTime after = LocalDateTime.now();

    // Act
    final LocalDateTime occurredOn = event.getOccurredOn();

    // Assert
    assertNotNull(occurredOn, "occurredOn no debe ser null");
    assertFalse(
        occurredOn.isBefore(before),
        "occurredOn debe ser >= al instante anterior a la construcción");
    assertFalse(
        occurredOn.isAfter(after),
        "occurredOn debe ser <= al instante posterior a la construcción");
  }

  // ── user()

  @Test
  @DisplayName("user() debe devolver la misma instancia de UserModel recibida en el constructor")
  void shouldReturnSameUserInstance() {
    // Arrange
    final UserUpdatedDomainEvent event = new UserUpdatedDomainEvent(user);

    // Act
    final UserModel result = event.getUser();

    // Assert
    assertSame(user, result);
  }

  // ── payload()

  @Test
  @DisplayName("payload() debe contener exactamente los cinco campos del usuario actualizado")
  void shouldReturnPayloadWithAllUserFields() {
    // Arrange
    final UserUpdatedDomainEvent event = new UserUpdatedDomainEvent(user);

    // Act
    final Map<String, String> payload = event.payload();

    // Assert
    assertAll(
        "payload de UserUpdatedDomainEvent",
        () -> assertEquals(5, payload.size(), "tamaño del mapa"),
        () -> assertEquals(ID, payload.get("id"), "id"),
        () -> assertEquals(NAME, payload.get("name"), "name"),
        () -> assertEquals(EMAIL, payload.get("email"), "email"),
        () -> assertEquals(UserRole.ADMIN.name(), payload.get("role"), "role"),
        () -> assertEquals(UserStatus.INACTIVE.name(), payload.get("status"), "status"));
  }
}
