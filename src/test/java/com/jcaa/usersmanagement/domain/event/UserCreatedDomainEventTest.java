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
 * Tests para UserCreatedDomainEvent.
 *
 * <p>Estrategia: un @Test por cada comportamiento observable del evento (eventName, occurredOn,
 * user, payload). De paso, cada test ejecuta el constructor heredado de DomainEvent, cubriendo la
 * clase abstracta sin necesidad de una clase separada.
 */
@DisplayName("Test de UserCreatedDomainEvent")
class UserCreatedDomainEventTest {

  // ── Arrenge Generales
  private static final String ID = "user-001";
  private static final String NAME = "John Arrieta";
  private static final String EMAIL = "john.arrieta@gmail.com";
  // fromHash() acepta cualquier string no-null: evitamos el coste de BCrypt en tests
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
            UserRole.MEMBER,
            UserStatus.ACTIVE);
  }

  // ── eventName

  @Test
  @DisplayName("eventName() debe retornar la constante 'user.created'")
  void shouldHaveEventNameUserCreated() {
    // Arrange
    final UserCreatedDomainEvent event = new UserCreatedDomainEvent(user);

    // Act
    final String result = event.getEventName();

    // Assert
    assertEquals("user.created", result);
  }

  // ── occurredOn

  @Test
  @DisplayName("occurredOn() no debe ser nulo y debe quedar acotado al instante de construcción")
  void shouldRecordOccurredOnAtCreationTime() {
    // Arrange
    final LocalDateTime before = LocalDateTime.now();
    final UserCreatedDomainEvent event = new UserCreatedDomainEvent(user);
    final LocalDateTime after = LocalDateTime.now();

    // Act
    final LocalDateTime occurredOn = event.getOccurredOn();

    // Assert
    assertNotNull(occurredOn, "occurredOn no debe ser null");
    assertFalse(occurredOn.isBefore(before),
        "occurredOn debe ser >= al instante anterior a la construcción");
    assertFalse(occurredOn.isAfter(after),
        "occurredOn debe ser <= al instante posterior a la construcción");
  }

  // ── user()

  @Test
  @DisplayName("user() debe devolver la misma instancia de UserModel recibida en el constructor")
  void shouldReturnSameUserInstance() {
    // Arrange
    final UserCreatedDomainEvent event = new UserCreatedDomainEvent(user);

    // Act
    final UserModel result = event.getUser();

    // Assert
    assertSame(user, result);
  }

  // ── payload()

  @Test
  @DisplayName("payload() debe contener exactamente los cinco campos del usuario")
  void shouldReturnPayloadWithAllUserFields() {
    // Arrange
    final UserCreatedDomainEvent event = new UserCreatedDomainEvent(user);

    // Act
    final Map<String, String> payload = event.payload();

    // Assert
    assertAll(
        "payload de UserCreatedDomainEvent",
        () -> assertEquals(5,                        payload.size(),           "tamaño del mapa"),
        () -> assertEquals(ID,                       payload.get("id"),        "id"),
        () -> assertEquals(NAME,                     payload.get("name"),      "name"),
        () -> assertEquals(EMAIL,                    payload.get("email"),     "email"),
        () -> assertEquals(UserRole.MEMBER.name(),   payload.get("role"),      "role"),
        () -> assertEquals(UserStatus.ACTIVE.name(), payload.get("status"),    "status"));
  }
}
