package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.enums.TareaEstado;
import com.jcaa.usersmanagement.domain.enums.TareaPrioridad;
import com.jcaa.usersmanagement.domain.model.TareaModel;
import com.jcaa.usersmanagement.domain.valueobject.EmpleadoId;
import com.jcaa.usersmanagement.domain.valueobject.TareaId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.TareaPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.TareaEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TareaPersistenceMapper {

  public TareaPersistenceDto fromModelToDto(final TareaModel tarea) {
    return new TareaPersistenceDto(
        tarea.getId() != null ? tarea.getId().value() : null,
        tarea.getTitulo(),
        tarea.getDescripcion(),
        tarea.getPrioridad().name(),
        tarea.getEstado().name(),
        tarea.getFechaVencimiento() != null ? tarea.getFechaVencimiento().toString() : null,
        tarea.getEmpleadoId().value());
  }

  public TareaEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
    return new TareaEntity(
        resultSet.getLong("id"),
        resultSet.getString("titulo"),
        resultSet.getString("descripcion"),
        resultSet.getString("prioridad"),
        resultSet.getString("estado"),
        resultSet.getString("fecha_vencimiento"),
        resultSet.getLong("empleado_id"),
        resultSet.getString("created_at"),
        resultSet.getString("updated_at"));
  }

  public TareaModel fromEntityToModel(final TareaEntity entity) {
    return new TareaModel(
        new TareaId(entity.id()),
        entity.titulo(),
        entity.descripcion(),
        TareaPrioridad.fromString(entity.prioridad()),
        TareaEstado.fromString(entity.estado()),
        entity.fechaVencimiento() != null ? LocalDate.parse(entity.fechaVencimiento()) : null,
        new EmpleadoId(entity.empleadoId()));
  }

  public TareaModel fromResultSetToModel(final ResultSet resultSet) throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(resultSet));
  }

  public List<TareaModel> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
    final List<TareaModel> tareas = new ArrayList<>();
    while (resultSet.next()) {
      tareas.add(fromResultSetToModel(resultSet));
    }
    return tareas;
  }
}
