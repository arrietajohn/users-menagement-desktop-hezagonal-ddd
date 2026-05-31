package com.rcarmona.censo.infrastructure.config;

import com.rcarmona.censo.infrastructure.entrypoint.desktop.cli.io.CensoConsoleIO;
import com.rcarmona.censo.infrastructure.entrypoint.desktop.controller.MunicipioController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;
/**
 * ==========================================================================================
 * AUTOR: Rosary Carmona (rcarmona)
 * ==========================================================================================
 * EXPLICACIÓN ESTILO TUTORIAL (LENGUAJE UBICUO - DDD):
 * 
 * Función Específica de este Archivo:
 * - Contenedor de Inyección de Dependencias manual y gestor de migración de esquema. Ensambla todas las capas (Repositorios -> Servicios -> Controladores) y se encarga de crear o actualizar la base de datos dinámicamente si falta algún componente.
 * 
 * Detalles del Componente:
 * - Nombre de Clase/Interface: CensoDependencyContainer
 * - Propósito Arquitectónico: Aislar la complejidad tecnológica y mantener la cohesión del sistema.
 * - Modo de Uso: Es utilizado de forma desacoplada dentro de la arquitectura para cumplir
 *   exclusivamente con el principio de Responsabilidad Única.
 * ==========================================================================================
 */
public final class CensoDependencyContainer {

  private final MunicipioController municipioController;

  public CensoDependencyContainer() {
    final Connection connection = buildDatabaseConnection();

    com.rcarmona.censo.infrastructure.adapter.persistence.municipio.repository.MunicipioRepositoryMySQL municipioRepo = 
        new com.rcarmona.censo.infrastructure.adapter.persistence.municipio.repository.MunicipioRepositoryMySQL(connection);

    com.rcarmona.censo.application.municipio.port.in.CreateMunicipioUseCase createMunicipio = new com.rcarmona.censo.application.municipio.service.CreateMunicipioService(municipioRepo);
    com.rcarmona.censo.application.municipio.port.in.UpdateMunicipioUseCase updateMunicipio = new com.rcarmona.censo.application.municipio.service.UpdateMunicipioService(municipioRepo, municipioRepo);
    com.rcarmona.censo.application.municipio.port.in.DeleteMunicipioUseCase deleteMunicipio = new com.rcarmona.censo.application.municipio.service.DeleteMunicipioService(municipioRepo, municipioRepo);
    com.rcarmona.censo.application.municipio.port.in.GetMunicipioByIdUseCase getByIdMunicipio = new com.rcarmona.censo.application.municipio.service.GetMunicipioByIdService(municipioRepo);
    com.rcarmona.censo.application.municipio.port.in.GetAllMunicipiosUseCase getAllMunicipios = new com.rcarmona.censo.application.municipio.service.GetAllMunicipiosService(municipioRepo);
    com.rcarmona.censo.application.municipio.port.in.CountMunicipiosUseCase countMunicipios = new com.rcarmona.censo.application.municipio.service.CountMunicipiosService(municipioRepo);
    com.rcarmona.censo.application.municipio.port.in.FindMunicipiosByProvinciaUseCase findByProvincia = new com.rcarmona.censo.application.municipio.service.FindMunicipiosByProvinciaService(municipioRepo);
    com.rcarmona.censo.application.municipio.port.in.SearchMunicipiosByNameUseCase searchByName = new com.rcarmona.censo.application.municipio.service.SearchMunicipiosByNameService(municipioRepo);

    this.municipioController = new MunicipioController(
        createMunicipio, updateMunicipio, deleteMunicipio, getByIdMunicipio, getAllMunicipios, countMunicipios, findByProvincia, searchByName,
        new CensoConsoleIO(new java.util.Scanner(System.in), System.out)
    );
  }

  public MunicipioController municipioController() {
    return municipioController;
  }

