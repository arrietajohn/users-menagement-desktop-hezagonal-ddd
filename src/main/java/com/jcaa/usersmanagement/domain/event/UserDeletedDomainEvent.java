package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.valueobject.UserId;
import java.util.Map;
import lombok.Getter;

@Getter
public final class UserDeletedDomainEvent extends DomainEvent {

  private static final String EVENT_NAME = "user.deleted";

  private final UserId userId;

  public UserDeletedDomainEvent(final UserId userId) {
    super(EVENT_NAME);
    this.userId = userId;
  }

  @Override
  public Map<String, String> payload() {
    return Map.of("id", userId.value());
  }
}
