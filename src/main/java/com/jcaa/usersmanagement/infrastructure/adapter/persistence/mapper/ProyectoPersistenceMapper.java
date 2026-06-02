package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.enums.ProyectoEstado;
import com.jcaa.usersmanagement.domain.model.ProyectoModel;
import com.jcaa.usersmanagement.domain.valueobject.ProyectoId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.ProyectoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.ProyectoEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProyectoPersistenceMapper {

  public ProyectoPersistenceDto fromModelToDto(final ProyectoModel proyecto) {
    return new ProyectoPersistenceDto(
        proyecto.getId() != null ? proyecto.getId().value() : null,
        proyecto.getNombreClave(),
        proyecto.getDenominacion(),
        proyecto.getFechaInicio().toString(),
        proyecto.getFechaFin() != null ? proyecto.getFechaFin().toString() : null,
        proyecto.getEstado().name(),
        proyecto.getPromotorId());
  }

  public ProyectoEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
    return new ProyectoEntity(
        resultSet.getLong("id"),
        resultSet.getString("nombre_clave"),
        resultSet.getString("denominacion"),
        resultSet.getString("fecha_inicio"),
        resultSet.getString("fecha_fin"),
        resultSet.getString("estado"),
        resultSet.getObject("promotor_id") != null ? resultSet.getLong("promotor_id") : null,
        resultSet.getString("created_at"),
        resultSet.getString("updated_at"));
  }

  public ProyectoModel fromEntityToModel(final ProyectoEntity entity) {
    return new ProyectoModel(
        new ProyectoId(entity.id()),
        entity.nombreClave(),
        entity.denominacion(),
        LocalDate.parse(entity.fechaInicio()),
        entity.fechaFin() != null ? LocalDate.parse(entity.fechaFin()) : null,
        ProyectoEstado.fromString(entity.estado()),
        entity.promotorId());
  }

  public ProyectoModel fromResultSetToModel(final ResultSet resultSet) throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(resultSet));
  }

  public List<ProyectoModel> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
    final List<ProyectoModel> proyectos = new ArrayList<>();
    while (resultSet.next()) {
      proyectos.add(fromResultSetToModel(resultSet));
    }
    return proyectos;
  }
}
