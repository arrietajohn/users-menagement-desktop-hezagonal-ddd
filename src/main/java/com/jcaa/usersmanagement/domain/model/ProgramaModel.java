package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.valueobject.ProgramaGenero;
import com.jcaa.usersmanagement.domain.valueobject.ProgramaId;
import com.jcaa.usersmanagement.domain.valueobject.ProgramaName;
import lombok.Value;

@Value
public class ProgramaModel {

  ProgramaId id;
  ProgramaName nombre;
  ProgramaGenero genero;

  public static ProgramaModel create(
      final ProgramaId id,
      final ProgramaName nombre,
      final ProgramaGenero genero) {
    return new ProgramaModel(id, nombre, genero);
  }
}
