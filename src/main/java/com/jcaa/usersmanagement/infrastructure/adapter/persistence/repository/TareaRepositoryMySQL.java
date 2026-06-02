package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteTareaPort;
import com.jcaa.usersmanagement.application.port.out.GetAllTareasPort;
import com.jcaa.usersmanagement.application.port.out.GetTareaByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveTareaPort;
import com.jcaa.usersmanagement.application.port.out.UpdateTareaPort;
import com.jcaa.usersmanagement.domain.exception.TareaNotFoundException;
import com.jcaa.usersmanagement.domain.model.TareaModel;
import com.jcaa.usersmanagement.domain.valueobject.TareaId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.TareaPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.TareaPersistenceMapper;
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
public final class TareaRepositoryMySQL
    implements SaveTareaPort,
        UpdateTareaPort,
        GetTareaByIdPort,
        GetAllTareasPort,
        DeleteTareaPort {

  private final Connection connection;
  private final TareaPersistenceMapper persistenceMapper = new TareaPersistenceMapper();

  private static final String SQL_INSERT =
      "INSERT INTO tarea (titulo, descripcion, prioridad, estado, fecha_vencimiento, empleado_id) "
      + "VALUES (?, ?, ?, ?, ?, ?)";

  private static final String SQL_UPDATE =
      "UPDATE tarea SET titulo = ?, descripcion = ?, prioridad = ?, estado = ?, "
      + "fecha_vencimiento = ?, empleado_id = ?, updated_at = NOW() WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
      "SELECT id, titulo, descripcion, prioridad, estado, fecha_vencimiento, empleado_id, "
      + "created_at, updated_at FROM tarea WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT id, titulo, descripcion, prioridad, estado, fecha_vencimiento, empleado_id, "
      + "created_at, updated_at FROM tarea ORDER BY titulo ASC";

  private static final String SQL_DELETE = "DELETE FROM tarea WHERE id = ?";

  @Override
  public TareaModel save(final TareaModel tarea) {
    try (final PreparedStatement statement =
        connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
      final TareaPersistenceDto dto = persistenceMapper.fromModelToDto(tarea);
      statement.setString(1, dto.titulo());
      statement.setString(2, dto.descripcion());
      statement.setString(3, dto.prioridad());
      statement.setString(4, dto.estado());
      setNullableDate(statement, 5, dto.fechaVencimiento());
      statement.setLong(6, dto.empleadoId());
      statement.executeUpdate();
      final ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next()) {
        return findByIdOrFail(new TareaId(generatedKeys.getLong(1)));
      }
      throw PersistenceException.becauseTareaGeneratedKeyMissing();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseTareaSaveFailed(exception);
    }
  }

  @Override
  public TareaModel update(final TareaModel tarea) {
    final TareaPersistenceDto dto = persistenceMapper.fromModelToDto(tarea);
    try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
      statement.setString(1, dto.titulo());
      statement.setString(2, dto.descripcion());
      statement.setString(3, dto.prioridad());
      statement.setString(4, dto.estado());
      setNullableDate(statement, 5, dto.fechaVencimiento());
      statement.setLong(6, dto.empleadoId());
      statement.setLong(7, dto.id());
      statement.executeUpdate();
      return findByIdOrFail(new TareaId(dto.id()));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseTareaUpdateFailed(dto.id(), exception);
    }
  }

  @Override
  public Optional<TareaModel> getById(final TareaId id) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      statement.setLong(1, id.value());
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(persistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseTareaFindByIdFailed(id.value(), exception);
    }
  }

  @Override
  public List<TareaModel> getAll() {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet resultSet = statement.executeQuery();
      return persistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseTareaFindAllFailed(exception);
    }
  }

  @Override
  public void delete(final TareaId id) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
      statement.setLong(1, id.value());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseTareaDeleteFailed(id.value(), exception);
    }
  }

  private TareaModel findByIdOrFail(final TareaId id) {
    return getById(id)
        .orElseThrow(() -> TareaNotFoundException.becauseIdWasNotFound(id.value()));
  }

  private static void setNullableDate(
      final PreparedStatement statement, final int index, final String value) throws SQLException {
    if (value != null) {
      statement.setDate(index, Date.valueOf(value));
    } else {
      statement.setNull(index, Types.DATE);
    }
  }
}