  private static Connection buildDatabaseConnection() {
      try {
          Properties props = new Properties();
          try (InputStream input = CensoDependencyContainer.class.getClassLoader().getResourceAsStream("censo_application.properties")) {
              if (input != null) props.load(input);
          }
          
          String engine = System.getProperty("censo.db.engine", props.getProperty("db.engine", "mysql"));
          String host = System.getProperty("censo.db.host", props.getProperty("db.host", "localhost"));
          String port = System.getProperty("censo.db.port", props.getProperty("db.port", "3306"));
          String name = System.getProperty("censo.db.name", props.getProperty("db.name", "7502523003_9_censo_nacional"));
          String user = System.getProperty("censo.db.username", props.getProperty("db.username", "root"));
          String pass = System.getProperty("censo.db.password", props.getProperty("db.password", "R1234"));
          
          // Primero conectar a la base de datos por defecto para correr el script de inicializaciÃ³n
          String baseUrl = "";
          if (engine.equals("postgresql")) {
              baseUrl = "jdbc:postgresql://" + host + ":" + port + "/postgres";
          } else {
              baseUrl = "jdbc:mysql://" + host + ":" + port + "/?allowMultiQueries=true&serverTimezone=UTC";
          }
          
          try (Connection initConn = DriverManager.getConnection(baseUrl, user, pass);
               java.sql.Statement stmt = initConn.createStatement()) {
               
              if (engine.equals("postgresql")) {
                  try {
                      stmt.execute("CREATE DATABASE \"" + name + "\"");
                  } catch (Exception e) {
                      // Error if it already exists, which is fine
                  }
              } else {
                  stmt.execute("CREATE DATABASE IF NOT EXISTS `" + name + "`");
              }
          } catch (Exception ex) {
              System.err.println("Advertencia al crear base de datos: " + ex.getMessage());
          }

          // Conectar a la base de datos ya creada y ejecutar script
          String finalUrl = "";
          if (engine.equals("postgresql")) {
              finalUrl = "jdbc:postgresql://" + host + ":" + port + "/" + name;
          } else {
              finalUrl = "jdbc:mysql://" + host + ":" + port + "/" + name + "?allowMultiQueries=true&serverTimezone=UTC";
          }
          
          Connection finalConn = DriverManager.getConnection(finalUrl, user, pass);
          
          try (java.sql.Statement stmt = finalConn.createStatement()) {
              java.net.URL resource = CensoDependencyContainer.class.getClassLoader().getResource("censo_schema.sql");
              if (resource != null) {
                  @SuppressWarnings("resource")
                  java.util.Scanner s = new java.util.Scanner(resource.openStream(), "UTF-8").useDelimiter("\\A");
                  String sql = s.hasNext() ? s.next() : "";
                  if (!sql.isEmpty()) {
                      String[] statements = sql.split(";");
                      for (String statement : statements) {
                          if (!statement.trim().isEmpty()) {
                              try {
                                  stmt.execute(statement.trim() + ";");
                              } catch (Exception se) {
                                  // Skip individual statement errors (like mysql specific comments on postgres)
                              }
                          }
                      }
                  }
              }
          } catch (Exception e) {
              System.err.println("Advertencia al ejecutar script SQL: " + e.getMessage());
          }
          
          // VerificaciÃ³n de existencia de campos (MigraciÃ³n Estructural DinÃ¡mica)
          ensureColumnExists(finalConn, "provincia", "nombre_provincia", "varchar(100) NOT NULL");
          
          ensureColumnExists(finalConn, "municipio", "nombre_municipio", "varchar(100) NOT NULL");
          ensureColumnExists(finalConn, "municipio", "id_provincia", "int DEFAULT NULL");
          
          ensureColumnExists(finalConn, "distrito", "nombre_distrito", "varchar(100) NOT NULL");
          ensureColumnExists(finalConn, "distrito", "id_municipio", "int DEFAULT NULL");
          
          ensureColumnExists(finalConn, "vivienda", "direccion", "varchar(255) NOT NULL");
          ensureColumnExists(finalConn, "vivienda", "tipo_vivienda", "varchar(50) NOT NULL");
          ensureColumnExists(finalConn, "vivienda", "id_distrito", "int DEFAULT NULL");
          
          ensureColumnExists(finalConn, "persona", "documento_identidad", "varchar(20) NOT NULL");
          ensureColumnExists(finalConn, "persona", "primer_nombre", "varchar(50) NOT NULL");
          ensureColumnExists(finalConn, "persona", "apellidos", "varchar(50) NOT NULL");
          ensureColumnExists(finalConn, "persona", "fecha_nacimiento", "date NOT NULL");
          ensureColumnExists(finalConn, "persona", "id_vivienda_censada", "int DEFAULT NULL");

          ensureColumnExists(finalConn, "matriculas", "fecha", "datetime DEFAULT NULL");
          ensureColumnExists(finalConn, "matriculas", "nombre", "varchar(100) DEFAULT NULL");
          ensureColumnExists(finalConn, "matriculas", "apellidos", "varchar(100) DEFAULT NULL");
          ensureColumnExists(finalConn, "matriculas", "asignatura", "varchar(100) DEFAULT NULL");
          ensureColumnExists(finalConn, "matriculas", "nota1", "decimal(3,1) DEFAULT NULL");
          ensureColumnExists(finalConn, "matriculas", "nota2", "decimal(3,1) DEFAULT NULL");
          ensureColumnExists(finalConn, "matriculas", "nota3", "decimal(3,1) DEFAULT NULL");
          ensureColumnExists(finalConn, "matriculas", "nota_definitiva", "decimal(3,1) DEFAULT NULL");
          ensureColumnExists(finalConn, "matriculas", "promedio", "decimal(3,1) DEFAULT NULL");
          ensureColumnExists(finalConn, "matriculas", "mensaje", "text");

          return finalConn;
      } catch (Exception e) {
          throw new RuntimeException("Error al conectar con la base de datos de Municipio: " + e.getMessage(), e);
      }
  }

  private static void ensureColumnExists(Connection conn, String tableName, String columnName, String columnDefinition) {
      try {
          java.sql.DatabaseMetaData meta = conn.getMetaData();
          boolean exists = false;
          try (java.sql.ResultSet rs = meta.getColumns(null, null, tableName, columnName)) {
              if (rs.next()) exists = true;
          }
          if (!exists) {
              try (java.sql.ResultSet rs = meta.getColumns(null, null, tableName.toLowerCase(), columnName.toLowerCase())) {
                  if (rs.next()) exists = true;
              }
          }
          if (!exists) {
              try (java.sql.ResultSet rs = meta.getColumns(null, null, tableName.toUpperCase(), columnName.toUpperCase())) {
                  if (rs.next()) exists = true;
              }
          }
          
          if (!exists) {
              try (java.sql.Statement stmt = conn.createStatement()) {
                  stmt.execute("ALTER TABLE " + tableName + " ADD COLUMN " + columnName + " " + columnDefinition);
              }
          }
      } catch (Exception e) {
          // Ignoramos silenciosamente si falla porque el motor no soporta ALTER o la sintaxis.
      }
  }
}


