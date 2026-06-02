package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteEmpleadoPort;
import com.jcaa.usersmanagement.application.port.out.GetAllEmpleadosPort;
import com.jcaa.usersmanagement.application.port.out.GetEmpleadoByEmailPort;
import com.jcaa.usersmanagement.application.port.out.GetEmpleadoByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveEmpleadoPort;
import com.jcaa.usersmanagement.application.port.out.UpdateEmpleadoPort;
import com.jcaa.usersmanagement.domain.exception.EmpleadoNotFoundException;
import com.jcaa.usersmanagement.domain.model.EmpleadoModel;
import com.jcaa.usersmanagement.domain.valueobject.EmpleadoId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.EmpleadoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.EmpleadoPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Log
@RequiredArgsConstructor
public final class EmpleadoRepositoryMySQL
    implements SaveEmpleadoPort,
        UpdateEmpleadoPort,
        GetEmpleadoByIdPort,
        GetEmpleadoByEmailPort,
        GetAllEmpleadosPort,
        DeleteEmpleadoPort {

  private final Connection connection;
  private final EmpleadoPersistenceMapper persistenceMapper = new EmpleadoPersistenceMapper();

  private static final String SQL_INSERT =
      "INSERT INTO empleado (nombre, apellido, email, cargo, fecha_contratacion, estado) "
      + "VALUES (?, ?, ?, ?, ?, ?)";

  private static final String SQL_UPDATE =
      "UPDATE empleado SET nombre = ?, apellido = ?, email = ?, cargo = ?, "
      + "fecha_contratacion = ?, estado = ?, updated_at = NOW() WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
      "SELECT id, nombre, apellido, email, cargo, fecha_contratacion, estado, "
      + "created_at, updated_at FROM empleado WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_BY_EMAIL =
      "SELECT id, nombre, apellido, email, cargo, fecha_contratacion, estado, "
      + "created_at, updated_at FROM empleado WHERE email = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT id, nombre, apellido, email, cargo, fecha_contratacion, estado, "
      + "created_at, updated_at FROM empleado ORDER BY apellido ASC, nombre ASC";

  private static final String SQL_DELETE = "DELETE FROM empleado WHERE id = ?";

  @Override
  public EmpleadoModel save(final EmpleadoModel empleado) {
    try (final PreparedStatement statement =
        connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
      final EmpleadoPersistenceDto dto = persistenceMapper.fromModelToDto(empleado);
      statement.setString(1, dto.nombre());
      statement.setString(2, dto.apellido());
      statement.setString(3, dto.email());
      statement.setString(4, dto.cargo());
      statement.setDate(5, Date.valueOf(dto.fechaContratacion()));
      statement.setString(6, dto.estado());
      statement.executeUpdate();
      final ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next()) {
        return findByIdOrFail(new EmpleadoId(generatedKeys.getLong(1)));
      }
      throw PersistenceException.becauseEmpleadoGeneratedKeyMissing();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseEmpleadoSaveFailed(exception);
    }
  }

  @Override
  public EmpleadoModel update(final EmpleadoModel empleado) {
    final EmpleadoPersistenceDto dto = persistenceMapper.fromModelToDto(empleado);
    try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
      statement.setString(1, dto.nombre());
      statement.setString(2, dto.apellido());
      statement.setString(3, dto.email());
      statement.setString(4, dto.cargo());
      statement.setDate(5, Date.valueOf(dto.fechaContratacion()));
      statement.setString(6, dto.estado());
      statement.setLong(7, dto.id());
      statement.executeUpdate();
      return findByIdOrFail(new EmpleadoId(dto.id()));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseEmpleadoUpdateFailed(dto.id(), exception);
    }
  }

  @Override
  public Optional<EmpleadoModel> getById(final EmpleadoId id) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      statement.setLong(1, id.value());
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(persistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseEmpleadoFindByIdFailed(id.value(), exception);
    }
  }

  @Override
  public Optional<EmpleadoModel> getByEmail(final String email) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_EMAIL)) {
      statement.setString(1, email);
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(persistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseEmpleadoFindByEmailFailed(email, exception);
    }
  }

  @Override
  public List<EmpleadoModel> getAll() {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet resultSet = statement.executeQuery();
      return persistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseEmpleadoFindAllFailed(exception);
    }
  }

  @Override
  public void delete(final EmpleadoId id) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
      statement.setLong(1, id.value());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseEmpleadoDeleteFailed(id.value(), exception);
    }
  }

  private EmpleadoModel findByIdOrFail(final EmpleadoId id) {
    return getById(id)
        .orElseThrow(() -> EmpleadoNotFoundException.becauseIdWasNotFound(id.value()));
  }
}
