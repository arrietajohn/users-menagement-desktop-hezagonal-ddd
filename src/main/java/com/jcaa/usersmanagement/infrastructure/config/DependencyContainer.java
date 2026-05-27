package com.jcaa.usersmanagement.infrastructure.config;

import com.jcaa.usersmanagement.application.port.in.CreateRangoMilitarUseCase;
import com.jcaa.usersmanagement.application.port.in.CreateUserUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteRangoMilitarUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteUserUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllRangosMilitaresUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllUsersUseCase;
import com.jcaa.usersmanagement.application.port.in.GetRangoMilitarByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.GetUserByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.LoginUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateRangoMilitarUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateUserUseCase;
import com.jcaa.usersmanagement.application.service.CreateRangoMilitarService;
import com.jcaa.usersmanagement.application.service.CreateUserService;
import com.jcaa.usersmanagement.application.service.DeleteRangoMilitarService;
import com.jcaa.usersmanagement.application.service.DeleteUserService;
import com.jcaa.usersmanagement.application.service.EmailNotificationService;
import com.jcaa.usersmanagement.application.service.GetAllRangosMilitaresService;
import com.jcaa.usersmanagement.application.service.GetAllUsersService;
import com.jcaa.usersmanagement.application.service.GetRangoMilitarByIdService;
import com.jcaa.usersmanagement.application.service.GetUserByIdService;
import com.jcaa.usersmanagement.application.service.LoginService;
import com.jcaa.usersmanagement.application.service.UpdateRangoMilitarService;
import com.jcaa.usersmanagement.application.service.UpdateUserService;
import com.jcaa.usersmanagement.infrastructure.adapter.email.JavaMailEmailSenderAdapter;
import com.jcaa.usersmanagement.infrastructure.adapter.email.SmtpConfig;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConfig;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConnectionFactory;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.RangoMilitarRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.UserRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.RangoMilitarController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;

import java.sql.Connection;
import jakarta.validation.Validator;

public final class DependencyContainer {

  private static final String DB_HOST     = "db.host";
  private static final String DB_PORT     = "db.port";
  private static final String DB_NAME     = "db.name";
  private static final String DB_USER     = "db.username";
  private static final String DB_PASSWORD = "db.password";

  private static final String SMTP_HOST      = "smtp.host";
  private static final String SMTP_PORT      = "smtp.port";
  private static final String SMTP_USER      = "smtp.username";
  private static final String SMTP_PASSWORD  = "smtp.password";
  private static final String SMTP_FROM      = "smtp.from.address";
  private static final String SMTP_FROM_NAME = "smtp.from.name";

  private final UserController userController;
  private final RangoMilitarController rangoMilitarController;

  public DependencyContainer() {
    final AppProperties properties = new AppProperties();
    final Connection connection = buildDatabaseConnection(properties);
    final Validator validator = ValidatorProvider.buildValidator();

    // ── Users ──────────────────────────────────────────────
    final UserRepositoryMySQL userRepository = new UserRepositoryMySQL(connection);
    final JavaMailEmailSenderAdapter emailSender =
            new JavaMailEmailSenderAdapter(buildSmtpConfig(properties));
    final EmailNotificationService emailNotification =
            new EmailNotificationService(emailSender);

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

    this.userController = new UserController(
            createUserUseCase, updateUserUseCase, deleteUserUseCase,
            getUserByIdUseCase, getAllUsersUseCase, loginUseCase);

    // ── Rangos Militares ───────────────────────────────────
    final RangoMilitarRepositoryMySQL rangoRepository =
            new RangoMilitarRepositoryMySQL(connection);

    final CreateRangoMilitarUseCase createRangoUseCase =
            new CreateRangoMilitarService(rangoRepository, rangoRepository, validator);
    final UpdateRangoMilitarUseCase updateRangoUseCase =
            new UpdateRangoMilitarService(rangoRepository, rangoRepository, validator);
    final DeleteRangoMilitarUseCase deleteRangoUseCase =
            new DeleteRangoMilitarService(rangoRepository, rangoRepository, validator);
    final GetRangoMilitarByIdUseCase getRangoByIdUseCase =
            new GetRangoMilitarByIdService(rangoRepository, validator);
    final GetAllRangosMilitaresUseCase getAllRangosUseCase =
            new GetAllRangosMilitaresService(rangoRepository);

    this.rangoMilitarController = new RangoMilitarController(
            createRangoUseCase, updateRangoUseCase, deleteRangoUseCase,
            getRangoByIdUseCase, getAllRangosUseCase);
  }

  public UserController userController() {
    return userController;
  }

  public RangoMilitarController rangoMilitarController() {
    return rangoMilitarController;
  }
