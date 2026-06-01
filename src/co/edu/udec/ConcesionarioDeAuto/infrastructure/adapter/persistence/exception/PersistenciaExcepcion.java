package edu.udec.ConcesionarioDeAuto.infrastructure.adapter.persistence.exception;

public final class PersistenciaExcepcion extends RuntimeException {

  private static final String MESSAGE_SAVE = "Failed to save cliente with ID: '%s'.";
  private static final String MESSAGE_UPDATE = "Failed to update cliente with ID: '%s'.";
  private static final String MESSAGE_FIND = "Failed to find cliente with ID: '%s'.";
  private static final String MESSAGE_TELEFONO = "Failed to find cliente with telefono: '%s'.";
  private static final String MESSAGE_ALL = "Failed to retrieve all clientes.";
  private static final String MESSAGE_DELETE = "Failed to delete cliente with ID: '%s'.";
  private static final String MESSAGE_CONNECTION = "Could not establish database connection.";

  private PersistenciaExcepcion(final String message, final Throwable cause) {
    super(message, cause);
  }

  public static PersistenciaExcepcion becauseSaveFailed(final String id_cliente, final Throwable cause) {
    return new PersistenciaExcepcion(String.format(MESSAGE_SAVE, id_cliente), cause);
  }

  public static PersistenciaExcepcion becauseUpdateFailed(
          final String id_cliente, final Throwable cause) {
    return new PersistenciaExcepcion(String.format(MESSAGE_UPDATE, id_cliente), cause);
  }

  public static PersistenciaExcepcion becauseFindByIdFailed(
          final String id_cliente, final Throwable cause) {
    return new PersistenciaExcepcion(String.format(MESSAGE_FIND, id_cliente), cause);
  }

  public static PersistenciaExcepcion becauseFindByTelefonoFailed(
          final String telefono, final Throwable cause) {
    return new PersistenciaExcepcion(String.format(MESSAGE_TELEFONO, telefono), cause);
  }

  public static PersistenciaExcepcion becauseFindAllFailed(final Throwable cause) {
    return new PersistenciaExcepcion(MESSAGE_ALL, cause);
  }

  public static PersistenciaExcepcion becauseDeleteFailed(
          final String id_cliente, final Throwable cause) {
    return new PersistenciaExcepcion(String.format(MESSAGE_DELETE, id_cliente), cause);
  }

  public static PersistenciaExcepcion becauseConnectionFailed(final Throwable cause) {
    return new PersistenciaExcepcion(MESSAGE_CONNECTION, cause);
  }
}
