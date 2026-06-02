package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.ProyectoEstado;
import com.jcaa.usersmanagement.domain.valueobject.ProyectoId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ProyectoModel {

  private ProyectoId id;
  private String nombreClave;
  private String denominacion;
  private LocalDate fechaInicio;
  private LocalDate fechaFin;
  private ProyectoEstado estado;
  private Long promotorId;

  public static ProyectoModel create(
      final String nombreClave,
      final String denominacion,
      final LocalDate fechaInicio,
      final LocalDate fechaFin,
      final ProyectoEstado estado,
      final Long promotorId) {
    return new ProyectoModel(null, nombreClave, denominacion, fechaInicio, fechaFin, estado, promotorId);
  }
}
