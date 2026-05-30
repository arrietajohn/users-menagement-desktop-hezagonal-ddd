package co.edu.udc.desechos_fabrica.user.infrastructure.config;

import co.edu.udc.desechos_fabrica.user.application.port.in.*;
import co.edu.udc.desechos_fabrica.user.application.service.CreateUserService;
import co.edu.udc.desechos_fabrica.user.application.service.DeleteUserService;
import co.edu.udc.desechos_fabrica.user.application.service.EmailNotificationService;
import co.edu.udc.desechos_fabrica.user.application.service.GetAllUsersService;
import co.edu.udc.desechos_fabrica.user.application.service.GetUserByEmailService;
import co.edu.udc.desechos_fabrica.user.application.service.LoginService;
import co.edu.udc.desechos_fabrica.user.application.service.UpdateUserService;
import co.edu.udc.desechos_fabrica.user.infrastructure.adapter.email.JavaMailEmailSenderAdapter;
import co.edu.udc.desechos_fabrica.user.infrastructure.adapter.email.SmtpConfig;
import co.edu.udc.desechos_fabrica.user.infrastructure.adapter.persistence.config.DatabaseConfig;
import co.edu.udc.desechos_fabrica.user.infrastructure.adapter.persistence.config.DatabaseConnectionFactory;
import co.edu.udc.desechos_fabrica.user.infrastructure.adapter.persistence.repository.UserRepositoryPostgreSQL;
import co.edu.udc.desechos_fabrica.user.infrastructure.entrypoint.desktop.controller.UserController;

import java.sql.Connection;
import jakarta.validation.Validator;

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

  private final UserController userController;

  public DependencyContainer() {
    final AppProperties properties = new AppProperties();

    final Connection connection = buildDatabaseConnection(properties);
    final UserRepositoryPostgreSQL userRepository = new UserRepositoryPostgreSQL(connection);

    final JavaMailEmailSenderAdapter emailSender =
        new JavaMailEmailSenderAdapter(buildSmtpConfig(properties));
    final EmailNotificationService emailNotification = new EmailNotificationService(emailSender);

    // Construir Validator para las validaciones en la capa de aplicación
    final Validator validator = ValidatorProvider.buildValidator();

    final CreateUserUseCase createUserUseCase =
        new CreateUserService(userRepository, userRepository, emailNotification, validator);
    final UpdateUserUseCase updateUserUseCase =
        new UpdateUserService(userRepository, userRepository, emailNotification, validator);
    final DeleteUserUseCase deleteUserUseCase =
        new DeleteUserService(userRepository, userRepository, validator);
    final GetUserByEmailUseCase getUserByEmailUseCase = new GetUserByEmailService(userRepository, validator);
    final GetAllUsersUseCase getAllUsersUseCase = new GetAllUsersService(userRepository);
    final LoginUseCase loginUseCase = new LoginService(userRepository, validator);

    this.userController =
        new UserController(
            createUserUseCase,
            updateUserUseCase,
            deleteUserUseCase,
            getUserByEmailUseCase,
            getAllUsersUseCase,
            loginUseCase);
  }

  public UserController userController() {
    return userController;
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
