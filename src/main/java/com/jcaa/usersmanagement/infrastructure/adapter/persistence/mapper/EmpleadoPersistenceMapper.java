package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.enums.EmpleadoEstado;
import com.jcaa.usersmanagement.domain.model.EmpleadoModel;
import com.jcaa.usersmanagement.domain.valueobject.EmpleadoId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.EmpleadoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.EmpleadoEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoPersistenceMapper {

  public EmpleadoPersistenceDto fromModelToDto(final EmpleadoModel empleado) {
    return new EmpleadoPersistenceDto(
        empleado.getId() != null ? empleado.getId().value() : null,
        empleado.getNombre(),
        empleado.getApellido(),
        empleado.getEmail(),
        empleado.getCargo(),
        empleado.getFechaContratacion().toString(),
        empleado.getEstado().name());
  }

  public EmpleadoEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
    return new EmpleadoEntity(
        resultSet.getLong("id"),
        resultSet.getString("nombre"),
        resultSet.getString("apellido"),
        resultSet.getString("email"),
        resultSet.getString("cargo"),
        resultSet.getString("fecha_contratacion"),
        resultSet.getString("estado"),
        resultSet.getString("created_at"),
        resultSet.getString("updated_at"));
  }

  public EmpleadoModel fromEntityToModel(final EmpleadoEntity entity) {
    return new EmpleadoModel(
        new EmpleadoId(entity.id()),
        entity.nombre(),
        entity.apellido(),
        entity.email(),
        entity.cargo(),
        LocalDate.parse(entity.fechaContratacion()),
        EmpleadoEstado.fromString(entity.estado()));
  }

  public EmpleadoModel fromResultSetToModel(final ResultSet resultSet) throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(resultSet));
  }

  public List<EmpleadoModel> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
    final List<EmpleadoModel> empleados = new ArrayList<>();
    while (resultSet.next()) {
      empleados.add(fromResultSetToModel(resultSet));
    }
    return empleados;
  }
}
