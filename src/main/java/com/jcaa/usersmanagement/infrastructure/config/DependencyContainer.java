package com.jcaa.usersmanagement.infrastructure.config;

import com.jcaa.usersmanagement.application.port.in.CreateDocumentoUseCase;
import com.jcaa.usersmanagement.application.port.in.CreateEmpleadoUseCase;
import com.jcaa.usersmanagement.application.port.in.CreateProyectoUseCase;
import com.jcaa.usersmanagement.application.port.in.CreateTareaUseCase;
import com.jcaa.usersmanagement.application.port.in.CreateUserUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteDocumentoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteEmpleadoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteProyectoUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteTareaUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteUserUseCase;
import com.jcaa.usersmanagement.application.port.in.FindDocumentoByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.FindEmpleadoByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.FindProyectoByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.FindTareaByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllUsersUseCase;
import com.jcaa.usersmanagement.application.port.in.GetUserByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.ListDocumentosUseCase;
import com.jcaa.usersmanagement.application.port.in.ListEmpleadosUseCase;
import com.jcaa.usersmanagement.application.port.in.ListProyectosUseCase;
import com.jcaa.usersmanagement.application.port.in.ListTareasUseCase;
import com.jcaa.usersmanagement.application.port.in.LoginUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateDocumentoUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateEmpleadoUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateProyectoUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateTareaUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateUserUseCase;
import com.jcaa.usersmanagement.application.service.CreateDocumentoService;
import com.jcaa.usersmanagement.application.service.CreateEmpleadoService;
import com.jcaa.usersmanagement.application.service.CreateProyectoService;
import com.jcaa.usersmanagement.application.service.CreateTareaService;
import com.jcaa.usersmanagement.application.service.CreateUserService;
import com.jcaa.usersmanagement.application.service.DeleteDocumentoService;
import com.jcaa.usersmanagement.application.service.DeleteEmpleadoService;
import com.jcaa.usersmanagement.application.service.DeleteProyectoService;
import com.jcaa.usersmanagement.application.service.DeleteTareaService;
import com.jcaa.usersmanagement.application.service.DeleteUserService;
import com.jcaa.usersmanagement.application.service.EmailNotificationService;
import com.jcaa.usersmanagement.application.service.FindDocumentoByIdService;
import com.jcaa.usersmanagement.application.service.FindEmpleadoByIdService;
import com.jcaa.usersmanagement.application.service.FindProyectoByIdService;
import com.jcaa.usersmanagement.application.service.FindTareaByIdService;
import com.jcaa.usersmanagement.application.service.GetAllUsersService;
import com.jcaa.usersmanagement.application.service.GetUserByIdService;
import com.jcaa.usersmanagement.application.service.ListDocumentosService;
import com.jcaa.usersmanagement.application.service.ListEmpleadosService;
import com.jcaa.usersmanagement.application.service.ListProyectosService;
import com.jcaa.usersmanagement.application.service.ListTareasService;
import com.jcaa.usersmanagement.application.service.LoginService;
import com.jcaa.usersmanagement.application.service.UpdateDocumentoService;
import com.jcaa.usersmanagement.application.service.UpdateEmpleadoService;
import com.jcaa.usersmanagement.application.service.UpdateProyectoService;
import com.jcaa.usersmanagement.application.service.UpdateTareaService;
import com.jcaa.usersmanagement.application.service.UpdateUserService;
import com.jcaa.usersmanagement.infrastructure.adapter.email.JavaMailEmailSenderAdapter;
import com.jcaa.usersmanagement.infrastructure.adapter.email.SmtpConfig;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConfig;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.config.DatabaseConnectionFactory;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.DocumentoRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.EmpleadoRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.ProyectoRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.TareaRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.UserRepositoryMySQL;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.DocumentoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.EmpleadoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.ProyectoController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.TareaController;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;

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
  private final ProyectoController proyectoController;
  private final EmpleadoController empleadoController;
  private final TareaController tareaController;
  private final DocumentoController documentoController;

  public DependencyContainer() {
    final AppProperties properties = new AppProperties();

    final Connection connection = buildDatabaseConnection(properties);
    final UserRepositoryMySQL userRepository = new UserRepositoryMySQL(connection);
    final ProyectoRepositoryMySQL proyectoRepository = new ProyectoRepositoryMySQL(connection);
    final EmpleadoRepositoryMySQL empleadoRepository = new EmpleadoRepositoryMySQL(connection);
    final TareaRepositoryMySQL tareaRepository = new TareaRepositoryMySQL(connection);
    final DocumentoRepositoryMySQL documentoRepository = new DocumentoRepositoryMySQL(connection);

    final JavaMailEmailSenderAdapter emailSender =
        new JavaMailEmailSenderAdapter(buildSmtpConfig(properties));
    final EmailNotificationService emailNotification = new EmailNotificationService(emailSender);

    // Construir Validator para las validaciones en la capa de aplicación
    final Validator validator = ValidatorProvider.buildValidator();

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

    final CreateProyectoUseCase createProyectoUseCase =
        new CreateProyectoService(proyectoRepository, validator);
    final FindProyectoByIdUseCase findProyectoByIdUseCase =
        new FindProyectoByIdService(proyectoRepository);
    final UpdateProyectoUseCase updateProyectoUseCase =
        new UpdateProyectoService(proyectoRepository, proyectoRepository, validator);
    final DeleteProyectoUseCase deleteProyectoUseCase =
        new DeleteProyectoService(proyectoRepository, proyectoRepository);
    final ListProyectosUseCase listProyectosUseCase =
        new ListProyectosService(proyectoRepository);

    this.proyectoController =
        new ProyectoController(
            createProyectoUseCase,
            updateProyectoUseCase,
            deleteProyectoUseCase,
            findProyectoByIdUseCase,
            listProyectosUseCase);

    final CreateEmpleadoUseCase createEmpleadoUseCase =
        new CreateEmpleadoService(empleadoRepository, empleadoRepository, validator);
    final FindEmpleadoByIdUseCase findEmpleadoByIdUseCase =
        new FindEmpleadoByIdService(empleadoRepository);
    final UpdateEmpleadoUseCase updateEmpleadoUseCase =
        new UpdateEmpleadoService(empleadoRepository, empleadoRepository, validator);
    final DeleteEmpleadoUseCase deleteEmpleadoUseCase =
        new DeleteEmpleadoService(empleadoRepository, empleadoRepository, validator);
    final ListEmpleadosUseCase listEmpleadosUseCase =
        new ListEmpleadosService(empleadoRepository);

    this.empleadoController =
        new EmpleadoController(
            createEmpleadoUseCase,
            updateEmpleadoUseCase,
            deleteEmpleadoUseCase,
            findEmpleadoByIdUseCase,
            listEmpleadosUseCase);

    final CreateTareaUseCase createTareaUseCase =
        new CreateTareaService(tareaRepository, empleadoRepository, validator);
    final FindTareaByIdUseCase findTareaByIdUseCase =
        new FindTareaByIdService(tareaRepository);
    final UpdateTareaUseCase updateTareaUseCase =
        new UpdateTareaService(tareaRepository, tareaRepository, validator);
    final DeleteTareaUseCase deleteTareaUseCase =
        new DeleteTareaService(tareaRepository, tareaRepository, validator);
    final ListTareasUseCase listTareasUseCase =
        new ListTareasService(tareaRepository);

    this.tareaController =
        new TareaController(
            createTareaUseCase,
            updateTareaUseCase,
            deleteTareaUseCase,
            findTareaByIdUseCase,
            listTareasUseCase);

    final CreateDocumentoUseCase createDocumentoUseCase =
        new CreateDocumentoService(documentoRepository, empleadoRepository, validator);
    final FindDocumentoByIdUseCase findDocumentoByIdUseCase =
        new FindDocumentoByIdService(documentoRepository);
    final UpdateDocumentoUseCase updateDocumentoUseCase =
        new UpdateDocumentoService(documentoRepository, documentoRepository, validator);
    final DeleteDocumentoUseCase deleteDocumentoUseCase =
        new DeleteDocumentoService(documentoRepository, documentoRepository, validator);
    final ListDocumentosUseCase listDocumentosUseCase =
        new ListDocumentosService(documentoRepository);

    this.documentoController =
        new DocumentoController(
            createDocumentoUseCase,
            updateDocumentoUseCase,
            deleteDocumentoUseCase,
            findDocumentoByIdUseCase,
            listDocumentosUseCase);
  }

  public UserController userController() {
    return userController;
  }

  public ProyectoController proyectoController() {
    return proyectoController;
  }

  public EmpleadoController empleadoController() {
    return empleadoController;
  }

  public TareaController tareaController() {
    return tareaController;
  }

  public DocumentoController documentoController() {
    return documentoController;
  }

  private static Connection buildDatabaseConnection(final AppProperties properties) {
    final DatabaseConfig config =
        new DatabaseConfig(
            properties.get(DB_HOST),
            properties.getInt(DB_PORT),
            properties.get(DB_NAME),
            properties.get(DB_USER),
            properties.get(DB_PASSWORD));
    // VIOLACIÓN Regla 4 (consecuencia): DatabaseConnectionFactory ya no tiene @UtilityClass,
    // por lo que debe instanciarse para llamar a createConnection.
    return new DatabaseConnectionFactory().createConnection(config);
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
