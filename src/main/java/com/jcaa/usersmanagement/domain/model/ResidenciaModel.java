package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.valueobject.DireccionResidencia;
import com.jcaa.usersmanagement.domain.valueobject.MunicipioId;
import com.jcaa.usersmanagement.domain.valueobject.PaisExtranjero;
import com.jcaa.usersmanagement.domain.valueobject.PersonaId;
import com.jcaa.usersmanagement.domain.valueobject.ResidenciaId;
import java.time.LocalDate;
import lombok.Value;

@Value
public class ResidenciaModel {

  ResidenciaId id;
  PersonaId personaId;
  MunicipioId municipioId;
  PaisExtranjero paisExtranjero;
  DireccionResidencia direccion;
  LocalDate fechaInicio;

  public static ResidenciaModel create(
      final PersonaId personaId,
      final MunicipioId municipioId,
      final PaisExtranjero paisExtranjero,
      final DireccionResidencia direccion,
      final LocalDate fechaInicio) {
    return new ResidenciaModel(
        null,
        personaId,
        municipioId,
        paisExtranjero,
        direccion,
        fechaInicio);
  }

  public ResidenciaModel withId(final ResidenciaId id) {
    return new ResidenciaModel(
        id,
        personaId,
        municipioId,
        paisExtranjero,
        direccion,
        fechaInicio);
  }

  public ResidenciaModel update(
      final PersonaId personaId,
      final MunicipioId municipioId,
      final PaisExtranjero paisExtranjero,
      final DireccionResidencia direccion,
      final LocalDate fechaInicio) {
    return new ResidenciaModel(
        id,
        personaId,
        municipioId,
        paisExtranjero,
        direccion,
        fechaInicio);
  }
}
