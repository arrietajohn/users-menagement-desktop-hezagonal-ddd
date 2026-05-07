package com.jcaa.usersmanagement.domain.event;

import com.jcaa.usersmanagement.domain.model.AsignaturaModel;
import java.util.Map;

public final class AsignaturaDeletedDomainEvent extends DomainEvent {

  private static final String EVENT_NAME = "asignatura.deleted";

  private final AsignaturaModel asignatura;

  public AsignaturaDeletedDomainEvent(final AsignaturaModel asignatura) {
    super(EVENT_NAME);
    this.asignatura = asignatura;
  }

  public AsignaturaModel getAsignatura() {
    return asignatura;
  }

  @Override
  public Map<String, String> payload() {
    return Map.of(
        "id", asignatura.getId().value(),
        "nombre", asignatura.getNombre().value(),
        "nombreCompleto", asignatura.getNombreCompleto().value(),
        "descripcion", asignatura.getDescripcion().value(),
        "areaConocimiento", asignatura.getAreaConocimiento().value(),
        "carrera", asignatura.getCarrera().value(),
        "numeroCreditos", String.valueOf(asignatura.getNumeroCreditos().value()),
        "contenidoTematico", asignatura.getContenidoTematico().value(),
        "semestre", String.valueOf(asignatura.getSemestre().value()),
        "profesor", asignatura.getProfesor().value());
  }
}

