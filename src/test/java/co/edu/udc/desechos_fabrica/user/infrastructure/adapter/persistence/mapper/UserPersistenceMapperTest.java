package co.edu.udc.desechos_fabrica.user.infrastructure.adapter.persistence.mapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import co.edu.udc.desechos_fabrica.user.domain.enums.UserRole;
import co.edu.udc.desechos_fabrica.user.domain.enums.UserStatus;
import co.edu.udc.desechos_fabrica.user.domain.model.UserModel;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserEmail;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserFirstName;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserLastName;
import co.edu.udc.desechos_fabrica.user.domain.valueobject.UserPassword;
import co.edu.udc.desechos_fabrica.user.infrastructure.adapter.persistence.dto.UserPersistenceDto;
import co.edu.udc.desechos_fabrica.user.infrastructure.adapter.persistence.entity.UserEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Tests for UserPersistenceMapper.
 *
 * <p>Covers: fromModelToDto(), fromEntityToModel(), fromResultSetToEntity() (happy path and
 * SQLException propagation), and fromResultSetToModelList() — empty list, multiple rows, and
 * SQLException propagation during iteration.
 */
@DisplayName("UserPersistenceMapper")
@ExtendWith(MockitoExtension.class)
class UserPersistenceMapperTest {
  
  private static final String FIRST_NAME = "John";
  private static final String LAST_NAME = "Doe";
  private static final String EMAIL = "john@example.com";
  private static final String HASH = "$2a$12$abcdefghijklmnopqrstuO";
  private static final String ROLE = "ADMIN";
  private static final String STATUS = "ACTIVE";
  private static final String CREATED_AT = "2024-01-01 00:00:00";
  private static final String UPDATED_AT = "2024-01-02 00:00:00";

  @Mock private ResultSet resultSet;

  private UserModel userModel;
  private UserEntity userEntity;

  @BeforeEach
  void setUp() {
    userModel =
        new UserModel(
            new UserFirstName(FIRST_NAME),
            new UserLastName(LAST_NAME),
            new UserEmail(EMAIL),
            UserPassword.fromHash(HASH),
            UserRole.ADMIN,
            UserStatus.ACTIVE);

    userEntity = new UserEntity(FIRST_NAME, LAST_NAME, EMAIL, HASH, ROLE, STATUS, CREATED_AT, UPDATED_AT);
  }

  // ── fromModelToDto()

  @Test
  @DisplayName("fromModelToDto() maps all UserModel fields and sets null timestamps")
  void shouldMapModelToDto() {
    // Act
    final UserPersistenceDto result = UserPersistenceMapper.fromModelToDto(userModel);

    // Assert
    assertAll(
        "fromModelToDto()",
        () -> assertEquals(FIRST_NAME, result.firstName(), "firstName"),
        () -> assertEquals(LAST_NAME, result.lastName(), "lastName"),
        () -> assertEquals(EMAIL, result.email(), "email"),
        () -> assertEquals(HASH, result.password(), "password"),
        () -> assertEquals(ROLE, result.role(), "role"),
        () -> assertEquals(STATUS, result.status(), "status"),
        () -> assertNull(result.createdAt(), "createdAt must be null"),
        () -> assertNull(result.updatedAt(), "updatedAt must be null"));
  }

  // ── fromEntityToModel()

  @Test
  @DisplayName("fromEntityToModel() maps all UserEntity fields to a domain UserModel")
  void shouldMapEntityToModel() {
    // Act
    final UserModel result = UserPersistenceMapper.fromEntityToModel(userEntity);

    // Assert
    assertAll(
        "fromEntityToModel()",
        () -> assertEquals(FIRST_NAME, result.getFirstName().value(), "firstName"),
        () -> assertEquals(LAST_NAME, result.getLastName().value(), "lastName"),
        () -> assertEquals(EMAIL, result.getEmail().value(), "email"),
        () -> assertEquals(UserRole.ADMIN, result.getRole(), "role"),
        () -> assertEquals(UserStatus.ACTIVE, result.getStatus(), "status"));
  }

  // ── fromResultSetToEntity() — happy path

