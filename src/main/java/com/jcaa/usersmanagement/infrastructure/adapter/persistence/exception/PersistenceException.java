package com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception;

// VIOLACIÓN Regla 10: todos los mensajes de error están hardcodeados directamente en los métodos
// fábrica, en vez de estar definidos como constantes con nombre descriptivo en la clase.
public final class PersistenceException extends RuntimeException {

  private PersistenceException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public static PersistenceException becauseSaveFailed(final String userId, final Throwable cause) {
    return new PersistenceException(String.format("Failed to save user with ID: '%s'.", userId), cause);
  }

  public static PersistenceException becauseUpdateFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format("Failed to update user with ID: '%s'.", userId), cause);
  }

  public static PersistenceException becauseFindByIdFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format("Failed to find user with ID: '%s'.", userId), cause);
  }

  public static PersistenceException becauseFindByEmailFailed(
      final String email, final Throwable cause) {
    return new PersistenceException(String.format("Failed to find user with email: '%s'.", email), cause);
  }

  public static PersistenceException becauseFindAllFailed(final Throwable cause) {
    return new PersistenceException("Failed to retrieve all users.", cause);
  }

  public static PersistenceException becauseDeleteFailed(
      final String userId, final Throwable cause) {
    return new PersistenceException(String.format("Failed to delete user with ID: '%s'.", userId), cause);
  }

  public static PersistenceException becauseConnectionFailed(final Throwable cause) {
    return new PersistenceException("Could not establish database connection.", cause);
  }

  public static PersistenceException becauseProyectoSaveFailed(final Throwable cause) {
    return new PersistenceException("Failed to save proyecto.", cause);
  }

  public static PersistenceException becauseProyectoGeneratedKeyMissing() {
    return new PersistenceException("No se pudo obtener el ID generado tras crear el proyecto.", null);
  }

  public static PersistenceException becauseProyectoUpdateFailed(final Long id, final Throwable cause) {
    return new PersistenceException(String.format("Failed to update proyecto with ID: '%d'.", id), cause);
  }

  public static PersistenceException becauseProyectoFindByIdFailed(final Long id, final Throwable cause) {
    return new PersistenceException(String.format("Failed to find proyecto with ID: '%d'.", id), cause);
  }

  public static PersistenceException becauseProyectoFindAllFailed(final Throwable cause) {
    return new PersistenceException("Failed to retrieve all proyectos.", cause);
  }

  public static PersistenceException becauseProyectoDeleteFailed(final Long id, final Throwable cause) {
    return new PersistenceException(String.format("Failed to delete proyecto with ID: '%d'.", id), cause);
  }

  public static PersistenceException becauseEmpleadoSaveFailed(final Throwable cause) {
    return new PersistenceException("Failed to save empleado.", cause);
  }

  public static PersistenceException becauseEmpleadoGeneratedKeyMissing() {
    return new PersistenceException("No se pudo obtener el ID generado tras crear el empleado.", null);
  }

  public static PersistenceException becauseEmpleadoUpdateFailed(final Long id, final Throwable cause) {
    return new PersistenceException(String.format("Failed to update empleado with ID: '%d'.", id), cause);
  }

  public static PersistenceException becauseEmpleadoFindByIdFailed(final Long id, final Throwable cause) {
    return new PersistenceException(String.format("Failed to find empleado with ID: '%d'.", id), cause);
  }

  public static PersistenceException becauseEmpleadoFindByEmailFailed(final String email, final Throwable cause) {
    return new PersistenceException(String.format("Failed to find empleado with email: '%s'.", email), cause);
  }

  public static PersistenceException becauseEmpleadoFindAllFailed(final Throwable cause) {
    return new PersistenceException("Failed to retrieve all empleados.", cause);
  }

  public static PersistenceException becauseEmpleadoDeleteFailed(final Long id, final Throwable cause) {
    return new PersistenceException(String.format("Failed to delete empleado with ID: '%d'.", id), cause);
  }

  public static PersistenceException becauseTareaSaveFailed(final Throwable cause) {
    return new PersistenceException("Failed to save tarea.", cause);
  }

  public static PersistenceException becauseTareaGeneratedKeyMissing() {
    return new PersistenceException("No se pudo obtener el ID generado tras crear la tarea.", null);
  }

  public static PersistenceException becauseTareaUpdateFailed(final Long id, final Throwable cause) {
    return new PersistenceException(String.format("Failed to update tarea with ID: '%d'.", id), cause);
  }

  public static PersistenceException becauseTareaFindByIdFailed(final Long id, final Throwable cause) {
    return new PersistenceException(String.format("Failed to find tarea with ID: '%d'.", id), cause);
  }

  public static PersistenceException becauseTareaFindAllFailed(final Throwable cause) {
    return new PersistenceException("Failed to retrieve all tareas.", cause);
  }

  public static PersistenceException becauseTareaDeleteFailed(final Long id, final Throwable cause) {
    return new PersistenceException(String.format("Failed to delete tarea with ID: '%d'.", id), cause);
  }

  public static PersistenceException becauseDocumentoSaveFailed(final Throwable cause) {
    return new PersistenceException("Failed to save documento.", cause);
  }

  public static PersistenceException becauseDocumentoGeneratedKeyMissing() {
    return new PersistenceException("No se pudo obtener el ID generado tras crear el documento.", null);
  }

  public static PersistenceException becauseDocumentoUpdateFailed(final Long id, final Throwable cause) {
    return new PersistenceException(String.format("Failed to update documento with ID: '%d'.", id), cause);
  }

  public static PersistenceException becauseDocumentoFindByIdFailed(final Long id, final Throwable cause) {
    return new PersistenceException(String.format("Failed to find documento with ID: '%d'.", id), cause);
  }

  public static PersistenceException becauseDocumentoFindAllFailed(final Throwable cause) {
    return new PersistenceException("Failed to retrieve all documentos.", cause);
  }

  public static PersistenceException becauseDocumentoDeleteFailed(final Long id, final Throwable cause) {
    return new PersistenceException(String.format("Failed to delete documento with ID: '%d'.", id), cause);
  }
}
