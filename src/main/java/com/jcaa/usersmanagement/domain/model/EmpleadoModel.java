package com.jcaa.usersmanagement.domain.model;

import com.jcaa.usersmanagement.domain.enums.EmpleadoEstado;
import com.jcaa.usersmanagement.domain.valueobject.EmpleadoId;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EmpleadoModel {

  private EmpleadoId id;
  private String nombre;
  private String apellido;
  private String email;
  private String cargo;
  private LocalDate fechaContratacion;
  private EmpleadoEstado estado;

  public static EmpleadoModel create(
      final String nombre,
      final String apellido,
      final String email,
      final String cargo,
      final LocalDate fechaContratacion,
      final EmpleadoEstado estado) {
    return new EmpleadoModel(null, nombre, apellido, email, cargo, fechaContratacion, estado);
  }
}
