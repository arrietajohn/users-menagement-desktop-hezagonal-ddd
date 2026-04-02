package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.model.UserModel;
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
  public Map<String, String> payload() {
    return Map.of(
        "id", user.getId().value(),
        "name", user.getName().value(),
        "email", user.getEmail().value(),
        "role", user.getRole().name(),
        "status", user.getStatus().name());
  }
}
