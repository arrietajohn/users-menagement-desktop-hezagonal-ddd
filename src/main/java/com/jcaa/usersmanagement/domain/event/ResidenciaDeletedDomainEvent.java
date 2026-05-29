package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.valueobject.ResidenciaId;
import java.util.Map;
import lombok.Getter;

@Getter
public final class ResidenciaDeletedDomainEvent extends DomainEvent {

  private static final String EVENT_NAME = "residencia.deleted";

  private final ResidenciaId residenciaId;

  public ResidenciaDeletedDomainEvent(final ResidenciaId residenciaId) {
    super(EVENT_NAME);
    this.residenciaId = residenciaId;
  }

  @Override
  public Map<String, String> payload() {
    return Map.of("id", String.valueOf(residenciaId.value()));
  }
}
