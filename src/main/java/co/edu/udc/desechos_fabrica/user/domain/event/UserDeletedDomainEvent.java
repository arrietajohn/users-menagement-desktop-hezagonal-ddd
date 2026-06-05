package co.edu.udc.desechos_fabrica.user.domain.event;

import co.edu.udc.desechos_fabrica.shared.DomainEvent;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserEmail;

import java.io.Serializable;
import java.util.Map;
import lombok.Getter;

@Getter
public final class UserDeletedDomainEvent extends DomainEvent {

  private static final String EVENT_NAME = "user.deleted";

  private final UserEmail userEmail;

  public UserDeletedDomainEvent(final UserEmail userEmail) {
    super(EVENT_NAME);
    this.userEmail = userEmail;
  }

  @Override
  public Map<String, Serializable> payload() {
    return Map.of("email", userEmail.value());
  }
}
