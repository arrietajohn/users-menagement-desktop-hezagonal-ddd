package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.enums.DocumentoEstado;
import com.jcaa.usersmanagement.domain.enums.DocumentoTipo;
import com.jcaa.usersmanagement.domain.model.DocumentoModel;
import com.jcaa.usersmanagement.domain.valueobject.DocumentoId;
import com.jcaa.usersmanagement.domain.valueobject.EmpleadoId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.DocumentoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.entity.DocumentoEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DocumentoPersistenceMapper {

  public DocumentoPersistenceDto fromModelToDto(final DocumentoModel documento) {
    return new DocumentoPersistenceDto(
        documento.getId() != null ? documento.getId().value() : null,
        documento.getTitulo(),
        documento.getTipo().name(),
        documento.getContenido(),
        documento.getFechaCreacion().toString(),
        documento.getEstado().name(),
        documento.getAutorId().value());
  }

  public DocumentoEntity fromResultSetToEntity(final ResultSet resultSet) throws SQLException {
    return new DocumentoEntity(
        resultSet.getLong("id"),
        resultSet.getString("titulo"),
        resultSet.getString("tipo"),
        resultSet.getString("contenido"),
        resultSet.getString("fecha_creacion"),
        resultSet.getString("estado"),
        resultSet.getLong("autor_id"),
        resultSet.getString("created_at"),
        resultSet.getString("updated_at"));
  }

  public DocumentoModel fromEntityToModel(final DocumentoEntity entity) {
    return new DocumentoModel(
        new DocumentoId(entity.id()),
        entity.titulo(),
        DocumentoTipo.fromString(entity.tipo()),
        entity.contenido(),
        LocalDate.parse(entity.fechaCreacion()),
        DocumentoEstado.fromString(entity.estado()),
        new EmpleadoId(entity.autorId()));
  }

  public DocumentoModel fromResultSetToModel(final ResultSet resultSet) throws SQLException {
    return fromEntityToModel(fromResultSetToEntity(resultSet));
  }

  public List<DocumentoModel> fromResultSetToModelList(final ResultSet resultSet) throws SQLException {
    final List<DocumentoModel> documentos = new ArrayList<>();
    while (resultSet.next()) {
      documentos.add(fromResultSetToModel(resultSet));
    }
    return documentos;
  }
}
