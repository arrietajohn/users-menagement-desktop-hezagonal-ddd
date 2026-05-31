package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.*;
import com.jcaa.usersmanagement.domain.model.ClienteModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.ClientePersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.ClientePersistenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import java.sql.*;
import java.util.List;
import java.util.Optional;

@Log
@RequiredArgsConstructor
public final class ClienteRepositoryMySQL
        implements SaveClientePort,
        UpdateClientePort,
        GetClienteByIdPort,
        GetAllClientesPort,
        DeleteClientePort {

    private static final String SQL_INSERT =
            "INSERT INTO cliente (nombre, apellido, email, telefono, direccion) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE =
            "UPDATE cliente SET nombre=?, apellido=?, email=?, telefono=?, direccion=? WHERE id_cliente=?";
    private static final String SQL_SELECT_BY_ID =
            "SELECT id_cliente, nombre, apellido, email, telefono, direccion FROM cliente WHERE id_cliente=? LIMIT 1";
    private static final String SQL_SELECT_ALL =
            "SELECT id_cliente, nombre, apellido, email, telefono, direccion FROM cliente ORDER BY apellido ASC";
    private static final String SQL_DELETE =
            "DELETE FROM cliente WHERE id_cliente=?";

    private final Connection connection;

    @Override
    public ClienteModel save(final ClienteModel cliente) {
        try (final PreparedStatement st = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, cliente.getNombre());
            st.setString(2, cliente.getApellido());
            st.setString(3, cliente.getEmail());
            st.setString(4, cliente.getTelefono());
            st.setString(5, cliente.getDireccion());
            st.executeUpdate();
            final ResultSet keys = st.getGeneratedKeys();
            if (keys.next()) {
                return getById(keys.getInt(1)).orElseThrow();
            }
            throw new RuntimeException("No se pudo obtener el ID generado");
        } catch (final SQLException e) {
            throw PersistenceException.becauseSaveFailed("cliente", e);
        }
    }

    @Override
    public ClienteModel update(final ClienteModel cliente) {
        try (final PreparedStatement st = connection.prepareStatement(SQL_UPDATE)) {
            st.setString(1, cliente.getNombre());
            st.setString(2, cliente.getApellido());
            st.setString(3, cliente.getEmail());
            st.setString(4, cliente.getTelefono());
            st.setString(5, cliente.getDireccion());
            st.setInt(6, cliente.getIdCliente());
            st.executeUpdate();
        } catch (final SQLException e) {
            throw PersistenceException.becauseUpdateFailed(cliente.getIdCliente().toString(), e);
        }
        return getById(cliente.getIdCliente()).orElseThrow();
    }

    @Override
    public Optional<ClienteModel> getById(final Integer idCliente) {
        try (final PreparedStatement st = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            st.setInt(1, idCliente);
            final ResultSet rs = st.executeQuery();
            if (!rs.next()) return Optional.empty();
            return Optional.of(ClientePersistenceMapper.fromResultSetToModel(rs));
        } catch (final SQLException e) {
            throw PersistenceException.becauseFindByIdFailed(idCliente.toString(), e);
        }
    }

    @Override
    public List<ClienteModel> getAll() {
        try (final PreparedStatement st = connection.prepareStatement(SQL_SELECT_ALL)) {
            final ResultSet rs = st.executeQuery();
            return ClientePersistenceMapper.fromResultSetToModelList(rs);
        } catch (final SQLException e) {
            throw PersistenceException.becauseFindAllFailed(e);
        }
    }

    @Override
    public void delete(final Integer idCliente) {
        try (final PreparedStatement st = connection.prepareStatement(SQL_DELETE)) {
            st.setInt(1, idCliente);
            st.executeUpdate();
        } catch (final SQLException e) {
            throw PersistenceException.becauseDeleteFailed(idCliente.toString(), e);
        }
    }
}
