package co.edu.udc.desechos_fabrica.user.domain.event;

import static org.junit.jupiter.api.Assertions.*;

import co.edu.udc.desechos_fabrica.user.domain.event.UserDeletedDomainEvent;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserEmail;
import java.time.LocalDateTime;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Tests para UserDeletedDomainEvent.
 *
 * <p>Este evento es el más simple: recibe un UserEmail y genera un payload con una sola entrada. Se
 * verifica que el nombre del evento sea correcto, que occurredOn quede registrado, que el accessor
 * UserEmail() devuelva la misma referencia y que el payload contenga exactamente un campo.
 */
@DisplayName("UserDeletedDomainEvent")
class UserDeletedDomainEventTest {

  private static final String EMAIL = "john.arrieta@gmail.com";

  // ── eventName

  @Test
  @DisplayName("eventName() debe retornar la constante 'user.deleted'")
  void shouldHaveEventNameUserDeleted() {
    // Arrange
    final UserDeletedDomainEvent event = new UserDeletedDomainEvent(new UserEmail(EMAIL));

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
    final UserDeletedDomainEvent event = new UserDeletedDomainEvent(new UserEmail(EMAIL));
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

  // ── UserEmail()

  @Test
  @DisplayName("UserEmail() debe devolver la misma instancia de UserEmail recibida en el constructor")
  void shouldReturnSameUserEmailInstance() {
    // Arrange
    final UserEmail UserEmail = new UserEmail(EMAIL);
    final UserDeletedDomainEvent event = new UserDeletedDomainEvent(UserEmail);

    // Act
    final UserEmail result = event.getUserEmail();

    // Assert
    assertSame(UserEmail, result);
  }

  // ── payload()

  @Test
  @DisplayName("payload() debe contener únicamente la entrada 'id' con el valor del UserEmail")
  void shouldReturnPayloadWithOnlyUserEmail() {
    // Arrange
    final UserDeletedDomainEvent event = new UserDeletedDomainEvent(new UserEmail(EMAIL));

    // Act
    final Map<String, String> payload = event.payload();

    // Assert
    assertAll(
        "payload de UserDeletedDomainEvent",
        () -> assertEquals(1, payload.size(), "el mapa debe tener exactamente 1 entrada"),
        () -> assertEquals(EMAIL, payload.get("email"), "email"));
  }
}
