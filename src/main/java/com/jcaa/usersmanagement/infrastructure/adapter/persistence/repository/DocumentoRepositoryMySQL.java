package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteDocumentoPort;
import com.jcaa.usersmanagement.application.port.out.GetAllDocumentosPort;
import com.jcaa.usersmanagement.application.port.out.GetDocumentoByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveDocumentoPort;
import com.jcaa.usersmanagement.application.port.out.UpdateDocumentoPort;
import com.jcaa.usersmanagement.domain.exception.DocumentoNotFoundException;
import com.jcaa.usersmanagement.domain.model.DocumentoModel;
import com.jcaa.usersmanagement.domain.valueobject.DocumentoId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.DocumentoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.DocumentoPersistenceMapper;
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
public final class DocumentoRepositoryMySQL
    implements SaveDocumentoPort,
        UpdateDocumentoPort,
        GetDocumentoByIdPort,
        GetAllDocumentosPort,
        DeleteDocumentoPort {

  private final Connection connection;
  private final DocumentoPersistenceMapper persistenceMapper = new DocumentoPersistenceMapper();

  private static final String SQL_INSERT =
      "INSERT INTO documento (titulo, tipo, contenido, fecha_creacion, estado, autor_id) "
      + "VALUES (?, ?, ?, ?, ?, ?)";

  private static final String SQL_UPDATE =
      "UPDATE documento SET titulo = ?, tipo = ?, contenido = ?, fecha_creacion = ?, "
      + "estado = ?, autor_id = ?, updated_at = NOW() WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
      "SELECT id, titulo, tipo, contenido, fecha_creacion, estado, autor_id, "
      + "created_at, updated_at FROM documento WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT id, titulo, tipo, contenido, fecha_creacion, estado, autor_id, "
      + "created_at, updated_at FROM documento ORDER BY titulo ASC";

  private static final String SQL_DELETE = "DELETE FROM documento WHERE id = ?";

  @Override
  public DocumentoModel save(final DocumentoModel documento) {
    try (final PreparedStatement statement =
        connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
      final DocumentoPersistenceDto dto = persistenceMapper.fromModelToDto(documento);
      statement.setString(1, dto.titulo());
      statement.setString(2, dto.tipo());
      statement.setString(3, dto.contenido());
      statement.setDate(4, Date.valueOf(dto.fechaCreacion()));
      statement.setString(5, dto.estado());
      statement.setLong(6, dto.autorId());
      statement.executeUpdate();
      final ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next()) {
        return findByIdOrFail(new DocumentoId(generatedKeys.getLong(1)));
      }
      throw PersistenceException.becauseDocumentoGeneratedKeyMissing();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseDocumentoSaveFailed(exception);
    }
  }

  @Override
  public DocumentoModel update(final DocumentoModel documento) {
    final DocumentoPersistenceDto dto = persistenceMapper.fromModelToDto(documento);
    try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
      statement.setString(1, dto.titulo());
      statement.setString(2, dto.tipo());
      statement.setString(3, dto.contenido());
      statement.setDate(4, Date.valueOf(dto.fechaCreacion()));
      statement.setString(5, dto.estado());
      statement.setLong(6, dto.autorId());
      statement.setLong(7, dto.id());
      statement.executeUpdate();
      return findByIdOrFail(new DocumentoId(dto.id()));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseDocumentoUpdateFailed(dto.id(), exception);
    }
  }

  @Override
  public Optional<DocumentoModel> getById(final DocumentoId id) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      statement.setLong(1, id.value());
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(persistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseDocumentoFindByIdFailed(id.value(), exception);
    }
  }

  @Override
  public List<DocumentoModel> getAll() {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet resultSet = statement.executeQuery();
      return persistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseDocumentoFindAllFailed(exception);
    }
  }

  @Override
  public void delete(final DocumentoId id) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
      statement.setLong(1, id.value());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseDocumentoDeleteFailed(id.value(), exception);
    }
  }

  private DocumentoModel findByIdOrFail(final DocumentoId id) {
    return getById(id)
        .orElseThrow(() -> DocumentoNotFoundException.becauseIdWasNotFound(id.value()));
  }
}
