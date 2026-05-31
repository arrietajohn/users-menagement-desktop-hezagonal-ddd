package com.jcaa.usersmanagement.infrastructure.config;

import com.jcaa.usersmanagement.application.port.in.*;
import com.jcaa.usersmanagement.application.service.*;
import com.jcaa.usersmanagement.infrastructure.adapter.email.JavaMailEmailSenderAdapter;
import com.jcaa.usersmanagement.infrastructure.adapter.email.SmtpConfig;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConfig;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConnectionFactory;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.UserRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.AerolineaRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.AerolineaController;

import java.sql.Connection;
import jakarta.validation.Validator;

public final class DependencyContainer {

  private final UserController userController;
  private final AerolineaController aerolineaController;

  public DependencyContainer() {

    final AppProperties properties = new AppProperties();

    // ===================== DB =====================
    final Connection connection = buildDatabaseConnection(properties);

    final UserRepositoryMySQL userRepository = new UserRepositoryMySQL(connection);
    final AerolineaRepositoryMySQL aerolineaRepository = new AerolineaRepositoryMySQL(connection);

    // ===================== EMAIL =====================
    final JavaMailEmailSenderAdapter emailSender =
            new JavaMailEmailSenderAdapter(buildSmtpConfig(properties));

    final EmailNotificationService emailNotification =
            new EmailNotificationService(emailSender);

    // ===================== VALIDATOR =====================
    final Validator validator = ValidatorProvider.buildValidator();

    // ===================== USERS USE CASES =====================
    final CreateUserUseCase createUserUseCase =
            new CreateUserService(userRepository, userRepository, emailNotification, validator);

    final UpdateUserUseCase updateUserUseCase =
            new UpdateUserService(userRepository, userRepository, userRepository, emailNotification, validator);

    final DeleteUserUseCase deleteUserUseCase =
            new DeleteUserService(userRepository, userRepository, validator);

    final GetUserByIdUseCase getUserByIdUseCase =
            new GetUserByIdService(userRepository, validator);

    final GetAllUsersUseCase getAllUsersUseCase =
            new GetAllUsersService(userRepository);

    final LoginUseCase loginUseCase =
            new LoginService(userRepository, validator);

    // ===================== AEROLINEA USE CASES =====================
    final CreateAerolineaUseCase createAerolineaUseCase =
            new CreateAerolineaService(aerolineaRepository, validator);

    final ListAerolineasUseCase listAerolineasUseCase =
            new ListAerolineasService(aerolineaRepository);

    final DeleteAerolineaUseCase deleteAerolineaUseCase =
            new DeleteAerolineaService(aerolineaRepository);

    // ===================== CONTROLLERS =====================
    this.userController =
            new UserController(
                    createUserUseCase,
                    updateUserUseCase,
                    deleteUserUseCase,
                    getUserByIdUseCase,
                    getAllUsersUseCase,
                    loginUseCase
            );

    this.aerolineaController =
            new AerolineaController(
                    createAerolineaUseCase,
                    listAerolineasUseCase,
                    deleteAerolineaUseCase
            );
  }

  public UserController userController() {
    return userController;
  }

  public AerolineaController aerolineaController() {
    return aerolineaController;
  }

  // ===================== DB CONNECTION =====================
  private static Connection buildDatabaseConnection(final AppProperties properties) {
    final DatabaseConfig config =
            new DatabaseConfig(
                    properties.get("db.host"),
                    properties.getInt("db.port"),
                    properties.get("db.name"),
                    properties.get("db.username"),
                    properties.get("db.password"));

    return DatabaseConnectionFactory.createConnection(config);
  }

  // ===================== SMTP =====================
  private static SmtpConfig buildSmtpConfig(final AppProperties properties) {
    return new SmtpConfig(
            properties.get("smtp.host"),
            properties.getInt("smtp.port"),
            properties.get("smtp.username"),
            properties.get("smtp.password"),
            properties.get("smtp.from.address"),
            properties.get("smtp.from.name"));
  }
}