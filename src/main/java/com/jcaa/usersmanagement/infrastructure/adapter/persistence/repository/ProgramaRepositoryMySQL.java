package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteProgramaPort;
import com.jcaa.usersmanagement.application.port.out.GetAllProgramasPort;
import com.jcaa.usersmanagement.application.port.out.GetProgramaByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveProgramaPort;
import com.jcaa.usersmanagement.domain.exception.ProgramaNotFoundException;
import com.jcaa.usersmanagement.domain.model.ProgramaModel;
import com.jcaa.usersmanagement.domain.valueobject.ProgramaId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.ProgramaPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.ProgramaPersistenceMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public final class ProgramaRepositoryMySQL
    implements SaveProgramaPort, GetProgramaByIdPort, GetAllProgramasPort, DeleteProgramaPort {

  private static final String SQL_INSERT =
      "INSERT INTO programas (id, nombre, genero) VALUES (?, ?, ?)";

  private static final String SQL_UPDATE =
      "UPDATE programas SET nombre = ?, genero = ? WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
      "SELECT id, nombre, genero FROM programas WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT id, nombre, genero FROM programas ORDER BY id ASC";

  private static final String SQL_DELETE =
      "DELETE FROM programas WHERE id = ?";

  private final Connection connection;

  @Override
  public ProgramaModel save(final ProgramaModel programa) {
    final ProgramaPersistenceDto dto = ProgramaPersistenceMapper.fromModelToDto(programa);
    
    // Check if it exists to decide whether to insert or update
    Optional<ProgramaModel> existing = getById(programa.getId());
    if (existing.isPresent()) {
      executeUpdate(dto);
    } else {
      executeSave(dto);
    }
    return findByIdOrFail(programa.getId());
  }

  @Override
  public Optional<ProgramaModel> getById(final ProgramaId id) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      statement.setLong(1, id.value());
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(ProgramaPersistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindByIdFailed(id.value().toString(), exception);
    }
  }

  @Override
  public List<ProgramaModel> getAll() {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet resultSet = statement.executeQuery();
      return ProgramaPersistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindAllFailed(exception);
    }
  }

  @Override
  public void deleteById(final ProgramaId id) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
      statement.setLong(1, id.value());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseDeleteFailed(id.value().toString(), exception);
    }
  }

  private void executeSave(final ProgramaPersistenceDto dto) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
      statement.setLong(1, dto.id());
      statement.setString(2, dto.nombre());
      statement.setString(3, dto.genero());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseSaveFailed(dto.id().toString(), exception);
    }
  }

  private void executeUpdate(final ProgramaPersistenceDto dto) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
      statement.setString(1, dto.nombre());
      statement.setString(2, dto.genero());
      statement.setLong(3, dto.id());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseUpdateFailed(dto.id().toString(), exception);
    }
  }

  private ProgramaModel findByIdOrFail(final ProgramaId id) {
    return getById(id)
        .orElseThrow(() -> ProgramaNotFoundException.becauseIdWasNotFound(id.value().toString()));
  }
}
