package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.TareaEstado;
import com.jcaa.usersmanagement.domain.enums.TareaPrioridad;
import com.jcaa.usersmanagement.domain.valueobject.EmpleadoId;
import com.jcaa.usersmanagement.domain.valueobject.TareaId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TareaModel {

  private TareaId id;
  private String titulo;
  private String descripcion;
  private TareaPrioridad prioridad;
  private TareaEstado estado;
  private LocalDate fechaVencimiento;
  private EmpleadoId empleadoId;

  public static TareaModel create(
      final String titulo,
      final String descripcion,
      final TareaPrioridad prioridad,
      final TareaEstado estado,
      final LocalDate fechaVencimiento,
      final EmpleadoId empleadoId) {
    return new TareaModel(null, titulo, descripcion, prioridad, estado, fechaVencimiento, empleadoId);
  }
}
