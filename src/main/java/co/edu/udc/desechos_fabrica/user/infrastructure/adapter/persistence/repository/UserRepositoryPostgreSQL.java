package co.edu.udc.desechos_fabrica.user.infrastructure.adapter.persistence.repository;

import co.edu.udc.desechos_fabrica.user.application.port.out.DeleteUserPort;
import co.edu.udc.desechos_fabrica.user.application.port.out.GetAllUsersPort;
import co.edu.udc.desechos_fabrica.user.application.port.out.GetUserByEmailPort;
import co.edu.udc.desechos_fabrica.user.application.port.out.SaveUserPort;
import co.edu.udc.desechos_fabrica.user.application.port.out.UpdateUserPort;
import co.edu.udc.desechos_fabrica.user.domain.exception.UserNotFoundException;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserEmail;
import co.edu.udc.desechos_fabrica.user.infrastructure.adapter.persistence.dto.UserPersistenceDto;
import co.edu.udc.desechos_fabrica.user.infrastructure.adapter.persistence.exception.PersistenceException;
import co.edu.udc.desechos_fabrica.user.infrastructure.adapter.persistence.mapper.UserPersistenceMapper;
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
public final class UserRepositoryPostgreSQL
    implements SaveUserPort,
        UpdateUserPort,
        GetUserByEmailPort,
        GetAllUsersPort,
        DeleteUserPort {

  private static final String SQL_INSERT =
      "INSERT INTO \"user\" "
      + "(first_name, last_name, email, password, role, status, created_at, updated_at) "
      + "VALUES (?, ?, ?, ?, ?, ?, NOW(), NOW())";

  private static final String SQL_UPDATE =
      "UPDATE \"user\" SET first_name = ?, last_name = ?, email = ?, password = ?, role = ?, status = ?, updated_at = NOW(), enterprise_id = (SELECT id FROM enterprise WHERE nit = ?) "
      + "WHERE email = ?";

  private static final String SQL_SELECT_BY_EMAIL =
      "SELECT first_name, last_name, email, password, role, status, created_at, updated_at "
      + "FROM \"user\" "
      + "WHERE email = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT first_name, last_name, email, password, role, status, created_at, updated_at "
      + "FROM \"user\" "
      + "ORDER BY first_name ASC";

  private static final String SQL_DELETE =
        "DELETE FROM \"user\" "
        + "WHERE email = ?";

  private final Connection connection;

  @Override
  public UserModel save(final UserModel user) {
    final UserPersistenceDto dto = UserPersistenceMapper.fromModelToDto(user);
    executeSave(dto);
    return findByEmailOrFail(user.getEmail());
  }

  @Override
  public UserModel update(final UserEmail currentEmail, final UserModel userToUpdate) {
    final UserPersistenceDto dto = UserPersistenceMapper.fromModelToDto(userToUpdate);
    executeUpdate(currentEmail, dto);
    return findByEmailOrFail(userToUpdate.getEmail());
  }

  @Override
  public Optional<UserModel> getByEmail(final UserEmail email) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_EMAIL)) {
      statement.setString(1, email.value());
      final ResultSet resultSet = statement.executeQuery();
      if (!resultSet.next()) {
        return Optional.empty();
      }
      return Optional.of(UserPersistenceMapper.fromResultSetToModel(resultSet));
    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindByEmailFailed(email.value(), exception);
    }
  }

  @Override
  public List<UserModel> getAll() {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet resultSet = statement.executeQuery();
      return UserPersistenceMapper.fromResultSetToModelList(resultSet);
    } catch (final SQLException exception) {
      throw PersistenceException.becauseFindAllFailed(exception);
    }
  }

  @Override
  public void delete(final UserEmail userEmail) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
      statement.setString(1, userEmail.value());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseDeleteFailed(userEmail.value(), exception);
    }
  }

  private void executeSave(final UserPersistenceDto dto) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_INSERT)) {
      statement.setString(1, dto.firstName());
      statement.setString(2, dto.lastName());
      statement.setString(3, dto.email());
      statement.setString(4, dto.password());
      statement.setString(5, dto.role());
      statement.setString(6, dto.status());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseSaveFailed(dto.email(), exception);
    }
  }

  private void executeUpdate(final UserEmail currentEmail, final UserPersistenceDto dto) {
    try (final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
      statement.setString(1, dto.firstName());
      statement.setString(2, dto.lastName());
      statement.setString(3, dto.email());
      statement.setString(4, dto.password());
      statement.setString(5, dto.role());
      statement.setString(6, dto.status());
      statement.setString(7, dto.nit());
      statement.setString(8, currentEmail.value());
      statement.executeUpdate();
    } catch (final SQLException exception) {
      throw PersistenceException.becauseUpdateFailed(currentEmail.value(), exception);
    }
  }

  private UserModel findByEmailOrFail(final UserEmail userEmail) {
    return getByEmail(userEmail)
        .orElseThrow(() -> UserNotFoundException.becauseEmailWasNotFound(userEmail.value()));
  }
}
