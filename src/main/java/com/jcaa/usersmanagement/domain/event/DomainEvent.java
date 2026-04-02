package com.jcaa.usersmanagement.domain.event;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Getter;

@Getter
public abstract class DomainEvent {

  private final String eventName;
  private final LocalDateTime occurredOn;

  protected DomainEvent(final String eventName) {
    this.eventName = eventName;
    this.occurredOn = LocalDateTime.now();
  }

  public abstract Map<String, String> payload();
}
