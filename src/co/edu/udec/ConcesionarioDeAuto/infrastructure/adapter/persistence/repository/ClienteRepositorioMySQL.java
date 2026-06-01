package edu.udec.ConcesionarioDeAuto.infrastructure.adapter.persistence.repository;

import edu.udec.ConcesionarioDeAuto.application.port.out.ActualizarClientePort;
import edu.udec.ConcesionarioDeAuto.application.port.out.ConsultarClientePort;
import edu.udec.ConcesionarioDeAuto.application.port.out.EliminarClientePort;
import edu.udec.ConcesionarioDeAuto.application.port.out.GuardarClientePort;
import edu.udec.ConcesionarioDeAuto.domain.exceptions.DniInvalidoExcepcion;
import edu.udec.ConcesionarioDeAuto.domain.model.Cliente;
import edu.udec.ConcesionarioDeAuto.domain.valueobject.Cliente.ClienteId;
import edu.udec.ConcesionarioDeAuto.infrastructure.adapter.persistence.dto.PersistenciaClienteDto;
import edu.udec.ConcesionarioDeAuto.infrastructure.adapter.persistence.exception.PersistenceException;
import edu.udec.ConcesionarioDeAuto.infrastructure.adapter.persistence.mapper.PersistenciaClienteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Log
@RequiredArgsConstructor
public final class ClienteRepositorioMySQL
    implements ActualizarClientePort,
        GuardarClientePort,
        ConsultarClientePort,
        EliminarClientePort {

  private static final String SQL_INSERT =
      "INSERT INTO cliente "
      + "((id_cliente, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, created_at, updated_at)) "
      + "VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

  private static final String SQL_UPDATE =
      "UPDATE cliente SET primerNombre = ?, segundoNombre = ?, primerApellido= ?, segundoApellido = ? direccion = ?, telefono = ?, , updated_at = NOW() "
      + "WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
      "SELECT (id_cliente, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, created_at, updated_at) "
      + "FROM cliente "
      + "WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT (id_cliente, primerNombre, segundoNombre, primerApellido, segundoApellido, direccion, telefono, created_at, updated_at) "
      + "FROM cliente "
      + "ORDER BY name ASC";

  private static final String SQL_DELETE =
        "DELETE FROM cliente "
        + "WHERE id = ?";

  private final Connection connection;

  @Override
  public Cliente guardar(final Cliente cliente) {
    final PersistenciaClienteDto dto = PersistenciaClienteMapper.fromModelToDto(cliente);
    executeSave(dto);
    return findByIdOrFail(cliente.getId());
  }

  @Override
  public Cliente actualizar(final Cliente cliente) {
    final PersistenciaClienteDto dto = PersistenciaClienteMapper.fromModelToDto(cliente);
    executeUpdate(dto);
    return findByIdOrFail(cliente.getId());
  }

  @Override
  public Optional<Cliente> consultarCliente(final ClienteId clienteId) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      statement.setString(1, clienteId.value());
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(PersistenciaClienteMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindByIdFailed(clienteId.value(), exception);
    }
  }


  @Override
  public List<Cliente> ListarCliente() {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet resultSet = statement.executeQuery();
      return PersistenciaClienteMapper.fromResultSetToList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindAllFailed(exception);
    }
  }

  @Override
  public void eliminar(final ClienteId clienteId) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
      statement.setString(1, clienteId.value());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseDeleteFailed(clienteId.value(), exception);
    }
  }

  private void executeSave(final PersistenciaClienteDto dto) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
      statement.setString(1, dto.id_cliente());
      statement.setString(1, dto.primerNombre());
      statement.setString(1, dto.segundoNombre());
      statement.setString(1, dto.primerApellido());
      statement.setString(1, dto.segundoApellido());
      statement.setString(1, dto.direccion());
      statement.setString(1, dto.telefono());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseSaveFailed(dto.id_cliente(), exception);
    }
  }

  private void executeUpdate(final PersistenciaClienteDto dto) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
      statement.setString(1, dto.id_cliente());
      statement.setString(2, dto.primerNombre());
      statement.setString(3, dto.segundoNombre());
      statement.setString(4, dto.primerApellido());
      statement.setString(5, dto.segundoApellido());
      statement.setString(6, dto.direccion());
      statement.setString(7, dto.telefono());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseUpdateFailed(dto.id_cliente(), exception);
    }
  }

  private Cliente findByIdOrFail(final ClienteId clienteId) {
    return getById(clienteId)
        .orElseThrow(() -> DniInvalidoExcepcion.(clienteId.value()));
  }
}