  @Test
  @DisplayName("fromResultSetToEntity() reads all eight columns from the ResultSet")
  void shouldReadAllColumnsFromResultSet() throws SQLException {
    // Arrange
    when(resultSet.getString("first_name")).thenReturn(FIRST_NAME);
    when(resultSet.getString("last_name")).thenReturn(LAST_NAME);
    when(resultSet.getString("email")).thenReturn(EMAIL);
    when(resultSet.getString("password")).thenReturn(HASH);
    when(resultSet.getString("role")).thenReturn(ROLE);
    when(resultSet.getString("status")).thenReturn(STATUS);
    when(resultSet.getString("created_at")).thenReturn(CREATED_AT);
    when(resultSet.getString("updated_at")).thenReturn(UPDATED_AT);

    // Act
    final UserEntity result = UserPersistenceMapper.fromResultSetToEntity(resultSet);

    // Assert
    assertAll(
        "fromResultSetToEntity()",
        () -> assertEquals(FIRST_NAME, result.firstName(), "firstName"),
        () -> assertEquals(LAST_NAME, result.lastName(), "lastName"),
        () -> assertEquals(EMAIL, result.email(), "email"),
        () -> assertEquals(HASH, result.password(), "password"),
        () -> assertEquals(ROLE, result.role(), "role"),
        () -> assertEquals(STATUS, result.status(), "status"),
        () -> assertEquals(CREATED_AT, result.createdAt(), "createdAt"),
        () -> assertEquals(UPDATED_AT, result.updatedAt(), "updatedAt"));
  }

  // ── fromResultSetToEntity() — SQLException propagation

  @Test
  @DisplayName("fromResultSetToEntity() propagates SQLException when ResultSet read fails")
  void shouldPropagateExceptionFromResultSet() throws SQLException {
    // Arrange
    when(resultSet.getString(anyString())).thenThrow(new SQLException("Column read failed"));

    // Act + Assert
    assertThrows(
        SQLException.class,
        () -> UserPersistenceMapper.fromResultSetToEntity(resultSet),
        "must propagate SQLException when ResultSet throws on getString");
  }

  // ── fromResultSetToModelList() — empty

  @Test
  @DisplayName("fromResultSetToModelList() returns an empty list when ResultSet has no rows")
  void shouldReturnEmptyListWhenResultSetIsEmpty() throws SQLException {
    // Arrange
    when(resultSet.next()).thenReturn(false);

    // Act
    final List<UserModel> result = UserPersistenceMapper.fromResultSetToModelList(resultSet);

    // Assert
    assertTrue(result.isEmpty(), "must return an empty list when ResultSet has no rows");
  }

  // ── fromResultSetToModelList() — multiple rows

  @Test
  @DisplayName("fromResultSetToModelList() returns one model per row in the ResultSet")
  void shouldReturnOneModelPerRow() throws SQLException {
    // Arrange
    when(resultSet.next()).thenReturn(true, true, false);
    when(resultSet.getString("first_name")).thenReturn(FIRST_NAME, "Jane");
    when(resultSet.getString("last_name")).thenReturn(LAST_NAME, "Doe");
    when(resultSet.getString("email")).thenReturn(EMAIL, "jane@example.com");
    when(resultSet.getString("password")).thenReturn(HASH, HASH);
    when(resultSet.getString("role")).thenReturn(ROLE, "MEMBER");
    when(resultSet.getString("status")).thenReturn(STATUS, "PENDING");
    when(resultSet.getString("created_at")).thenReturn(CREATED_AT, CREATED_AT);
    when(resultSet.getString("updated_at")).thenReturn(UPDATED_AT, UPDATED_AT);

    // Act
    final List<UserModel> result = UserPersistenceMapper.fromResultSetToModelList(resultSet);

    // Assert
    assertEquals(2, result.size(), "must return one model per row in the ResultSet");
  }

  // ── fromResultSetToModelList() — SQLException propagation during iteration

  @Test
  @DisplayName("fromResultSetToModelList() propagates SQLException when a row read fails")
  void shouldPropagateExceptionDuringIteration() throws SQLException {
    // Arrange
    when(resultSet.next()).thenReturn(true);
    when(resultSet.getString(anyString())).thenThrow(new SQLException("Row read failed"));

    // Act + Assert
    assertThrows(
        SQLException.class,
        () -> UserPersistenceMapper.fromResultSetToModelList(resultSet),
        "must propagate SQLException when a row fails to be read");
  }
}
