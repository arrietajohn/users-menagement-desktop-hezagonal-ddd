package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.DocumentoEstado;
import com.jcaa.usersmanagement.domain.enums.DocumentoTipo;
import com.jcaa.usersmanagement.domain.valueobject.DocumentoId;
import com.jcaa.usersmanagement.domain.valueobject.EmpleadoId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DocumentoModel {

  private DocumentoId id;
  private String titulo;
  private DocumentoTipo tipo;
  private String contenido;
  private LocalDate fechaCreacion;
  private DocumentoEstado estado;
  private EmpleadoId autorId;

  public static DocumentoModel create(
      final String titulo,
      final DocumentoTipo tipo,
      final String contenido,
      final LocalDate fechaCreacion,
      final DocumentoEstado estado,
      final EmpleadoId autorId) {
    return new DocumentoModel(null, titulo, tipo, contenido, fechaCreacion, estado, autorId);
  }
}
