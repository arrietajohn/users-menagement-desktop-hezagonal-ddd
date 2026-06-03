package co.edu.udc.desechos_fabrica.user.domain.event;

import co.edu.udc.desechos_fabrica.shared.DomainEvent;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;

import java.io.Serializable;
import java.util.Map;
import lombok.Getter;

@Getter
public final class UserUpdatedDomainEvent extends DomainEvent {

  private static final String EVENT_NAME = "user.updated";

  private final UserModel user;

  public UserUpdatedDomainEvent(final UserModel user) {
    super(EVENT_NAME);
    this.user = user;
  }

  @Override
  public Map<String, Serializable> payload() {
    return Map.of(
        "firstName", user.getFirstName().value(),
        "lastName", user.getLastName().value(),
        "email", user.getEmail().value(),
        "role", user.getRole().name(),
        "status", user.getStatus().name());
  }
}
