package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.*;
import com.jcaa.usersmanagement.domain.model.Vehiculomodel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.VehiculoPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.VehiculoPersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.sql.*;
import java.util.List;
import java.util.Optional;

@Log
@RequiredArgsConstructor
public final class VehiculoRepositoryMySQL
        implements SaveVehiculoPort,
        UpdateVehiculoPort,
        GetVehiculoByIdPort,
        GetAllVehiculosPort,
        DeleteVehiculoPort {

    private static final String SQL_INSERT =
            "INSERT INTO vehiculo (id_bastidor, precio, cilindrada, potencia, estado, id_modelo, id_servicio) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE =
            "UPDATE vehiculo SET precio=?, cilindrada=?, potencia=?, estado=?, id_modelo=?, id_servicio=? " +
                    "WHERE id_bastidor=?";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id_bastidor, precio, cilindrada, potencia, estado, id_modelo, id_servicio " +
                    "FROM vehiculo WHERE id_bastidor=? LIMIT 1";

    private static final String SQL_SELECT_ALL =
            "SELECT id_bastidor, precio, cilindrada, potencia, estado, id_modelo, id_servicio " +
                    "FROM vehiculo ORDER BY id_bastidor ASC";

    private static final String SQL_DELETE =
            "DELETE FROM vehiculo WHERE id_bastidor=?";

    private final Connection connection;

    @Override
    public Vehiculomodel save(final Vehiculomodel vehiculo) {
        final VehiculoPersistenceDto dto = VehiculoPersistenceMapper.fromModelToDto(vehiculo);
        try (final PreparedStatement st = connection.prepareStatement(SQL_INSERT)) {
            st.setInt(1, dto.idBastidor());
            st.setBigDecimal(2, dto.precio());
            st.setString(3, dto.cilindrada());
            st.setString(4, dto.potencia());
            st.setString(5, dto.estado());
            st.setInt(6, dto.idModelo());
            st.setInt(7, dto.idServicio());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw PersistenceException.becauseSaveFailed(dto.idBastidor().toString(), e);
        }
        return getById(vehiculo.getIdBastidor())
                .orElseThrow(() -> new RuntimeException(
                        "Vehículo no encontrado después de guardar: " + vehiculo.getIdBastidor()));
    }

    @Override
    public Vehiculomodel update(final Vehiculomodel vehiculo) {
        final VehiculoPersistenceDto dto = VehiculoPersistenceMapper.fromModelToDto(vehiculo);
        try (final PreparedStatement st = connection.prepareStatement(SQL_UPDATE)) {
            st.setBigDecimal(1, dto.precio());
            st.setString(2, dto.cilindrada());
            st.setString(3, dto.potencia());
            st.setString(4, dto.estado());
            st.setInt(5, dto.idModelo());
            st.setInt(6, dto.idServicio());
            st.setInt(7, dto.idBastidor());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw PersistenceException.becauseUpdateFailed(dto.idBastidor().toString(), e);
        }
        return getById(vehiculo.getIdBastidor()).orElseThrow();
    }

    @Override
    public Optional<Vehiculomodel> getById(final Integer idBastidor) {
        try (final PreparedStatement st = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            st.setInt(1, idBastidor);
            final ResultSet rs = st.executeQuery();
            if (!rs.next()) return Optional.empty();
            return Optional.of(VehiculoPersistenceMapper.fromResultSetToModel(rs));
        } catch (final SQLException e) {
            throw PersistenceException.becauseFindByIdFailed(idBastidor.toString(), e);
        }
    }

    @Override
    public List<Vehiculomodel> getAll() {
        try (final PreparedStatement st = connection.prepareStatement(SQL_SELECT_ALL)) {
            final ResultSet rs = st.executeQuery();
            return VehiculoPersistenceMapper.fromResultSetToModelList(rs);
        } catch (final SQLException e) {
            throw PersistenceException.becauseFindAllFailed(e);
        }
    }

    @Override
    public void delete(final Integer idBastidor) {
        try (final PreparedStatement st = connection.prepareStatement(SQL_DELETE)) {
            st.setInt(1, idBastidor);
            st.executeUpdate();
        } catch (final SQLException e) {
            throw PersistenceException.becauseDeleteFailed(idBastidor.toString(), e);
        }
    }
}