package edu.udec.ConcesionarioDeAuto.infrastructure.config;

import com.jcaa.usersmanagement.application.port.in.*;
import com.jcaa.usersmanagement.application.service.*;
import edu.udec.ConcesionarioDeAuto.application.port.in.*;
import edu.udec.ConcesionarioDeAuto.application.port.out.EliminarClientePort;
import edu.udec.ConcesionarioDeAuto.infrastructure.adapter.email.JavaMailEmailSenderAdapter;
import edu.udec.ConcesionarioDeAuto.infrastructure.adapter.email.SmtpConfig;
import edu.udec.ConcesionarioDeAuto.infrastructure.adapter.persistence.config.DatabaseConfig;
import edu.udec.ConcesionarioDeAuto.infrastructure.adapter.persistence.config.DatabaseConnectionFactory;
import edu.udec.ConcesionarioDeAuto.infrastructure.adapter.persistence.repository.ClienteRepositorioMySQL;
import edu.udec.ConcesionarioDeAuto.infrastructure.entrypoint.desktop.controller.ControladorCliente;
import jakarta.validation.Validator;

import java.sql.Connection;

public final class DependencyContainer {

  private static final String DB_HOST = "db.host";
  private static final String DB_PORT = "db.port";
  private static final String DB_NAME = "db.name";
  private static final String DB_USER = "db.username";
  private static final String DB_PASSWORD = "db.password";

  private static final String SMTP_HOST = "smtp.host";
  private static final String SMTP_PORT = "smtp.port";
  private static final String SMTP_USER = "smtp.username";
  private static final String SMTP_PASSWORD = "smtp.password";
  private static final String SMTP_FROM = "smtp.from.address";
  private static final String SMTP_FROM_NAME = "smtp.from.name";

  private final ControladorCliente ControladorCliente;

  public DependencyContainer() {
    final AppProperties properties = new AppProperties();

    final Connection connection = buildDatabaseConnection(properties);
    final ClienteRepositorioMySQL ClienteRepositorio = new ClienteRepositorioMySQL(connection);


    // Construir Validator para las validaciones en la capa de aplicación
    final Validator validator = ValidatorProvider.buildValidator();

    final CrearClienteCasoUso createUserUseCase =
        new CreateUserService(ClienteRepositorio, ClienteRepositorio, emailNotification, validator);
    final ActualizarClienteCasoUso updateUserUseCase =
        new UpdateUserService(ClienteRepositorio, ClienteRepositorio, ClienteRepositorio, emailNotification, validator);
    final EliminarClienteCasoUso deleteUserUseCase =
        new DeleteUserService(ClienteRepositorio, ClienteRepositorio, validator);
    final ListarTodoClienteCasoUso getAllUsersUseCase = new GetAllUsersService(ClienteRepositorio);
    final IngresarSistemaCasoUso loginUseCase = new LoginService(ClienteRepositorio, validator);

    this.ControladorCliente =
        new ControladorCliente(
            CrearClienteCasoUso,
            ActualizarClienteCasoUso,
            EliminarClienteCasoUso,
            ListarTodoClienteCasoUso,
            IngresarSistemaCasoUso);
  }

  public ControladorCliente ControladorCliente() {
    return ControladorCliente;
  }

  private static Connection buildDatabaseConnection(final AppProperties properties) {
    final DatabaseConfig config =
        new DatabaseConfig(
            properties.get(DB_HOST),
            properties.getInt(DB_PORT),
            properties.get(DB_NAME),
            properties.get(DB_USER),
            properties.get(DB_PASSWORD));
    return DatabaseConnectionFactory.createConnection(config);
  }

  private static SmtpConfig buildSmtpConfig(final AppProperties properties) {
    return new SmtpConfig(
        properties.get(SMTP_HOST),
        properties.getInt(SMTP_PORT),
        properties.get(SMTP_USER),
        properties.get(SMTP_PASSWORD),
        properties.get(SMTP_FROM),
        properties.get(SMTP_FROM_NAME));
  }
}
