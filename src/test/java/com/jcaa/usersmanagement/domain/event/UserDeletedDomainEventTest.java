package com.jcaa.usersmanagement.domain.event;

import static org.junit.jupiter.api.Assertions.*;

import com.jcaa.usersmanagement.domain.valueobject.UserId;
import java.time.LocalDateTime;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests para UserDeletedDomainEvent.
 *
 * <p>Este evento es el más simple: recibe un UserId y genera un payload con una sola entrada. Se
 * verifica que el nombre del evento sea correcto, que occurredOn quede registrado, que el accessor
 * userId() devuelva la misma referencia y que el payload contenga exactamente un campo.
 */
@DisplayName("UserDeletedDomainEvent")
class UserDeletedDomainEventTest {

  private static final String ID = "user-002";

  // ── eventName

  @Test
  @DisplayName("eventName() debe retornar la constante 'user.deleted'")
  void shouldHaveEventNameUserDeleted() {
    // Arrange
    final UserDeletedDomainEvent event = new UserDeletedDomainEvent(new UserId(ID));

    // Act
    final String result = event.getEventName();

    // Assert
    assertEquals("user.deleted", result);
  }

  // ── occurredOn

  @Test
  @DisplayName("occurredOn() no debe ser nulo y debe quedar acotado al instante de construcción")
  void shouldRecordOccurredOnAtCreationTime() {
    // Arrange
    final LocalDateTime before = LocalDateTime.now();
    final UserDeletedDomainEvent event = new UserDeletedDomainEvent(new UserId(ID));
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

  // ── userId()

  @Test
  @DisplayName("userId() debe devolver la misma instancia de UserId recibida en el constructor")
  void shouldReturnSameUserIdInstance() {
    // Arrange
    final UserId userId = new UserId(ID);
    final UserDeletedDomainEvent event = new UserDeletedDomainEvent(userId);

    // Act
    final UserId result = event.getUserId();

    // Assert
    assertSame(userId, result);
  }

  // ── payload()

  @Test
  @DisplayName("payload() debe contener únicamente la entrada 'id' con el valor del UserId")
  void shouldReturnPayloadWithOnlyUserId() {
    // Arrange
    final UserDeletedDomainEvent event = new UserDeletedDomainEvent(new UserId(ID));

    // Act
    final Map<String, String> payload = event.payload();

    // Assert
    assertAll("payload de UserDeletedDomainEvent",
        () -> assertEquals(1,  payload.size(),    "el mapa debe tener exactamente 1 entrada"),
        () -> assertEquals(ID, payload.get("id"), "id")
    );
  }
}
