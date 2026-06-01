package edu.udec.ConcesionarioDeAuto.infrastructure.adapter.persistence.config;

import edu.udec.ConcesionarioDeAuto.infrastructure.adapter.persistence.exception.PersistenceException;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


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
