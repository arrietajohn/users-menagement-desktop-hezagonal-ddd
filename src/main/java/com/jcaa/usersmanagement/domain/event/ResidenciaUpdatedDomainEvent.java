package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.model.ResidenciaModel;
import com.jcaa.usersmanagement.domain.valueobject.DireccionResidencia;
import com.jcaa.usersmanagement.domain.valueobject.PaisExtranjero;
import java.time.LocalDate;
import java.util.Map;
import lombok.Getter;

@Getter
public final class ResidenciaUpdatedDomainEvent extends DomainEvent {

  private static final String EVENT_NAME = "residencia.updated";

  private final ResidenciaModel residencia;

  public ResidenciaUpdatedDomainEvent(final ResidenciaModel residencia) {
    super(EVENT_NAME);
    this.residencia = residencia;
  }

  @Override
  public Map<String, String> payload() {
    return Map.of(
        "id", String.valueOf(residencia.getId().value()),
        "personaId", residencia.getPersonaId().toString(),
        "municipioId", residencia.getMunicipioId().toString(),
        "paisExtranjero", asString(residencia.getPaisExtranjero()),
        "direccion", asString(residencia.getDireccion()),
        "fechaInicio", asString(residencia.getFechaInicio()));
  }

  private static String asString(final PaisExtranjero pais) {
    return pais == null || pais.value() == null ? "" : pais.value();
  }

  private static String asString(final DireccionResidencia direccion) {
    return direccion == null || direccion.value() == null ? "" : direccion.value();
  }

  private static String asString(final LocalDate date) {
    return date == null ? "" : date.toString();
  }
}
