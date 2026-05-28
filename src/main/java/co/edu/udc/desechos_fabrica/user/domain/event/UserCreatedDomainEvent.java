package co.edu.udc.desechos_fabrica.user.domain.event;

import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import java.util.Map;
import lombok.Getter;

@Getter
public final class UserCreatedDomainEvent extends DomainEvent {

  private static final String EVENT_NAME = "user.created";

  private final UserModel user;

  public UserCreatedDomainEvent(final UserModel user) {
    super(EVENT_NAME);
    this.user = user;
  }

  @Override
  public Map<String, String> payload() {
    return Map.of(
        "id", user.getId().value(),
        "name", user.getName().value(),
        "email", user.getEmail().value(),
        "role", user.getRole().name(),
        "status", user.getStatus().name());
  }
}
