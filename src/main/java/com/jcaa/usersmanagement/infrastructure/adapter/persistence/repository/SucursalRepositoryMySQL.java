package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteSucursalPort;
import com.jcaa.usersmanagement.application.port.out.GetSucursalByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveSucursalPort;
import com.jcaa.usersmanagement.application.port.out.UpdateSucursalPort;
import com.jcaa.usersmanagement.domain.exception.SucursalNotFoundException;
import com.jcaa.usersmanagement.domain.model.SucursalModel;
import com.jcaa.usersmanagement.domain.valueobject.SucursaId;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.SucursalPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.SucursalPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Log
@RequiredArgsConstructor
public final class SucursalRepositoryMySQL
        implements SaveSucursalPort,
        UpdateSucursalPort,
        GetSucursalByIdPort,
        DeleteSucursalPort {

    private static final String SQL_INSERT =
            "INSERT INTO sucursal (id, numero_sucursal, direccion, codigo_postal, ciudad_operando, id_banco) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE sucursal SET numero_sucursal = ?, direccion = ?, codigo_postal = ?, "
                    + "ciudad_operando = ?, id_banco = ? WHERE id = ?";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, numero_sucursal, direccion, codigo_postal, ciudad_operando, id_banco "
                    + "FROM sucursal WHERE id = ? LIMIT 1";

    private static final String SQL_DELETE =
            "DELETE FROM sucursal WHERE id = ?";

    private final Connection connection;

    @Override
    public SucursalModel save(final SucursalModel sucursal) {
        final SucursalPersistenceDto dto = SucursalPersistenceMapper.fromModelToDto(sucursal);
        executeSave(dto);
        return findByIdOrFail(sucursal.getId());
    }

    @Override
    public SucursalModel update(final SucursalModel sucursal) {
        final SucursalPersistenceDto dto = SucursalPersistenceMapper.fromModelToDto(sucursal);
        executeUpdate(dto);
        return findByIdOrFail(sucursal.getId());
    }

    @Override
    public Optional<SucursalModel> getById(final SucursaId sucursalId) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setString(1, sucursalId.value());
            final ResultSet resultSet = statement.executeQuery();
            if (!resultSet.next()) {
                return Optional.empty();
            }
            return Optional.of(SucursalPersistenceMapper.fromResultSetToModel(resultSet));
        } catch (final SQLException exception) {
            throw PersistenceException.becauseFindByIdFailed(sucursalId.value(), exception);
        }
    }

    @Override
    public void delete(final SucursaId sucursalId) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setString(1, sucursalId.value());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw PersistenceException.becauseDeleteFailed(sucursalId.value(), exception);
        }
    }

    private void executeSave(final SucursalPersistenceDto dto) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
            statement.setString(1, dto.id());
            statement.setString(2, dto.numero());
            statement.setString(3, dto.direccion());
            statement.setString(4, dto.codigoPostal());
            statement.setString(5, dto.ciudad());
            statement.setString(6, dto.bancoId());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw PersistenceException.becauseSaveFailed(dto.id(), exception);
        }
    }

    private void executeUpdate(final SucursalPersistenceDto dto) {
        try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, dto.numero());
            statement.setString(2, dto.direccion());
            statement.setString(3, dto.codigoPostal());
            statement.setString(4, dto.ciudad());
            statement.setString(5, dto.bancoId());
            statement.setString(6, dto.id());
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw PersistenceException.becauseUpdateFailed(dto.id(), exception);
        }
    }

    private SucursalModel findByIdOrFail(final SucursaId sucursalId) {
        return getById(sucursalId)
                .orElseThrow(() -> SucursalNotFoundException.becauseIdWasNotFound(sucursalId.value()));
    }
}