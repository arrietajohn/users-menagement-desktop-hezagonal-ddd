package com.jcaa.usersmanagement.infrastructure.adapter.persistence.config;

import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.experimental.UtilityClass;

@UtilityClass
public  class DatabaseConnectionFactory {

  public  Connection createConnection(final DatabaseConfig config) {
    try {
      return DriverManager.getConnection(
          config.buildJdbcUrl(), config.username(), config.password());
    } catch (final SQLException exception) {
      throw PersistenceException.becauseConnectionFailed(exception);
    }
  }
}
