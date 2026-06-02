package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteProyectoPort;
import com.jcaa.usersmanagement.application.port.out.GetAllProyectosPort;
import com.jcaa.usersmanagement.application.port.out.GetProyectoByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveProyectoPort;
import com.jcaa.usersmanagement.application.port.out.UpdateProyectoPort;
import com.jcaa.usersmanagement.domain.exception.ProyectoNotFoundException;
import com.jcaa.usersmanagement.domain.model.ProyectoModel;
import com.jcaa.usersmanagement.domain.valueobject.ProyectoId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.ProyectoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.ProyectoPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Log
@RequiredArgsConstructor
public final class ProyectoRepositoryMySQL
    implements SaveProyectoPort,
        UpdateProyectoPort,
        GetProyectoByIdPort,
        GetAllProyectosPort,
        DeleteProyectoPort {

  private final Connection connection;
  private final ProyectoPersistenceMapper persistenceMapper = new ProyectoPersistenceMapper();

  private static final String SQL_INSERT =
      "INSERT INTO proyecto (nombre_clave, denominacion, fecha_inicio, fecha_fin, estado, promotor_id) "
      + "VALUES (?, ?, ?, ?, ?, ?)";

  private static final String SQL_UPDATE =
      "UPDATE proyecto SET nombre_clave = ?, denominacion = ?, fecha_inicio = ?, fecha_fin = ?, "
      + "estado = ?, promotor_id = ?, updated_at = NOW() WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
      "SELECT id, nombre_clave, denominacion, fecha_inicio, fecha_fin, estado, promotor_id, "
      + "created_at, updated_at FROM proyecto WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT id, nombre_clave, denominacion, fecha_inicio, fecha_fin, estado, promotor_id, "
      + "created_at, updated_at FROM proyecto ORDER BY nombre_clave ASC";

  private static final String SQL_DELETE = "DELETE FROM proyecto WHERE id = ?";

  @Override
  public ProyectoModel save(final ProyectoModel proyecto) {
    try (final PreparedStatement statement =
        connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
      final ProyectoPersistenceDto dto = persistenceMapper.fromModelToDto(proyecto);
      statement.setString(1, dto.nombreClave());
      statement.setString(2, dto.denominacion());
      statement.setDate(3, Date.valueOf(dto.fechaInicio()));
      setNullableDate(statement, 4, dto.fechaFin());
      statement.setString(5, dto.estado());
      setNullableLong(statement, 6, dto.promotorId());
      statement.executeUpdate();
      final ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next()) {
        return findByIdOrFail(new ProyectoId(generatedKeys.getLong(1)));
      }
      throw PersistenceException.becauseProyectoGeneratedKeyMissing();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseProyectoSaveFailed(exception);
    }
  }

  @Override
  public ProyectoModel update(final ProyectoModel proyecto) {
    final ProyectoPersistenceDto dto = persistenceMapper.fromModelToDto(proyecto);
    try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
      statement.setString(1, dto.nombreClave());
      statement.setString(2, dto.denominacion());
      statement.setDate(3, Date.valueOf(dto.fechaInicio()));
      setNullableDate(statement, 4, dto.fechaFin());
      statement.setString(5, dto.estado());
      setNullableLong(statement, 6, dto.promotorId());
      statement.setLong(7, dto.id());
      statement.executeUpdate();
      return findByIdOrFail(new ProyectoId(dto.id()));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseProyectoUpdateFailed(dto.id(), exception);
    }
  }

  @Override
  public Optional<ProyectoModel> getById(final ProyectoId id) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      statement.setLong(1, id.value());
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(persistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseProyectoFindByIdFailed(id.value(), exception);
    }
  }

  @Override
  public List<ProyectoModel> getAll() {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet resultSet = statement.executeQuery();
      return persistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseProyectoFindAllFailed(exception);
    }
  }

  @Override
  public void delete(final ProyectoId id) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
      statement.setLong(1, id.value());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseProyectoDeleteFailed(id.value(), exception);
    }
  }

  private ProyectoModel findByIdOrFail(final ProyectoId id) {
    return getById(id)
        .orElseThrow(() -> ProyectoNotFoundException.becauseIdWasNotFound(id.value()));
  }

  private static void setNullableDate(
      final PreparedStatement statement, final int index, final String value) throws SQLException {
    if (value != null) {
      statement.setDate(index, Date.valueOf(value));
    } else {
      statement.setNull(index, Types.DATE);
    }
  }

  private static void setNullableLong(
      final PreparedStatement statement, final int index, final Long value) throws SQLException {
    if (value != null) {
      statement.setLong(index, value);
    } else {
      statement.setNull(index, Types.BIGINT);
    }
  }
}
