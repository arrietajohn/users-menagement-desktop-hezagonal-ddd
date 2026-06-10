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
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.controller.UserController;

// Casos de uso para la unidad 3
import com.jcaa.usersmanagement.application.service.ActualizarEstadoProyectoService;
import com.jcaa.usersmanagement.application.service.CambiarPromotorProyectoService;
import com.jcaa.usersmanagement.application.service.ProrrogarFechaFinService;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository.ProyectoRepositoryMySQL;

// NUEVOS IMPORTS PARA LOS CASOS DE USO DE CONSULTA - UNIDAD 4
import com.jcaa.usersmanagement.application.service.ListarProyectosEnCursoService;
import com.jcaa.usersmanagement.application.service.BuscarProyectosPorRangoFechasService;
import com.jcaa.usersmanagement.application.service.ListarProyectosPorPromotorService;
import com.jcaa.usersmanagement.application.service.FiltrarProyectosPorEstadoService;
import com.jcaa.usersmanagement.application.service.BuscarProyectosPorDenominacionService;

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

  // Propiedades privadas para los servicios de proyectos (Unidad 3)
  private final ActualizarEstadoProyectoService actualizarEstadoProyectoService;
  private final ProrrogarFechaFinService prorrogarFechaFinService;
  private final CambiarPromotorProyectoService cambiarPromotorProyectoService;

  //  PROPIEDADES PRIVADAS PARA LOS NUEVOS SERVICIOS DE CONSULTA (Unidad 4)
  private final ListarProyectosEnCursoService listarProyectosEnCursoService;
  private final BuscarProyectosPorRangoFechasService buscarProyectosPorRangoFechasService;
  private final ListarProyectosPorPromotorService listarProyectosPorPromotorService;
  private final FiltrarProyectosPorEstadoService filtrarProyectosPorEstadoService;
  private final BuscarProyectosPorDenominacionService buscarProyectosPorDenominacionService;

  public DependencyContainer() {
    final AppProperties properties = new AppProperties();

    final Connection connection = buildDatabaseConnection(properties);
    final UserRepositoryMySQL userRepository = new UserRepositoryMySQL(connection);

    // Instanciación del adaptador de persistencia de proyectos
    final ProyectoRepositoryMySQL proyectoRepository = new ProyectoRepositoryMySQL(connection);

    final JavaMailEmailSenderAdapter emailSender =
            new JavaMailEmailSenderAdapter(buildSmtpConfig(properties));
    final EmailNotificationService emailNotification = new EmailNotificationService(emailSender);

    // Construccion Validator para las validaciones en la capa de aplicación
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

    // Inicialización de los tres servicios con el repositorio (Unidad 3)
    this.actualizarEstadoProyectoService = new ActualizarEstadoProyectoService(proyectoRepository);
    this.prorrogarFechaFinService = new ProrrogarFechaFinService(proyectoRepository);
    this.cambiarPromotorProyectoService = new CambiarPromotorProyectoService(proyectoRepository);

    //  INICIALIZACIÓN DE LOS 5 SERVICIOS DE CONSULTA AVANZADA (Unidad 4)
    this.listarProyectosEnCursoService = new ListarProyectosEnCursoService(proyectoRepository);
    this.buscarProyectosPorRangoFechasService = new BuscarProyectosPorRangoFechasService(proyectoRepository);
    this.listarProyectosPorPromotorService = new ListarProyectosPorPromotorService(proyectoRepository);
    this.filtrarProyectosPorEstadoService = new FiltrarProyectosPorEstadoService(proyectoRepository);
    this.buscarProyectosPorDenominacionService = new BuscarProyectosPorDenominacionService(proyectoRepository);
  }

  public UserController userController() {
    return userController;
  }

  // Métodos Getters (Unidad 3)
  public ActualizarEstadoProyectoService getActualizarEstadoProyectoService() {
    return actualizarEstadoProyectoService;
  }

  public ProrrogarFechaFinService getProrrogarFechaFinService() {
    return prorrogarFechaFinService;
  }

  public CambiarPromotorProyectoService getCambiarPromotorProyectoService() {
    return cambiarPromotorProyectoService;
  }

  // 🆕 MÉTODOS GETTERS PARA LOS SERVICIOS DE CONSULTA (Unidad IV)
  public ListarProyectosEnCursoService getListarProyectosEnCursoService() {
    return listarProyectosEnCursoService;
  }

  public BuscarProyectosPorRangoFechasService getBuscarProyectosPorRangoFechasService() {
    return buscarProyectosPorRangoFechasService;
  }

  public ListarProyectosPorPromotorService getListarProyectosPorPromotorService() {
    return listarProyectosPorPromotorService;
  }

  public FiltrarProyectosPorEstadoService getFiltrarProyectosPorEstadoService() {
    return filtrarProyectosPorEstadoService;
  }

  public BuscarProyectosPorDenominacionService getBuscarProyectosPorDenominacionService() {
    return buscarProyectosPorDenominacionService;
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