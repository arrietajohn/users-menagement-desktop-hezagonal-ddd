package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.ResidenciaRepositoryPort;
import com.jcaa.usersmanagement.domain.model.ResidenciaModel;
import com.jcaa.usersmanagement.domain.valueobject.ResidenciaId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.ResidenciaPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.ResidenciaPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Log
@RequiredArgsConstructor
public final class ResidenciaRepositoryMySQL implements ResidenciaRepositoryPort {

    private static final String SQL_INSERT =
            "INSERT INTO residencia (persona_id, municipio_id, pais_extranjero, direccion, fecha_inicio) "
                    + "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE residencia SET persona_id = ?, municipio_id = ?, pais_extranjero = ?, "
                    + "direccion = ?, fecha_inicio = ? WHERE id = ?";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, persona_id, municipio_id, pais_extranjero, direccion, fecha_inicio "
                    + "FROM residencia WHERE id = ? LIMIT 1";

    private static final String SQL_SELECT_ALL =
            "SELECT id, persona_id, municipio_id, pais_extranjero, direccion, fecha_inicio "
                    + "FROM residencia ORDER BY id ASC";

    private static final String SQL_DELETE =
            "DELETE FROM residencia WHERE id = ?";

    private final Connection connection;

    @Override
    public ResidenciaModel save(final ResidenciaModel residencia) {
        final ResidenciaPersistenceDto dto = ResidenciaPersistenceMapper.fromModelToDto(residencia);
        try (final PreparedStatement statement =
                     connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, dto.personaId());
            statement.setString(2, dto.municipioId());
            statement.setString(3, dto.paisExtranjero());
            statement.setString(4, dto.direccion());
            statement.setString(5, dto.fechaInicio());
            statement.executeUpdate();
            final ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                return findById(new ResidenciaId(keys.getInt(1))).orElseThrow();
            }
            throw PersistenceException.becauseSaveFailed("new", new SQLException("No generated key"));
        } catch (final SQLException exception) {
            throw PersistenceException.becauseSaveFailed("new", exception);
        }
    }

    @Override
    public ResidenciaModel update(final ResidenciaModel residencia) {
        final ResidenciaPersistenceDto dto = ResidenciaPersistenceMapper.fromModelToDto(residencia);
        try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setInt(1, dto.personaId());
            statement.setString(2, dto.municipioId());
            statement.setString(3, dto.paisExtranjero());
            statement.setString(4, dto.direccion());
            statement.setString(5, dto.fechaInicio());
            statement.setInt(6, dto.id());
            statement.executeUpdate();
            return findById(residencia.getId()).orElseThrow();
        } catch (final SQLException exception) {
            throw PersistenceException.becauseUpdateFailed(residencia.getId().toString(), exception);
        }
    }

    @Override
    public void delete(final ResidenciaId id) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setInt(1, id.value());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw PersistenceException.becauseDeleteFailed(id.toString(), exception);
        }
    }

    @Override
    public Optional<ResidenciaModel> findById(final ResidenciaId id) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setInt(1, id.value());
            final ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return Optional.empty();
            }
            return Optional.of(ResidenciaPersistenceMapper.fromResultSetToModel(rs));
        } catch (final SQLException exception) {
            throw PersistenceException.becauseFindByIdFailed(id.toString(), exception);
        }
    }

    @Override
    public List<ResidenciaModel> findAll() {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
            final ResultSet rs = statement.executeQuery();
            return ResidenciaPersistenceMapper.fromResultSetToModelList(rs);
        } catch (final SQLException exception) {
            throw PersistenceException.becauseFindAllFailed(exception);
        }
    }
}