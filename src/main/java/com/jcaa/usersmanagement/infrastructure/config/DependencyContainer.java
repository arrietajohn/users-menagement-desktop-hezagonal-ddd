package com.jcaa.usersmanagement.infrastructure.config;

import com.jcaa.usersmanagement.application.port.in.CreateUserUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteUserUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllUsersUseCase;
import com.jcaa.usersmanagement.application.port.in.GetUserByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.LoginUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateUserUseCase;
import com.jcaa.usersmanagement.application.service.CreateUserService;
import com.jcaa.usersmanagement.application.service.DeleteUserService;
import com.jcaa.usersmanagement.application.service.EmailNotificationService;
import com.jcaa.usersmanagement.application.service.GetAllUsersService;
import com.jcaa.usersmanagement.application.service.GetUserByIdService;
import com.jcaa.usersmanagement.application.service.LoginService;
import com.jcaa.usersmanagement.application.service.UpdateUserService;
import com.jcaa.usersmanagement.infrastructure.adapter.email.JavaMailEmailSenderAdapter;
import com.jcaa.usersmanagement.infrastructure.adapter.email.SmtpConfig;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConfig;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConnectionFactory;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.UserRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.desktop.controller.UserController;

// IMPORTS DE MÉDICOS (Agregados sin eliminar los anteriores)
import com.jcaa.usersmanagement.application.port.in.doctor.CreateDoctorUseCase;
import com.jcaa.usersmanagement.application.port.in.doctor.DeleteDoctorUseCase;
import com.jcaa.usersmanagement.application.port.in.doctor.GetAllDoctorsUseCase;
import com.jcaa.usersmanagement.application.port.in.doctor.GetDoctorByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.doctor.UpdateDoctorUseCase;
import com.jcaa.usersmanagement.application.service.doctor.CreateDoctorService;
import com.jcaa.usersmanagement.application.service.doctor.DeleteDoctorService;
import com.jcaa.usersmanagement.application.service.doctor.GetAllDoctorsService;
import com.jcaa.usersmanagement.application.service.doctor.GetDoctorByIdService;
import com.jcaa.usersmanagement.application.service.doctor.UpdateDoctorService;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.DoctorRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.desktop.controller.doctor.DoctorController;

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

  // Se mantienen ambos controladores expuestos de forma independiente
  private final UserController userController;
  private final DoctorController doctorController;

  public DependencyContainer() {
    final AppProperties properties = new AppProperties();

    final Connection connection = buildDatabaseConnection(properties);

    // Inicialización de ambos repositorios compartiendo la misma conexión JDBC
    final UserRepositoryMySQL userRepository = new UserRepositoryMySQL(connection);
    final DoctorRepositoryMySQL doctorRepository = new DoctorRepositoryMySQL(connection);

    final JavaMailEmailSenderAdapter emailSender =
            new JavaMailEmailSenderAdapter(buildSmtpConfig(properties));
    final EmailNotificationService emailNotification = new EmailNotificationService(emailSender);

    final Validator validator = ValidatorProvider.buildValidator();

    // ==========================================
    // BLOQUE  DE USUARIOS
    // ==========================================
    final CreateUserUseCase createUserUseCase =
            new CreateUserService(userRepository, userRepository, emailNotification, validator);
    final UpdateUserUseCase updateUserUseCase =
            new UpdateUserService(userRepository, userRepository, userRepository, emailNotification, validator);
    final DeleteUserUseCase deleteUserUseCase =
            new DeleteUserService(userRepository, userRepository, validator);
    final GetUserByIdUseCase getUserByIdUseCase = new GetUserByIdService(userRepository, validator);
    final GetAllUsersUseCase getAllUsersUseCase = new GetAllUsersService(userRepository);
    final LoginUseCase loginUseCase = new LoginService(userRepository, validator);

    this.userController =
            new UserController(
                    createUserUseCase,
                    updateUserUseCase,
                    deleteUserUseCase,
                    getUserByIdUseCase,
                    getAllUsersUseCase,
                    loginUseCase);

    // ==========================================
    // BLOQUE DE MÉDICOS
    // ==========================================
    final CreateDoctorUseCase createDoctorUseCase =
            new CreateDoctorService(doctorRepository, doctorRepository);

    final UpdateDoctorUseCase updateDoctorUseCase =
            new UpdateDoctorService(doctorRepository, doctorRepository);

    final DeleteDoctorUseCase deleteDoctorUseCase =
            new DeleteDoctorService(doctorRepository, doctorRepository);

    final GetDoctorByIdUseCase getDoctorByIdUseCase =
            new GetDoctorByIdService(doctorRepository);

    final GetAllDoctorsUseCase getAllDoctorsUseCase =
            new GetAllDoctorsService(doctorRepository);

    this.doctorController =
            new DoctorController(
                    createDoctorUseCase,
                    getDoctorByIdUseCase,
                    getAllDoctorsUseCase,
                    updateDoctorUseCase,
                    deleteDoctorUseCase);
  }

  // Métodos de acceso público para la interfaz CLI
  public UserController userController() {
    return userController;
  }

  public DoctorController doctorController() {
    return doctorController;
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