package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteEspeciePort;
import com.jcaa.usersmanagement.application.port.out.GetAllEspeciesPort;
import com.jcaa.usersmanagement.application.port.out.GetEspecieByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveEspeciePort;
import com.jcaa.usersmanagement.application.port.out.UpdateEspeciePort;
import com.jcaa.usersmanagement.domain.model.Especie;
import com.jcaa.usersmanagement.domain.model.EspecieId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.EspeciePersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.EspeciePersistenceMapper;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public final class EspecieRepositoryMySQL
    implements SaveEspeciePort,
               UpdateEspeciePort,
               GetEspecieByIdPort,
               GetAllEspeciesPort,
               DeleteEspeciePort {

    private static final String SQL_INSERT =
        "INSERT INTO especies (id, nombre_cientifico, nombre_español, descripcion, created_at, updated_at) "
        + "VALUES (?, ?, ?, ?, NOW(), NOW())";

    private static final String SQL_UPDATE =
        "UPDATE especies SET nombre_español = ?, descripcion = ?, updated_at = NOW() "
        + "WHERE id = ?";

    private static final String SQL_SELECT_BY_ID =
        "SELECT id, nombre_cientifico, nombre_español, descripcion, created_at, updated_at "
        + "FROM especies WHERE id = ? LIMIT 1";

    private static final String SQL_SELECT_ALL =
        "SELECT id, nombre_cientifico, nombre_español, descripcion, created_at, updated_at "
        + "FROM especies ORDER BY nombre_español ASC";

    private static final String SQL_DELETE =
        "DELETE FROM especies WHERE id = ?";

    private final Connection connection;

    @Override
    public Especie save(final Especie especie) {
        final EspeciePersistenceDto dto = EspeciePersistenceMapper.fromModelToDto(especie);
        try (final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, dto.id());
            statement.setString(2, dto.nombreCientifico());
            statement.setString(3, dto.nombreEspañol());
            statement.setString(4, dto.descripcion());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw PersistenceException.becauseSaveFailed(dto.id(), exception);
        }
        return getById(especie.getId()).orElseThrow();
    }

    @Override
    public Especie update(final Especie especie) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, especie.getNombreEspañol());
            statement.setString(2, especie.getDescripcionGeneral());
            statement.setString(3, especie.getId().getValor());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw PersistenceException.becauseUpdateFailed(especie.getId().getValor(), exception);
        }
        return getById(especie.getId()).orElseThrow();
    }

    @Override
    public Optional<Especie> getById(final EspecieId id) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setString(1, id.getValor());
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(EspeciePersistenceMapper.fromResultSetToModel(resultSet));
        } catch (final SQLException exception) {
            throw PersistenceException.becauseFindByIdFailed(id.getValor(), exception);
        }
    }

    @Override
    public List<Especie> getAll() {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
            final ResultSet resultSet = statement.executeQuery();
            return EspeciePersistenceMapper.fromResultSetToModelList(resultSet);
        } catch (final SQLException exception) {
            throw PersistenceException.becauseFindAllFailed(exception);
        }
    }

    @Override
    public void delete(final EspecieId id) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setString(1, id.getValor());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw PersistenceException.becauseDeleteFailed(id.getValor(), exception);
        }
    }
}
